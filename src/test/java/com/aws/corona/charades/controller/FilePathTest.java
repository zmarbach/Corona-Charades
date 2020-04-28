package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.CategoryMap;
import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.TeamsViewForm;
import com.aws.corona.charades.service.GameSetUpService;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

public class FilePathTest {
    private static final GameSingleton GAME = GameSingleton.getInstance();

    @Test
    public void logAbsoluteFilePathOfWordsTxtToConsole(){
        File file = new File("general.txt");
        System.out.println(file.getAbsolutePath());
        assertEquals(1,1);
    }

    @Test
    public void gameShouldHave30WordsIf6TotalPlayersAnd5WordsPerPlayer(){
        GameSetUpService gameSetUpService = new GameSetUpService();
        gameSetUpService.addWordsToGame(new TeamsViewForm(3,3, 5, ""));
        assertEquals(30, GAME.getActiveWords().size());
    }

    @Test
    public void gameShouldHave100WordsIf10TotalPlayersAnd10WordsPerPlayer(){
        GameSetUpService gameSetUpService = new GameSetUpService();
        gameSetUpService.addWordsToGame(new TeamsViewForm(5,5, 10, ""));
        assertEquals(100, GAME.getActiveWords().size());
    }
}
