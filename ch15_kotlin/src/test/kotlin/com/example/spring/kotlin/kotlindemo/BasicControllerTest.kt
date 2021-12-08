package com.example.spring.kotlin.kotlindemo

import com.example.spring.kotlin.kotlindemo.controller.BasicController
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@ExtendWith(SpringExtension::class)
@WebMvcTest(BasicController::class)
class BasicControllerTest {

    @Autowired
    lateinit var mvc: MockMvc;

    @Test
    fun `GET welcome returns "Hello World"`() {
        mvc.perform(
            MockMvcRequestBuilders.get("/welcome").accept(
                MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(equalTo("Hello World")));
    }

    @Test
    fun `GET welcome-with-object returns "Hello World"`() {
        mvc.perform(
            MockMvcRequestBuilders.get("/welcome-with-object").accept(
                MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(containsString("Hello World")));
    }

    @Test
    fun `GET welcome-with-path-parameter returns "Hello World, Buddy"`() {
        mvc.perform(
            MockMvcRequestBuilders.get("/welcome-with-path-parameter/name/Buddy").accept(
                MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(containsString("Hello World, Buddy")));
    }


}