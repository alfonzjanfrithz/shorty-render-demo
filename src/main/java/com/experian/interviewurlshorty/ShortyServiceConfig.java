package com.experian.interviewurlshorty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShortyServiceConfig {

    @Value("${shorty.service.type:map}")
    private String serviceType;

    private final ApplicationContext context;

    public ShortyServiceConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public ShortyService shortyService() {
        return context.getBean(serviceType, ShortyService.class);
    }
}