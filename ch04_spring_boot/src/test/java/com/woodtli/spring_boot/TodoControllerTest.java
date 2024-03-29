package com.woodtli.spring_boot;

import com.woodtli.spring_boot.bean.Todo;
import com.woodtli.spring_boot.controller.TodoController;
import com.woodtli.spring_boot.service.TodoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    private static final int CREATED_TODO_ID = 4;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private TodoService service;

    @Test
    public void retrieveTodos() throws Exception {
        List<Todo> mockList = Arrays.asList(new Todo(1, "Jack",
                "Learn Spring MVC", new Date(), false), new Todo(2, "Jack",
                "Learn Struts", new Date(), false));

        when(service.retrieveTodos(anyString())).thenReturn(mockList);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/users/Jack/todos").
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andReturn();

        String expected = "["
                + "{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}" + ","
                + "{id:2,user:Jack,desc:\"Learn Struts\",done:false}" + "]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void retrieveTodo() throws Exception {
        Todo mockTodo = new Todo(1, "Jack", "Learn Spring MVC",
                new Date(), false);
        when(service.retrieveTodo(anyInt())).thenReturn(mockTodo);
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/users/Jack/todos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String expected = "{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}";
        JSONAssert.assertEquals(expected,
                result.getResponse().getContentAsString(), false);
    }


    @Test
    public void createTodo() throws Exception {
        Todo mockTodo = new Todo(CREATED_TODO_ID, "Jack",
                "Learn Spring MVC", new Date(), false);
        String todo = "{\"user\":\"Jack\",\"desc\":\"Learn Spring MVC\",\"done\":\"false\"}";

        when(service.addTodo(anyString(), anyString(), isNull(), anyBoolean()))
                .thenReturn(mockTodo);

        mvc.perform(MockMvcRequestBuilders.post("/users/Jack/todos").
                content(todo).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated()).andExpect(header().
                string("location", containsString("/users/Jack/todos/"
                                                + CREATED_TODO_ID)));
    }

    @Test
    public void setCreatedTodo_withValidationError() throws Exception {
        Todo mockTodo = new Todo(CREATED_TODO_ID, "Jack", "Learn Spring Boot", new Date(), false);

        String todo = "\"{\"user\":\"Jack\",\"desc\":\"Learn\",\"done\":false}";

        when(service.addTodo(anyString(), anyString(), isNull(), anyBoolean())).thenReturn(mockTodo);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/users/Jack/todos").content(todo).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
    }
}
