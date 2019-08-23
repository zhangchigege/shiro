package com.sh.demo.core.service.impl;/**
 * @program: SpringCloud_Parent
 * @description: 用户与角色业务实现
 * @author: ZhangChi
 * @create: 2019-08-23 12:23
 **/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.demo.core.dao.SysUserRoleDao;
import com.sh.demo.core.entity.SysUserRoleEntity;
import com.sh.demo.core.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 用户与角色业务实现
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 12:23
 **/
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {
}
