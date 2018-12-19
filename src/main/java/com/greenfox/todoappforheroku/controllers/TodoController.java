package com.greenfox.todoappforheroku.controllers;

import com.greenfox.todoappforheroku.repositories.AssigneeRepository;
import com.greenfox.todoappforheroku.repositories.TodoRepository;
import com.greenfox.todoappforheroku.repositories.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoRepository todoRepository;
    private final AssigneeRepository assigneeRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository, AssigneeRepository assigneeRepository) {
        this.todoRepository = todoRepository;
        this.assigneeRepository = assigneeRepository;
    }


    @GetMapping(value = { "","/","/list" })
    public String list(Model model, @RequestParam(value="isActive", required = false)Boolean isDone,
                                    @RequestParam(value="desc", required = false)Long id,
                                    @RequestParam(value = "search", required = false) String search) {


        model.addAttribute("searchstring", search);

        if(id != null && search.equals("null") && todoRepository.findById(id).isPresent()) {
            model.addAttribute("desc", todoRepository.findById(id).get());
            model.addAttribute("todos", todoRepository.findAllByOrderByDateDesc());
            System.out.println("descnullsearch");


            return "todolist";
        }

        if(id != null && todoRepository.findById(id).isPresent()) {
            model.addAttribute("desc", todoRepository.findById(id).get());
            model.addAttribute("todos", todoRepository.findAllByTitleContainsOrDescriptionContains(search,search));
            System.out.println("descnsearch");
            return "todolist";
        }

        if(search != null) {
            model.addAttribute("todos", todoRepository.findAllByTitleContainsOrDescriptionContains(search,search));
            System.out.println("search");
            return "todolist";
        }
        if(isDone != null && isDone){
            model.addAttribute("todos",todoRepository.findByDone(!isDone));
            System.out.println("active");
        } else {
            model.addAttribute("todos", todoRepository.findAllByOrderByDateDesc());
            System.out.println("all");
        }
        return "todolist";
    }

    @GetMapping(value = "/add")
    public String addTodo(Model model){
        model.addAttribute("assigneess", assigneeRepository.findAll());
        model.addAttribute("newtodo", new Todo());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addTodoPost(@ModelAttribute Todo todo,@ModelAttribute("assige")String name) {
        todo.setAssignee(assigneeRepository.findByName(name));
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
        model.addAttribute("assignees", assigneeRepository.findAll());
        if(todoRepository.findById(id).isPresent()) {
            model.addAttribute("editTodo", todoRepository.findById(id).get());
        }
        return "edit";
    }

    @PostMapping(value = "{id}/edit")
    public String editTodoPost(@ModelAttribute("editTodo") Todo todo,@ModelAttribute("assig")String name) {
        todo.setAssignee(assigneeRepository.findByName(name));
        todoRepository.save(todo);
        return "redirect:/todo/list";
    }


}
