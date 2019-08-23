package com.sh.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.demo.core.entity.SysMenuEntity;

import java.util.List;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 权限业务接口
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 11:55
 **/

public interface SysMenuService extends IService<SysMenuEntity> {

    /**
    * @Description: 根据角色查询用户权限
    * @Param: [roleId]
    * @return: java.util.List<com.sh.demo.core.service.SysMenuService> 权限集合
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);
}
