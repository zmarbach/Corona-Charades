package com.aws.corona.charades.configuration;

import com.aws.corona.charades.handler.GameSetUpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.aws.corona.charades.controller.GameSetUpController;

@Configuration
@ComponentScan({ "com.aws.corona.charades.configuration" })
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Value("${HelloWorld.SiteName}")
    private String siteName;
    @Autowired
    private GameSetUpHandler gameSetUpHandler;

    @Bean
    public GameSetUpController helloWorld() {
        return new GameSetUpController(this.siteName, gameSetUpHandler);
    }

    /**
     * Required to inject properties using the 'Value' annotation.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
