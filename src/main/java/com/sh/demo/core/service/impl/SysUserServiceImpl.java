package com.sh.demo.core.service.impl;/**
 * @program: SpringCloud_Parent
 * @description: 系统用户业务实现
 * @author: ZhangChi
 * @create: 2019-08-23 12:25
 **/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.demo.core.dao.SysUserDao;
import com.sh.demo.core.entity.SysUserEntity;
import com.sh.demo.core.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 系统用户业务实现
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 12:25
 **/
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    /** 
    * @Description: 根据用户名查询实体
    * @Param: [username] 
    * @return: com.sh.demo.core.entity.SysUserEntity 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @Override
    public SysUserEntity selectUserByName(String username) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserEntity::getUsername,username);
        return this.baseMapper.selectOne(queryWrapper);
    }

}
