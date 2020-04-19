package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamePlayService {
    private final static GameSingleton GAME = GameSingleton.getInstance();

    public void handleStartTurn(){
        GAME.setCurrentWord(GAME.getActiveWords().get(0));
    }

    public void handleCorrect() {
        incrementCurrentTeamScore();
        GAME.getActiveWords().remove(GAME.getCurrentWord());
        GAME.getGuessedWords().add(GAME.getCurrentWord());
        if (GAME.getActiveWords().isEmpty()){
           GAME.setCurrentWord("");
        }
        else{
            GAME.setCurrentWord(GAME.getActiveWords().get(0));
        }
    }

    public void handleSkip() {
        int currentWordIndex = GAME.getActiveWords().indexOf(GAME.getCurrentWord());

        if(currentElementIsLastElementInList(currentWordIndex, GAME.getActiveWords())){
            GAME.setCurrentWord(GAME.getActiveWords().get(0));
        }
        else{
            GAME.setCurrentWord(GAME.getActiveWords().get(currentWordIndex + 1));
        }
    }

    public void handleNextPlayer() {
        //TODO - what to do if uneven players on each team???
        //TODO - need to think about best way to do this...maybe store teamOnePreviousPlayer and teamTwoPreviousPlayer in GAME
        // , so can just refer back to that when determining next player
        // change the current team and then just determine currentPlayer by just incrementing based on previousPlayerIndex
        int currentPlayerIndex = GAME.getCurrentTeam().getPlayers().indexOf(GAME.getCurrentPlayer());

        changeCurrentTeam();

        if(currentElementIsLastElementInList(currentPlayerIndex, GAME.getCurrentTeam().getPlayers())){
            GAME.setCurrentPlayer(GAME.getCurrentTeam().getPlayers().get(0));
        }
        else{
            GAME.setCurrentPlayer(GAME.getCurrentTeam().getPlayers().get(currentPlayerIndex));
        }
    }

    private void incrementCurrentTeamScore() {
        GAME.getCurrentTeam().setScore(GAME.getCurrentTeam().getScore() + 1);
    }

    public boolean currentElementIsLastElementInList(int elementIndex, List list){
        return elementIndex == list.size() - 1;
    }

    private void changeCurrentTeam() {
        if(GAME.getCurrentTeam().equals(GAME.getTeamOne())){
            GAME.setCurrentTeam(GAME.getTeamTwo());
        }
        else{
            GAME.setCurrentTeam(GAME.getTeamOne());
        }
    }

}
