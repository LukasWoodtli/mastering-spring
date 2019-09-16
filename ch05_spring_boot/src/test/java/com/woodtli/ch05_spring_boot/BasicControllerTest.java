package com.woodtli.ch05_spring_boot;

import com.woodtli.ch05_spring_boot.controller.BasicController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.CoreMatchers.equalTo;


import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BasicController.class)
public class BasicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void welcome() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/welcome")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void welcomeWithObject() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/welcome-with-object")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void welcomeWithParameter() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/welcome-with-parameter/name/Luki")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(containsString("Hello World, Luki")));
    }
}
