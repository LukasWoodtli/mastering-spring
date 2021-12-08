package com.example.spring.kotlin.kotlindemo.controller

import com.example.spring.kotlin.kotlindemo.beans.WelcomeBean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class BasicController {

    @GetMapping("/welcome")
    fun welcome() = "Hello World"

    @GetMapping("/welcome-with-object")
    fun welcomeWithObject() = WelcomeBean("Hello World")

    @GetMapping("/welcome-with-path-parameter/name/{name}")
    fun welcomeWithParameter(@PathVariable name: String) = WelcomeBean("Hello World, $name")
}