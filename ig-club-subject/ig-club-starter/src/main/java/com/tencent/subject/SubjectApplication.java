package com.tencent.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题微服务启动类
 */

@SpringBootApplication
@ComponentScan("com.tencent")
@MapperScan("com.tencent.**.mapper")
@EnableFeignClients(basePackages = "com.tencent")
public class SubjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class,args);
    }
}
