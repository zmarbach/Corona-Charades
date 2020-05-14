package com.aws.corona.charades.configuration;

import com.aws.corona.charades.domain.CategoryMap;
import com.aws.corona.charades.repositories.GameRepository;
import com.aws.corona.charades.service.GameService;
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
        return new GameController(createGameSetUpService(createCategoryMap(), new Random()), createGamePlayService(), createGameService());
    }

    @Bean
    public GamePlayService createGamePlayService() {
        return new GamePlayService(createGameService());
    }

    @Bean
    public GameSetUpService createGameSetUpService(CategoryMap categoryMap, Random random){
        return new GameSetUpService(categoryMap, random, createGameService());
    }

    @Bean
    public CategoryMap createCategoryMap() {
        return new CategoryMap(new HashMap<>());
    }

    @Bean
    public GameService createGameService(){
        return new GameService(new GameRepository(new HashMap<>()));
    }

}
