package com.aws.corona.charades;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GameObjectMother {

    public Game createGame(UUID uuid, Team teamOne, Team teamTwo,
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
}
