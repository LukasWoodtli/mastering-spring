package com.woodtli.spring_data_jpa;

import com.woodtli.spring_data_jpa.model.Todo;
import com.woodtli.spring_data_jpa.repositories.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void check_todo_count() {
        assertEquals(3, todoRepository.count());
    }

    @Test
    public void findOne() {
        Optional<Todo> todo = todoRepository.findById(101L);
        assertEquals("Todo Desc 1", todo.get().getDescription());
    }

    @Test
    public void exists() {
        assertTrue(todoRepository.existsById(101L));
        assertFalse(todoRepository.existsById(105L));
    }

    @Test
    public void delete() {
        todoRepository.deleteById(101L);
        assertEquals(2, todoRepository.count());
    }

    @Test
    public void deleteAll() {
        todoRepository.deleteAll();
        assertEquals(0, todoRepository.count());
    }

    @Test
    public void save() {
        Todo todo = todoRepository.findById(101L).get();
        todo.setDescription("Todo Desc Updated");
        todoRepository.save(todo);

        entityManager.flush();

        Todo updatedTodo = todoRepository.findById(101L).get();

        assertEquals("Todo Desc Updated", updatedTodo.getDescription());

    }
}
