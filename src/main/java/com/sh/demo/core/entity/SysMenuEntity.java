package com.sh.demo.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 权限实体类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 08:50
 **/
@Data
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {

    private static final  long serialVersionUID = 1L;

    /*
    权限id
     */
    @TableId
    private Long menuId;

    /*
    权限名称
     */
    private String name;

    /*
    权限标识
     */
    private String perms;


}
