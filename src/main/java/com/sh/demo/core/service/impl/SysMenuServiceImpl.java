package com.sh.demo.core.service.impl;/**
 * @program: SpringCloud_Parent
 * @description: 权限业务实现
 * @author: ZhangChi
 * @create: 2019-08-23 12:04
 **/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.demo.core.dao.SysMenuDao;
import com.sh.demo.core.entity.SysMenuEntity;
import com.sh.demo.core.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 权限业务实现
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 12:04
 **/
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    /** 
    * @Description: 根据角色查询用户权限
    * @Param: [roleId] 
    * @return: java.util.List<com.sh.demo.core.entity.SysMenuEntity> 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */
    @Override
    public List<SysMenuEntity> selectSysMenuByRoleId(Long roleId) {
        return this.baseMapper.selectSysMenuByRoleId(roleId);
    }



}
