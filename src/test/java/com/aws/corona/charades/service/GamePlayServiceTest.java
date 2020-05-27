package com.aws.corona.charades.service;

import com.aws.corona.charades.GameObjectMother;
import com.aws.corona.charades.PlayerObjectMother;
import com.aws.corona.charades.TeamObjectMother;
import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import com.aws.corona.charades.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GamePlayServiceTest {

    private GamePlayService testObj = new GamePlayService(new GameService(new GameRepository(new HashMap<>())));
    private GameObjectMother gameObjectMother = new GameObjectMother();
    private TeamObjectMother teamObjectMother = new TeamObjectMother();
    private PlayerObjectMother playerObjectMother = new PlayerObjectMother();
    private Game game;

    @BeforeEach
    void setUp() {
        Team team1 = teamObjectMother.createTeam("Team One", 0, new ArrayList<>(), null);
        Player team1Player1 = playerObjectMother.createPlayer("Andrew", team1);
        Player team1Player2 = playerObjectMother.createPlayer("Billy", team1);
        setPlayersAndPrevPlayer(team1, team1Player1, Arrays.asList(team1Player1, team1Player2));

        Team team2 = teamObjectMother.createTeam("Team Two", 0, new ArrayList<>(), null);
        Player team2Player1 = playerObjectMother.createPlayer("Chad", team2);
        Player team2Player2 = playerObjectMother.createPlayer("Derek", team2);
        setPlayersAndPrevPlayer(team2, team2Player1, Arrays.asList(team2Player1, team2Player2));


        List<String> activeWords = createActiveWords();
        game = gameObjectMother.createGame(UUID.randomUUID(), team1, team2, activeWords, new ArrayList<>(), "mockCurrentWord", team1.getPlayers().get(0), true );
    }

    @Test
    void handleStartTurnShouldRandomlySetCurrentWordInGame() {
        //arrange...handled in set up

        //act
            testObj.handleStartTurn(game);
        //assert
            assertNotNull(game.getCurrentWord());
            assertNotEquals("mockCurrentWord", game.getCurrentWord());
    }

    @Test
    void handleStartTurnShouldMakeNewTurnFalse() {
        testObj.handleStartTurn(game);
        assertFalse(game.isNewTurn());
    }

    @Test
    void handleCorrectShouldRemoveWordFromActiveWordsAndAddItToGuessedWords() {
        game.setCurrentWord(game.getActiveWords().get(0));

        testObj.handleCorrect(game);

        assertTrue(!game.getActiveWords().contains("alpha"));
        assertTrue(game.getGuessedWords().contains("alpha"));
    }

    @Test
    void handleCorrectShouldIncrementTheCurrentTeamScore() {
        Integer initialScore = game.getTeamOne().getScore();
        Integer team2ScoreBefore = game.getTeamTwo().getScore();

        testObj.handleCorrect(game);
        Integer incrementedScore = game.getTeamOne().getScore();
        Integer team2ScoreAfter = game.getTeamTwo().getScore();

        assertEquals(initialScore += 1, incrementedScore);
        assertEquals(team2ScoreBefore, team2ScoreAfter);
    }

    @Test
    void handleCorrectShouldMakeNextWordInActiveWordsTheCurrentWordInGame() {
        List<String> activeWords = new ArrayList<>();
        activeWords.add("foo");
        activeWords.add("bar");
        game.setActiveWords(activeWords);
        game.setCurrentWord(game.getActiveWords().get(0));

        testObj.handleCorrect(game);

        assertEquals("bar", game.getCurrentWord());
    }

    @Test
    void handleCorrectShouldNOTSetCurrentWordIfOnlyOneWordLeft() {
        List<String> activeWords = new ArrayList<>();
        activeWords.add("test");
        game.setActiveWords(activeWords);
        game.setCurrentWord(game.getActiveWords().get(0));
        String currentWordBefore = game.getActiveWords().get(0);

        testObj.handleCorrect(game);

        assertEquals(currentWordBefore, game.getCurrentWord());
    }

    @Test
    void handleSkip() {
    }


    @Test
    void handleEndGame() {
    }

    @Test
    void handleNextRound() {
    }

    @Test
    void handleNextPlayer() {
    }

    private List<String> createActiveWords() {
        List<String> activeWords = new ArrayList<>();
        activeWords.addAll(Arrays.asList("alpha", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel", "india", "juliette"));
        return activeWords;
    }

    private void setPlayersAndPrevPlayer(Team team, Player prevPlayer, List<Player> players) {
        for (Player player : players) {
            team.getPlayers().add(player);
        }

        team.setPreviousPlayer(prevPlayer);
    }
}