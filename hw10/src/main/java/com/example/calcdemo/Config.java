package com.example.calcdemo;

import org.example.BasicCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class Config {

    @RequestScope
    @Bean
    public BasicCalculator calculator() {
        return new BasicCalculator();
    }
}
