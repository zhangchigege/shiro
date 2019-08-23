package com.sh.demo.core.entity;/**
 * @program: SpringCloud_Parent
 * @description: 用户实体类
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
 * @description: 用户实体类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 08:52
 **/
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    用户Id
     */
    @TableId
    private Long userId;

    /*
    用户名
     */
    private String username;

    /*
    密码
     */
    private String password;

    /*
    盐值
     */
    private String salt;

    /*
    状态:NORMAL 正常 PROHIBIT禁用
     */
    private String state;
}
