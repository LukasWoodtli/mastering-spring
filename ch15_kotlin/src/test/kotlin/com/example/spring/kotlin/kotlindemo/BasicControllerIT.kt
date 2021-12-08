package com.example.spring.kotlin.kotlindemo

import com.example.spring.kotlin.kotlindemo.beans.WelcomeBean
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasicControllerIT {

    @Autowired
    lateinit var restTemplate: TestRestTemplate;

    @Test
    fun `GET welcome returns "Hello World`() {
        val body = restTemplate.getForObject("/welcome", String::class.java)
        assertThat(body, equalTo("Hello World"))
    }

    @Test
    fun `GET welcome-with-object returns "Hello World`() {
        val body = restTemplate.getForObject("/welcome-with-object", WelcomeBean::class.java)
        assertThat(body.message, containsString("Hello World"))
    }


    @Test
    fun `GET welcome-with-path-parameter returns "Hello World`() {
        val body = restTemplate.getForObject("/welcome-with-path-parameter/name/Buddy", WelcomeBean::class.java)
        assertThat(body.message, containsString("Hello World, Buddy"))
    }
}