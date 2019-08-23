package com.sh.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.demo.core.entity.SysRoleEntity;

import java.util.List;

/**
 * @program: SpringCloud_Parent
 * @description: ${角色业务接口}
 * @author: ZhangChi
 * @create: 2019-08-23 11:59
 **/
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
    * @Description: 通过用户ID查询角色集合
    * @Param: [userId]
    * @return: java.util.List<com.sh.demo.core.service.SysRoleService> 角色名集合
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    List<SysRoleEntity> selectSyRoleByUserId(Long userId);



}
