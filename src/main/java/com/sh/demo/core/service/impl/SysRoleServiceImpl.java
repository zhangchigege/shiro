package com.sh.demo.core.service.impl;/**
 * @program: SpringCloud_Parent
 * @description: 角色业务实现
 * @author: ZhangChi
 * @create: 2019-08-23 12:21
 **/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.demo.core.dao.SysRoleDao;
import com.sh.demo.core.entity.SysRoleEntity;
import com.sh.demo.core.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 角色业务实现
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 12:21
 **/
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    /**
    * @Description: 通过用户ID查询角色集合
    * @Param: [userId]
    * @return: java.util.List<com.sh.demo.core.service.SysRoleService>角色名集合
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    @Override
    public List<SysRoleEntity> selectSyRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }
}
