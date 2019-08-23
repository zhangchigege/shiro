package com.sh.demo.core.dao;/**
 * @program: SpringCloud_Parent
 * @description: 权限业务
 * @author: ZhangChi
 * @create: 2019-08-23 12:05
 **/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sh.demo.core.entity.SysMenuEntity;

import java.util.List;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 权限业务
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 12:05
 **/
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);
}
