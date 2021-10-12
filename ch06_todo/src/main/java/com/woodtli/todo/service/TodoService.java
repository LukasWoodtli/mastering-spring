package com.woodtli.todo.service;

import com.woodtli.todo.bean.Todo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "Jack", "Mastering Spring 5", new Date(), false));
        todos.add(new Todo(2, "Julia", "Data Science", new Date(), false));
        todos.add(new Todo(2, "Romeo", "Love is Everything", new Date(), false));
    }


    // consider using JSR-107 for caching
    @Cacheable(cacheNames = "todos", condition = "#user.length < 10")
    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user))
                filteredTodos.add(todo);
        }
        return filteredTodos;
    }

    public Todo addTodo(String name, String desc, Date targetDate, boolean isDone) {
        Todo todo = new Todo(++todoCount, name, desc, targetDate, isDone);
        todos.add(todo);
        return todo;
    }

    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id)
                return todo;
        }
        return null;
    }

    public Todo update(Todo todo) {
        Todo deletedTodo = deleteById(todo.getId());
        if (deletedTodo == null) {
            throw new RuntimeException("Todo not found");
        }
        todos.add(todo);
        return todo;
    }

    public Todo deleteById(int id) {
        Todo todo = retrieveTodo(id);

        if (todo == null)
            throw new RuntimeException("Todo not found");

        if (todos.remove(todo)) {
            return todo;
        }

        throw new RuntimeException("Delete Unsuccessful");
    }
}
