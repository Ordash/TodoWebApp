package com.greenfox.todoappforheroku.repositories;

import com.greenfox.todoappforheroku.repositories.entities.Assignee;
import org.springframework.data.repository.CrudRepository;

public interface AssigneeRepository extends CrudRepository<Assignee, Long> {
    Assignee findByName(String name);
}
