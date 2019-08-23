package com.sh.demo.core.dao;/**
 * @program: SpringCloud_Parent
 * @description: 角色Dao
 * @author: ZhangChi
 * @create: 2019-08-23 12:14
 **/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sh.demo.core.entity.SysRoleEntity;

import java.util.List;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 角色Dao
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 12:14
 **/
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
    * @Description: 通过用户ID查询角色集合
    * @Param: [userId]
    * @return: java.util.List<com.sh.demo.core.entity.SysRoleEntity>角色名集合
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);


}
