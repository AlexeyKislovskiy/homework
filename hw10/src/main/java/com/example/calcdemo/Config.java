package com.example.calcdemo;

import org.example.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class Config {

    @RequestScope
    @Bean
    public Calculator calculator() {
        return new Calculator();
    }
}
