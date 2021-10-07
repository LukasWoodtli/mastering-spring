package com.woodtli.spring_boot.service.configuration_examples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SomeDataService {

    @Value("${somedataservice.url}")
    private String url;

    public String getUrl() {
        return url;
    }
}
