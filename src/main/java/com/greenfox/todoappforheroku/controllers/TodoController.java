package com.greenfox.todoappforheroku.controllers;

import com.greenfox.todoappforheroku.repositories.TodoRepository;
import com.greenfox.todoappforheroku.repositories.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @GetMapping(value = { "","/","/list" })
    public String list(Model model, @RequestParam(value="isActive", required = false)Boolean isDone,
                                    @RequestParam(value="desc", required = false)Long id){
        if(id != null) {
            model.addAttribute("desc", todoRepository.findById(id).get());
        }
        if(isDone != null && isDone){
            model.addAttribute("todos",todoRepository.findByDone(!isDone));
        } else {
            model.addAttribute("todos", todoRepository.findAll());
        }
        return "todolist";
    }

    @GetMapping(value = "/add")
    public String addTodo(Model model){
        model.addAttribute("newtodo", new Todo());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addTodoPost(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todo/list";

    }

    @GetMapping(value = "{id}/delete")
    public String deleteTodo( @PathVariable Long id) {
        if(todoRepository.findById(id).isPresent()) {
            todoRepository.delete(todoRepository.findById(id).get());
        }
        return "redirect:/todo/list";
    }

    @GetMapping(value = "{id}/edit")
    public String editTodo(Model model, @PathVariable Long id) {
        if(todoRepository.findById(id).isPresent()) {
            model.addAttribute("editTodo", todoRepository.findById(id).get());
        }
        return "edit";
    }

    @PostMapping(value = "{id}/edit")
    public String editTodoPost(@ModelAttribute("editTodo") Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todo/list";

    }

}
