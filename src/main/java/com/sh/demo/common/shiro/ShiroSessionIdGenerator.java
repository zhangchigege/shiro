package com.sh.demo.common.shiro;

import com.sh.demo.common.constant.RedisConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @program: SpringCloud_Parent
 *
 * @description: Shiro的SessionId生成器
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 10:36
 **/
public class ShiroSessionIdGenerator implements SessionIdGenerator {
    /**
    * @Description:  实现SessionId生成
    * @Param: [session]
    * @return: java.io.Serializable
    * @Author: ZhangChi
    * @Date: 2019/8/23
    */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format(RedisConstant.REDIS_PREFIX_LOGIN, sessionId);
    }
}
