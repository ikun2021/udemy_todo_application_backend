package com.zk.todo.service;

import com.zk.todo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService {
    private static Map<Integer,Todo> map = new HashMap<>();

    static{
        map.put(1,new Todo(1,"admin","learning java",false,new Date()));
        map.put(2,new Todo(2,"admin","learning python",false,new Date()));
        map.put(3,new Todo(3,"admin","learning react",false,new Date()));
        map.put(4,new Todo(4,"admin","learning docker",false,new Date()));
        map.put(5,new Todo(5,"admin","learning css",false,new Date()));
        map.put(6,new Todo(6,"admin","learning html",false,new Date()));
        map.put(7,new Todo(7,"admin","learning selenium",false,new Date()));

    }


    public Collection<Todo> findAll() {
        return map.values();
    }

    public void deleteById(int id) {
        map.remove(id);
    }

    public Todo findById(int id) {
        return map.get(id);
    }

    public void updateTodo(Todo todo) {
        map.put(todo.getId(),todo);
    }

}
