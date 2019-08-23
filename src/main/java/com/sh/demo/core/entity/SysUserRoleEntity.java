package com.sh.demo.core.entity;/**
 * @program: SpringCloud_Parent
 * @description: 用户角色实体类
 * @author: ZhangChi
 * @create: 2019-08-23 08:52
 **/

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 用户角色实体类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 08:52
 **/
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    ID
     */
    @TableId
    private Long id;

    /*
    用户ID
     */
    private Long userId;

    /*
    角色ID
     */
    private Long roleId;

}
