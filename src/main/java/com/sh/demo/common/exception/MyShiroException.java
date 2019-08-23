package com.sh.demo.common.exception;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringCloud_Parent
 *
 * @description: shiro异常处理类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 09:10
 **/
@ControllerAdvice
public class MyShiroException {
    /**
    * @Description: 处理Shiro权限拦截异常 , 如果返回JSON数据格式请加上@ResponseBody注解
    * @Param:
    * @return:  Map<Object> 返回结果集
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String , Object> defaultErrorHandler() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("403", "权限不足");
        return hashMap;
    }
}
