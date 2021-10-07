package com.woodtli.spring_boot.service.configuration_examples;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
public class TypesafeConfiguration {

    private boolean enableSwitchForService1;
    private String service1Url;
    private int serviceTimeout;

    public boolean isEnableSwitchForService1() {
        return enableSwitchForService1;
    }

    public void setEnableSwitchForService1(boolean enableSwitchForService1) {
        this.enableSwitchForService1 = enableSwitchForService1;
    }

    public String getService1Url() {
        return service1Url;
    }

    public void setService1Url(String service1Url) {
        this.service1Url = service1Url;
    }

    public int getService1Timeout() {
        return serviceTimeout;
    }

    public void setService1Timeout(int serviceTimeout) {
        this.serviceTimeout = serviceTimeout;
    }
}
