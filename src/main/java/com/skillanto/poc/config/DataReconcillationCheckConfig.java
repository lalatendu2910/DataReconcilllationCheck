package com.skillanto.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataReconcillationCheckConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
