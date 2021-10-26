package com.zk.todo.service;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {

        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 简单的白名单，登录这个接口直接放行
            if ("/authenticate".equals(request.getRequestURI())) {
                return true;
            }
//            到底是否需要这一段代码来避免前端请求的preflight错误？？？？
            if("OPTIONS".equals(request.getMethod())){
                String a = request.getMethod();
                int b=0;
                return true;
            }
//             从请求头中获取token字符串并解析
            String token = request.getHeader("Authorization");
            System.out.println(token);
            int i=1;
            if (token!= null) {
                return true;
            }



            // 走到这里就代表是其他接口，且没有登录
            // 设置响应数据类型为json（前后端分离）
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            // 设置响应内容，结束请求
            out.write("请先登录");
            out.flush();
            out.close();
            return false;
        }
}
