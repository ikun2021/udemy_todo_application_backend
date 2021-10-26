package com.zk.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Todo {
    private int id;
    private String username;
    private String description;
    private boolean isDone;
    private Date targetDate;


}
