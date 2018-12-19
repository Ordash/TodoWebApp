package com.greenfox.todoappforheroku;

import com.greenfox.todoappforheroku.repositories.AssigneeRepository;
import com.greenfox.todoappforheroku.repositories.TodoRepository;
import com.greenfox.todoappforheroku.repositories.entities.Assignee;
import com.greenfox.todoappforheroku.repositories.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappforherokuApplication implements CommandLineRunner {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AssigneeRepository assigneeRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoappforherokuApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        todoRepository.save(new Todo("Walk the dog", true, false));
        todoRepository.save(new Todo("Water the plants", false, true));
        todoRepository.save(new Todo("Save the world", false, false));
        todoRepository.save(new Todo("Find medicine for cancer", true, false));
        todoRepository.save(new Todo("Cook steak", true, false));
        todoRepository.save(new Todo("Find the meaning of life", false, true));
        assigneeRepository.save(new Assignee("john", "john@valami.com"));
        assigneeRepository.save(new Assignee("sanya", "jdfd@valami.com"));
        assigneeRepository.save(new Assignee("bela", "jgdgdfn@valami.com"));

    }
}

