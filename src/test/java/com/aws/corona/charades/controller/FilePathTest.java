package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.TeamPlayerNumbers;
import com.aws.corona.charades.service.GameSetUpService;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

public class FilePathTest {
    private static final GameSingleton GAME = GameSingleton.getInstance();

    @Test
    public void logAbsoluteFilePathOfWordsTxtToConsole(){
        File file = new File("words.txt");
        System.out.println(file.getAbsolutePath());
        assertEquals(1,1);
    }

    @Test
    public void gameShouldHave30WordsIf6TotalPlayers(){
        GameSetUpService gameSetUpService = new GameSetUpService();
        gameSetUpService.addWordsToGame(new TeamPlayerNumbers(3,3));
        assertEquals(30, GAME.getActiveWords().size());
    }

    @Test
    public void gameShouldHave50WordsIf10TotalPlayers(){
        GameSetUpService gameSetUpService = new GameSetUpService();
        gameSetUpService.addWordsToGame(new TeamPlayerNumbers(5,5));
        assertEquals(50, GAME.getActiveWords().size());
    }

    @Test
    public void gameShouldHave100WordsIf20TotalPlayers(){
        GameSetUpService gameSetUpService = new GameSetUpService();
        gameSetUpService.addWordsToGame(new TeamPlayerNumbers(10,10));
        assertEquals(100, GAME.getActiveWords().size());
    }
}
