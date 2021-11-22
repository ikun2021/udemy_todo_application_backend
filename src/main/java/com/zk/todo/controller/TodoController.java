package com.zk.todo.controller;
import com.zk.todo.entity.Todo;
import com.zk.todo.repository.TodoRepository;
import com.zk.todo.service.Auth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private Auth0 auth0;

    @GetMapping("/jpa/users/{username}/todos")
    public List<Todo> findAll(@PathVariable String username){
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo findById(@PathVariable String username,@PathVariable Long id){
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if(optionalTodo.isPresent()){
            return optionalTodo.get();
        }
        return new Todo();
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String username,@PathVariable Long id){
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jpa/users/{username}/todos/{id}")
    //@Requestbody注解需要entity有构造器
    public ResponseEntity<Todo>  updateTodo(@PathVariable String username,@PathVariable Long id,@RequestBody Todo todo) {
        todo.setUsername(username);
        Todo todoUpdated = todoRepository.save(todo);
        return new ResponseEntity<Todo>(HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String username,@RequestBody Todo todo){
        todo.setUsername(username);
        Todo createdTodo = todoRepository.save(todo);
        System.out.println(createdTodo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/authenticate")
    public Map authenticate(@RequestBody Map map){
        Map<Object,Object> result = new HashMap<>();
        String username=(String)map.get("username");
        String password=(String)map.get("password");
        if ("admin".equals(username) && "123123".equals(password)){
            result.put("token",auth0.getToken(username));
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
        return "api成功返回数据";
    }
}
