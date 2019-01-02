package com.greenfox.todoappforheroku.services;

import com.greenfox.todoappforheroku.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetNull {


    private TodoRepository todoRepository;

    @Autowired
    public SetNull(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void TodoAssigneeId(Long id){
        todoRepository.findAllByAssignee_Id(id).forEach(todo -> todo.setAssignee(null));
    }
}
