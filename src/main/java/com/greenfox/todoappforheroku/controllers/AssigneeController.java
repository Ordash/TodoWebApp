package com.greenfox.todoappforheroku.controllers;

import com.greenfox.todoappforheroku.repositories.AssigneeRepository;
import com.greenfox.todoappforheroku.repositories.TodoRepository;
import com.greenfox.todoappforheroku.repositories.entities.Assignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignee")
public class AssigneeController {

    private final AssigneeRepository assigneeRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public AssigneeController(AssigneeRepository assigneeRepository, TodoRepository todoRepository) {
        this.assigneeRepository = assigneeRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping({"/", "", "/list"})
    public String listAssignees(Model model) {
        model.addAttribute("allassignee", assigneeRepository.findAll());
        return "assignees";
    }

    @GetMapping("{id}/edit")
    public String editAssignee(Model model, @PathVariable Long id) {
        if (assigneeRepository.findById(id).isPresent()) {
            model.addAttribute("editAssignee", assigneeRepository.findById(id).get());
        }
        model.addAttribute("allassignee", assigneeRepository.findAll());
        return "assignees";
    }

    @PostMapping("{id}/edit")
    public String editAssigneePost(@ModelAttribute("editAssignee") Assignee assignee) {
        assigneeRepository.save(assignee);
        return "redirect:/assignee/list";
    }

    @GetMapping("/add")
    public String addAssignee(Model model) {
        model.addAttribute("newAssignee", new Assignee());
        model.addAttribute("allassignee", assigneeRepository.findAll());
        return "assignees";
    }

    @PostMapping("/add")
    public String addAssigneePost(@ModelAttribute("newAssignee") Assignee assignee) {
        assigneeRepository.save(assignee);
        return "redirect:/assignee/list";
    }

    @GetMapping(value = "{id}/delete")
    public String deleteTodo(@PathVariable Long id) {
        todoRepository.findAllByAssignee_Id(id).forEach(todo -> todo.setAssignee(null));
        if (assigneeRepository.findById(id).isPresent()) {
            assigneeRepository.delete(assigneeRepository.findById(id).get());
        }
        return "redirect:/assignee/list";
    }

}
