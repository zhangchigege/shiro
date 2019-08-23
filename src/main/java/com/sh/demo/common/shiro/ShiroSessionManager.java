package com.sh.demo.common.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @program: SpringCloud_Parent
 *
 * @description: 自定义获取Token
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 10:46
 **/
public class ShiroSessionManager extends DefaultWebSessionManager {

    //定义常量
    private static final String AUTHORIZATION = "Authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
    //重写构造器
    public ShiroSessionManager() {
        super();
        this.setDeleteInvalidSessions(true);
    }

    /**
    * @Description: 重写方法实现从请求头获取token便于接口统一
     * 每次请求进来,shiro会从请求头去找Authorization这个key对应的Value(Token)
    * @Param:
    * @return:
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中存在token,则从请求头中获取token
        if (!StringUtils.isEmpty(token)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }else {
            //否则按默认规则从cookie去token
            return super.getSessionId(request, response);
        }

    }






}
