package com.zk.todo;

import com.zk.todo.service.JwtInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class TodoApplication implements WebMvcConfigurer {//用webmvcconfigurer来使拦截器生效

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //使拦截器生效
         registry.addInterceptor(new JwtInterceptor());
    }




}
