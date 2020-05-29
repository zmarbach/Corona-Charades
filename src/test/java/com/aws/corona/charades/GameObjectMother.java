package com.aws.corona.charades;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class GameObjectMother {

    private TeamObjectMother teamObjectMother = new TeamObjectMother();
    private PlayerObjectMother playerObjectMother = new PlayerObjectMother();

    public Game buildGame(UUID uuid, Team teamOne, Team teamTwo,
                          List<String> activeWords, List<String> guessedWords,
                          String currentWord, Player currentPlayer, boolean newTurn){
        return new Game.GameBuilder()
                .withUUID(uuid)
                .withTeamOne(teamOne)
                .withTeamTwo(teamTwo)
                .withActiveWords(activeWords)
                .withGuessedWords(guessedWords)
                .withCurrentWord(currentWord)
                .withCurrentPlayer(currentPlayer)
                .isNewTurn(newTurn)
                .build();
    }

    public Game setUpGame(){
        Team team1 = teamObjectMother.createTeam("Team One", 0, new ArrayList<>(), new Player());
        Player team1Player1 = playerObjectMother.createPlayer("Andrew", team1);
        Player team1Player2 = playerObjectMother.createPlayer("Billy", team1);
        setPlayersAndPrevPlayer(team1, team1Player1, Arrays.asList(team1Player1, team1Player2));

        Team team2 = teamObjectMother.createTeam("Team Two", 0, new ArrayList<>(), new Player());
        Player team2Player1 = playerObjectMother.createPlayer("Chad", team2);
        Player team2Player2 = playerObjectMother.createPlayer("Derek", team2);
        setPlayersAndPrevPlayer(team2, team2Player1, Arrays.asList(team2Player1, team2Player2));

        List<String> activeWords = createActiveWords();
        return buildGame(UUID.randomUUID(), team1, team2, activeWords, new ArrayList<>(), "mockCurrentWord", team1.getPlayers().get(0), true );
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
