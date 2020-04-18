package com.aws.corona.charades.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.aws.corona.charades.controller.GameController;

@Configuration
@ComponentScan({ "com.aws.corona.charades.configuration" })
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public GameController helloWorld() {
        return new GameController();
    }

}
