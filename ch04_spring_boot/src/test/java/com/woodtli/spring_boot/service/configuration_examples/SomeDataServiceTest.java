package com.woodtli.spring_boot.service.configuration_examples;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SomeDataServiceTest {

    @Autowired
    private SomeDataService service;

    @Test
    void testCustomConfig() {
        assertEquals(service.getUrl(), "http://example.com/something");
    }
}