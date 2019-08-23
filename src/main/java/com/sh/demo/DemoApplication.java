package com.sh.demo;/**
 * @program: SpringCloud_Parent
 * @description: 启动类
 * @author: ZhangChi
 * @create: 2019-08-23 08:48
 **/
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = {"com.sh.demo.core.dao"}) //扫描DAO
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(DemoApplication.class, args);
    }

}
