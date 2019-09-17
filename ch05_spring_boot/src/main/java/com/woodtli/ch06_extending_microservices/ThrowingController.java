package com.woodtli.ch06_extending_microservices;

import com.woodtli.ch05_spring_boot.bean.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ThrowingController {

    @GetMapping(path = "/bla/dummy-service")
    public Todo throwingMethod() {
        throw new RuntimeException("Some exception occured");
    }
}
