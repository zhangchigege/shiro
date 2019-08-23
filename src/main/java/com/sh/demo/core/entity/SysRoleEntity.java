package com.sh.demo.core.entity;/**
 * @program: SpringCloud_Parent
 * @description: 角色实体类
 * @author: ZhangChi
 * @create: 2019-08-23 08:51
 **/

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 角色实体类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 08:51
 **/
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {

    private static final  long serialVersionUID = 1l;

    /*
    角色Id
     */
    @TableId
    private Long roleId;

    /*
    角色名称
     */
    private String roleName;
}
