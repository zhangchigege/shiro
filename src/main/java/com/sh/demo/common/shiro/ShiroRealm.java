package com.sh.demo.common.shiro;/**
 * @program: SpringCloud_Parent
 * @description: 权限匹配和账号密码匹配
 * @author: ZhangChi
 * @create: 2019-08-23 10:41
 **/

import com.sh.demo.common.util.ShiroUtils;
import com.sh.demo.core.entity.SysMenuEntity;
import com.sh.demo.core.entity.SysRoleEntity;
import com.sh.demo.core.entity.SysUserEntity;
import com.sh.demo.core.service.SysMenuService;
import com.sh.demo.core.service.SysRoleService;
import com.sh.demo.core.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 权限匹配和账号密码匹配
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 10:41
 **/
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
    * @Description: 授权权限
     * 用户进行权限验证时候Shiro会区缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
    * @Param: [principalCollection]
    * @return: org.apache.shiro.authz.AuthorizationInfo
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户ID
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUserEntity sysUserEntity = (SysUserEntity) principalCollection.getPrimaryPrincipal();
        Long userId =sysUserEntity.getUserId();
        //这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        //查询角色和权限(这里根据业务自行查询)
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.selectSyRoleByUserId(userId);
        for (SysRoleEntity sysRoleEntity:sysRoleEntityList) {
            rolesSet.add(sysRoleEntity.getRoleName());
            List<SysMenuEntity> sysMenuEntityList = sysMenuService.selectSysMenuByRoleId(sysRoleEntity.getRoleId());
            for (SysMenuEntity sysMenuEntity :sysMenuEntityList) {
                permsSet.add(sysMenuEntity.getPerms());
            }
        }
        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        return authorizationInfo;
    }

    /**
    * @Description: 身份认证
    * @Param: [authenticationToken]
    * @return: org.apache.shiro.authc.AuthenticationInfo
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户输入的账号
        String username = (String) authenticationToken.getPrincipal();
        //通过username从数据库中查找user对象,如果找到进行验证
        //实际项目中,可以根据实际情况做缓存,不做,shiro业务时有时间间隔机制,2分钟内不会重复执行该方法
        SysUserEntity user = sysUserService.selectUserByName(username);
        //判断账号是否存在
        if (user == null) {
            throw new LockedAccountException();
        }

        //判断账号是否被冻结
        if (user.getState() == null || user.getState().equals("PROHIBIT")) {
            throw new LockedAccountException();
        }
        //进行验证
        /**
        * @Description:
        * @Param: [user]  用户名
         *  user.getPassword() 密码
         *  ByteSource.Util.bytes(user.getSalt()) 设置盐值
        * @return: org.apache.shiro.authc.AuthenticationInfo
        * @Author: ZhangChi
        * @Date: 2019/8/23
        */
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,                                  //用户名
                user.getPassword(),                    //密码
                ByteSource.Util.bytes(user.getSalt()), //设置盐值
                getName()
        );
        //验证成功开始踢人(清楚缓存和Session
        ShiroUtils.deleteCache(username, true);
        return authenticationInfo;
    }
}
