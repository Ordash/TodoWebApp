package com.greenfox.todoappforheroku.repositories.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "assignee")
    private List<Todo> todosList;

    public Assignee() {
    }

    public Assignee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public List<Todo> getTodosList() {
        return todosList;
    }

    public void setTodosList(List<Todo> todosList) {
        this.todosList = todosList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @PreRemove
//    public void setNull(){
//        todosList.forEach(a -> a.setAssignee(null));
//    }
}
