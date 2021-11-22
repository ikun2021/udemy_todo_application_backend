package com.zk.todo;

import com.zk.todo.service.JwtInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
//使用WebMvcConfigure来添加拦截器
public class TodoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new JwtInterceptor());
    }




}
