package com.sh.demo.core.entity;/**
 * @program: SpringCloud_Parent
 * @description: 角色权限实体类
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
 * @description: 角色权限实体类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 08:51
 **/
@Data
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {

    private static final  long serialVersionUID = 1L;

    /*
    id
     */
    @TableId
    private Long id;

    /*
    角色ID
     */
    private Long roleId;

    /*
    权限ID
     */
    private Long menuId;

}
