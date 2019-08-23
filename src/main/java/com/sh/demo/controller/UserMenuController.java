package com.sh.demo.controller;/**
 * @program: SpringCloud_Parent
 * @description: 权限测试
 * @author: ZhangChi
 * @create: 2019-08-23 12:53
 **/

import com.sh.demo.common.util.ShiroUtils;
import com.sh.demo.core.entity.SysMenuEntity;
import com.sh.demo.core.entity.SysRoleEntity;
import com.sh.demo.core.entity.SysRoleMenuEntity;
import com.sh.demo.core.entity.SysUserEntity;
import com.sh.demo.core.service.SysMenuService;
import com.sh.demo.core.service.SysRoleMenuService;
import com.sh.demo.core.service.SysRoleService;
import com.sh.demo.core.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringCloud_Parent
 * @description: 权限测试
 * @author: ZhangChi
 * @create: 2019-08-23 12:53
 **/
@RestController
@RequestMapping("/menu")
public class UserMenuController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /** 
    * @Description: 获取用户信息集合 
    * @Param: [] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @RequestMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public Map<String, Object> getUserInfoList() {
        Map<String, Object> map = new HashMap<>();
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        map.put("sysUserEntityList", sysUserEntityList);
        return map;
    }

    /** 
    * @Description: 获取角色信息集合 
    * @Param: [] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @RequestMapping("/getRoleInfoList")
    @RequiresPermissions("sys:role:info")
    public Map<String, Object> getRoleInfoList() {
        Map<String, Object> map = new HashMap<>();
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        map.put("sysRoleEntityList", sysRoleEntityList);
        return map;
    }

    /** 
    * @Description: 获取权限信息集合 
    * @Param:  
    * @return:  
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @RequestMapping("/getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public Map<String, Object> getMenuInfoList() {
        Map<String, Object> map = new HashMap<>();
        List<SysMenuEntity> sysMenuEntityList = sysMenuService.list();
        map.put("sysMenuEntityList", sysMenuEntityList);
        return map;
    }

    /** 
    * @Description: 获取所有数据 
    * @Param: [] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @RequestMapping("/getInfoAll")
    @RequiresPermissions("sys:info:all")
    public Map<String, Object> getInfoAll() {
        Map<String, Object> map = new HashMap<>();
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        map.put("sysUserEntityList", sysUserEntityList);
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        map.put("sysRoleEntityList", sysRoleEntityList);
        List<SysMenuEntity> sysMenuEntityList = sysMenuService.list();
        map.put("sysMenuEntityList", sysMenuEntityList);
        return map;
    }

    /** 
    * @Description: 添加管理员角色权限(测试动态权限更新)
    * @Param: [] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @RequestMapping("/addMenu")
    public Map<String, Object> addMenu() {
        //添加管理员角色权限
        SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
        sysRoleMenuEntity.setMenuId(4L);
        sysRoleMenuEntity.setRoleId(1L);
        sysRoleMenuService.save(sysRoleMenuEntity);
        //清除缓存
        String username = "admin";
        ShiroUtils.deleteCache(username, false);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "权限添加成功");
        return map;
    }


}
