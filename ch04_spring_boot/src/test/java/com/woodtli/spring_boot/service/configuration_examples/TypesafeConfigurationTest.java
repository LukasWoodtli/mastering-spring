package com.woodtli.spring_boot.service.configuration_examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TypesafeConfigurationTest {

    @Autowired
    private TypesafeConfiguration configuration;

    @Test
    void testEnableSwitchForService1() {
        assertTrue(configuration.isEnableSwitchForService1());
    }

    @Test
    void testService1Url() {
        assertEquals("http://abc-dev.service.com/somethingelse", configuration.getService1Url());
    }

    @Test
    void testService1Timeout() {
        assertEquals(250, configuration.getService1Timeout());
    }
}