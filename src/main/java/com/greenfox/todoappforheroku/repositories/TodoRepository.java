package com.greenfox.todoappforheroku.repositories;

import com.greenfox.todoappforheroku.repositories.entities.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByDone(Boolean bool);

}
