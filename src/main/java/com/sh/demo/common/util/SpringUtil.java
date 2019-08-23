package com.sh.demo.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud_Parent
 *
 * @description: Spring上下文工具类
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-23 09:20
 **/
@Component
public class SpringUtil  implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * @Description: Spring 在bean初始化后会判断是不是ApplicationContextAware的子类,如果该类是,setApplicationContext()方法,会将容器中AppliationContext作为参数传入进去
     * @Param:
     * @return:
     * @Author: ZhangChi
     * @Date: 2019/8/23
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 通过name返回指定的Bean
     */
    public static <T>  T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
