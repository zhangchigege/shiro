package com.sh.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.demo.core.entity.SysUserEntity;

/**
 * @program: SpringCloud_Parent
 * @description: ${根据用户名查询实体}
 * @author: ZhangChi
 * @create: 2019-08-23 12:02
 **/
public interface SysUserService extends IService<SysUserEntity> {

    /**
    * @Description: 根据用户名查询实体
    * @Param: [username]
    * @return: com.sh.demo.core.entity.SysUserEntity 用户实体
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    SysUserEntity selectUserByName(String username);
}
