package com.sh.demo.controller;
import com.sh.demo.common.util.ShiroUtils;
import com.sh.demo.core.entity.SysUserEntity;
import com.sh.demo.core.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 用户登录
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 12:55
 **/
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

    @Autowired
    private SysUserService sysUserService;

    /** 
    * @Description: 登录 
    * @Param: [sysUserEntity] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody SysUserEntity sysUserEntity){
        Map<String,Object> map = new HashMap<>();
        //进行身份验证
        try{
            //验证身份和登陆
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUserEntity.getUsername(), sysUserEntity.getPassword());
            //验证成功进行登录操作
            subject.login(token);
        }catch (IncorrectCredentialsException e) {
            map.put("code",500);
            map.put("msg","用户不存在或者密码错误");
            return map;
        } catch (LockedAccountException e) {
            map.put("code",500);
            map.put("msg","登录失败，该用户已被冻结");
            return map;
        } catch (AuthenticationException e) {
            map.put("code",500);
            map.put("msg","该用户不存在");
            return map;
        } catch (Exception e) {
            map.put("code",500);
            map.put("msg","未知异常");
            return map;
        }
        map.put("code",0);
        map.put("msg","登录成功");
        map.put("token", ShiroUtils.getSession().getId().toString());
        return map;
    }
    /** 
    * @Description: 未登录 
    * @Param: [] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: ZhangChi
    * @Date: 2019/8/23 
    */ 
    @RequestMapping("/unauth")
    public Map<String,Object> unauth(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg","未登录");
        return map;
    }



}
