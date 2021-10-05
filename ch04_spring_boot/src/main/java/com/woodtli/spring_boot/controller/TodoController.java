package com.woodtli.spring_boot.controller;

import com.woodtli.spring_boot.bean.Todo;
import com.woodtli.spring_boot.service.TodoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

	@ApiOperation(
			value = "Retrieve all todos for a user by passing in his name",
			notes = "A list of matching todos is returned.",
			response = Todo.class,
			responseContainer = "List",
			produces = "application/json")
	@GetMapping("/users/{name}/todos")
	public List<Todo> retrieveTodos(@PathVariable String name) {
		return todoService.retrieveTodos(name);
	}

	@GetMapping(path = "/users/{name}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String name, @PathVariable int id) {
		return todoService.retrieveTodo(id);
	}

	@PostMapping("/users/{name}/todos")
	ResponseEntity<?> add(@PathVariable String name, @Valid @RequestBody Todo todo) {
		Todo createdTodo = todoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), todo.isDone());
		if (createdTodo == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}