package com.zk.todo.controller;

import com.zk.todo.entity.Todo;

import com.zk.todo.service.Auth0;
import com.zk.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private Auth0 auth0;


    @GetMapping("/todos")
    public Collection<Todo> findAll(){
        return todoService.findAll();
    }

    @DeleteMapping("/todos/{id}")
    public void deleteById(@PathVariable int id){
       todoService.deleteById(id);
    }


    @GetMapping("/todos/{id}")
    public Todo findById(@PathVariable int id){
        return todoService.findById(id);
    }

    @PutMapping("/todos/{id}")
    //@Requestbody注解需要entity有构造器
    public ResponseEntity<Void>  updateTodo(@RequestBody Todo todo,@PathVariable int id) {
        todoService.updateTodo(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/authenticate")
    public Map authenticate(@RequestBody Map map){
        Map<Object,Object> result = new HashMap<>();
        String username=(String)map.get("username");
        String password=(String)map.get("password");
        if ("admin".equals(username) && "123123".equals(password)){
            result.put("token",auth0.getToken(username));
            System.out.println(result);
            return result;
        }
        return null;
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        // 从请求头中获取token字符串
        String token = request.getHeader("Authorization");
        // 解析失败就提示用户登录
        if (token==null) {
            return "请先登录";
        }
        // 解析成功就执行业务逻辑返回数据
        System.out.println("token"+token);
        return "api成功返回数据";
    }





}
