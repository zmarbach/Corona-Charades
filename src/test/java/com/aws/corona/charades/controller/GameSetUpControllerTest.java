package com.aws.corona.charades.controller;

import com.aws.corona.charades.service.GameSetUpService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for GameController")
public class GameSetUpControllerTest {

    private static final String EXPECTED_SITE_NAME = "home";
    private static final String EXPECTED_VIEW_NAME = "index";
    @Autowired
    private GameSetUpService gameSetUpService;

    private GameSetUpController gameSetUpController = new GameSetUpController(EXPECTED_SITE_NAME, gameSetUpService);

    @BeforeAll
    static void setup() {
        // Use as needed.
    }

    @AfterAll
    static void tearDown() {
        // Use as needed.
    }

    @Test
    @DisplayName("Basic test for controller")
    void testHelloWorld() {
        ModelAndView actualModelAndView = gameSetUpController.test();

        // Verify the result obtained matches the values we expect.
        assertEquals(EXPECTED_VIEW_NAME, actualModelAndView.getViewName());
        assertEquals(EXPECTED_SITE_NAME, String.valueOf(actualModelAndView.getModel().get("siteName")));
    }
}
