package com.aws.corona.charades.configuration;

import com.aws.corona.charades.domain.CategoryMap;
import com.aws.corona.charades.service.GamePlayService;
import com.aws.corona.charades.service.GameSetUpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.aws.corona.charades.controller.GameController;

import java.util.HashMap;
import java.util.Random;

@Configuration
@ComponentScan({ "com.aws.corona.charades.configuration" })
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public GameController createGameController() {
        return new GameController(createGameSetUpService(createCategoryMap(), new Random()), createGamePlayService());
    }

    @Bean
    public GamePlayService createGamePlayService() {
        return new GamePlayService();
    }

    @Bean
    public GameSetUpService createGameSetUpService(CategoryMap categoryMap, Random random){
        return new GameSetUpService(categoryMap, random);
    }

    @Bean
    public CategoryMap createCategoryMap() {
        return new CategoryMap(new HashMap<>());
    }

}
