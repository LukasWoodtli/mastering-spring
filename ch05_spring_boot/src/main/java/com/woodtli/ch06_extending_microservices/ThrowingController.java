package com.woodtli.ch06_extending_microservices;

import com.woodtli.ch05_spring_boot.bean.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.woodtli.ch05_spring_boot.service.TodoService;



@RestController
public class ThrowingController {
    @Autowired
    private TodoService todoService;

    @GetMapping(path = "/bla/dummy-service")
    public Todo throwingMethod() {
        throw new RuntimeException("Some exception occured");
    }

    @GetMapping(path = "/bla/{name}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String name, @PathVariable int id) {
        Todo todo = todoService.retrieveTodo(id);
        if (todo == null) {
            throw new TodoNotFoundException("Todo not found");
        }
        return todo;
    }
}

