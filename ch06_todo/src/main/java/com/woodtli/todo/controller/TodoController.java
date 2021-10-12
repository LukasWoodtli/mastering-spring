package com.woodtli.todo.controller;

import com.woodtli.todo.bean.Todo;
import com.woodtli.todo.service.TodoNotFoundException;
import com.woodtli.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/users/{name}/todos")
    public List<Todo> retrieveTodos(@PathVariable String name) {
        return todoService.retrieveTodos(name);
    }

    @GetMapping("/users/{name}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String name,
                             @PathVariable int id) {
        Todo todo = todoService.retrieveTodo(id);

        if (todo == null) {
            throw new TodoNotFoundException("Todo not found");
        }

        return todo;
    }

    @PutMapping("/users/{name}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String name,
                                           @PathVariable int id,
                                           @RequestBody Todo todo) {
        todoService.update(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping("/users/{name}/todos")
    public ResponseEntity<?> add(@PathVariable String name,
                                 @Valid @RequestBody Todo todo) {
        Todo createdTodo = todoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), todo.isDone());

        if (createdTodo == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{name}/todos/{id}")
    public ResponseEntity<?> delete(@PathVariable String name,
                                    @PathVariable int id) {
        Todo deletedTodo = todoService.deleteById(id);

        if (deletedTodo != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/dummy-service")
    public Todo errorService() {
        throw new RuntimeException("Exception occurred");
    }
}
