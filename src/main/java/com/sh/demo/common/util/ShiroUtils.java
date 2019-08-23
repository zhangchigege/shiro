package com.sh.demo.common.util;

import com.sh.demo.core.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * @program: SpringCloud_Parent
 *
 * @description: Siro工具类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 09:25
 **/
public class ShiroUtils {

    /**
     * 私有构造器
     */
    private ShiroUtils(){};
    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);
    /**
     * 获取当前用户Session
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     */
    public static void logOut(){
        SecurityUtils.getSubject().logout();
    }

    /**
    * @Description: 获取当前用户信息
    * @Param: []
    * @return: com.sh.demo.core.entity.SysUserEntity  用户信息
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    public static SysUserEntity getUserInfo() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     * @param username 用户名称
     * @param isRemoveSession 是否阐述Session
     */
    public static void deleteCache(String username, boolean isRemoveSession){
        //从缓存中获取Session
        Session session = null;
        //获取Redis中session集合
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        SysUserEntity  sysUserEntity = null;
        Object attrubute = null;
        for (Session sessionInfo: sessions) {
            //遍历Session,找到该用户名称对应的Session
            attrubute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attrubute == null) {
                continue;
            }
            //获取用户信息
             sysUserEntity = (SysUserEntity) ((SimplePrincipalCollection) attrubute).getPrimaryPrincipal();
            if (sysUserEntity == null) {
                continue;
            }
            //判断用户名是否一致
            if (Objects.equals(sysUserEntity.getUsername(),username)){
                session = sessionInfo;
            }
        }
        if (session == null || attrubute == null) {
            return;
        }
        //删除Session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        //删除Cache,在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attrubute);
    }

}
