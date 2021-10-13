package com.woodtli.todo;

import com.woodtli.todo.bean.Todo;
import com.woodtli.todo.controller.TodoController;
import com.woodtli.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void retrieveTodos() throws Exception {
        List<Todo> mockList = Arrays.asList(
                new Todo(1, "Jack", "Learn Spring MVC", new Date(), false),
                new Todo(2, "Jack", "Learn Struts", new Date(), false));

        when(todoService.retrieveTodos(anyString())).thenReturn(mockList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/Jack/todos")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String expected = "[" + "{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}" + "," +
                "{id:2,user:Jack,desc:\"Learn Struts\",done:false}" + "]";

        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    void retrieveTodo() throws Exception {
        Todo mockTodo =
                new Todo(1, "Jack", "Learn Spring MVC", new Date(), false);

        when(todoService.retrieveTodo(anyInt())).thenReturn(mockTodo);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/Jack/todos/1")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String expected ="{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}";

        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    void createTodo() throws Exception {
        final int CREATED_TODO_ID = 4;
        Todo mockTodo =
                new Todo(CREATED_TODO_ID, "Jack", "Learn Spring MVC", new Date(), false);
        String todo = "{\"user\":\"Jack\",\"desc\":\"Learn Spring MVC\",\"done\":\"false\"}";

        when(todoService.addTodo(anyString(), anyString(), isNull(), anyBoolean())).thenReturn(mockTodo);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users/Jack/todos")
                        .content(todo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("location",
                                           containsString("/users/Jack/todos/" + CREATED_TODO_ID)))
                .andReturn();
    }

    @Test
    void createTodo_with_validation_error() throws Exception {
        final int CREATED_TODO_ID = 4;
        Todo mockTodo =
                new Todo(CREATED_TODO_ID, "Jack", "Learn", new Date(), false);
        String todo = "{\"user\":\"Jack\",\"desc\":\"Learn\",\"done\":\"false\"}";

        when(todoService.addTodo(anyString(), anyString(), isNull(), anyBoolean())).thenReturn(mockTodo);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users/Jack/todos")
                        .content(todo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void updateTodo() throws Exception {
        final int CREATED_TODO_ID = 4;
        Todo mockTodo =
                new Todo(CREATED_TODO_ID, "Jack", "Learn Spring MVC", new Date(), false);
        String todo = "{\"user\":\"Jack\",\"desc\":\"Learn Spring MVC\",\"targetDate\":null,\"done\":false}";

        when(todoService.update(mockTodo)).thenReturn(mockTodo);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/users/Jack/todos/" + CREATED_TODO_ID)
                        .content(todo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(todo,
                responseBody,
                false);
    }

    @Test
    void deleteTodo() throws Exception {
        Todo mockTodo =
                new Todo(1, "Jack", "Learn Spring MVC", new Date(), false);
        String todo = "{\"user\":\"Jack\",\"desc\":\"Learn Spring MVC\",\"targetDate\":null,\"done\":false}";

        when(todoService.deleteById(anyInt())).thenReturn(mockTodo);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/Jack/todos/" + mockTodo.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
