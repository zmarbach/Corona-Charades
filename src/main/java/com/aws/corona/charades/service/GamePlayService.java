package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamePlayService {
    private static GameSingleton GAME = GameSingleton.getInstance();

    public void handleStartTurn(){
        GAME.setCurrentWord(GAME.getActiveWords().get(0));
    }

    public void handleCorrect() {
        String correctlyGuessedWord = GAME.getCurrentWord();
        int currentWordIndex = determineCurrentWordIndex();

        //if only one word left in list and was just guessed correctly, then set current word to "", check for this on front end
        if(GAME.getActiveWords().size() == 1){
            GAME.setCurrentWord("");
        }
        else {
            GAME.setCurrentWord(determineNextWord(currentWordIndex));
        }
        GAME.getActiveWords().remove(correctlyGuessedWord);
        GAME.getGuessedWords().add(correctlyGuessedWord);

        incrementCurrentTeamScore();
    }

    public void handleSkip() {
        int currentWordIndex = determineCurrentWordIndex();
        GAME.setCurrentWord(determineNextWord(currentWordIndex));
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

    private int determineCurrentWordIndex(){
        return GAME.getActiveWords().indexOf(GAME.getCurrentWord());
    }
    private String determineNextWord(int currentWordIndex) {
        if(currentElementIsLastElementInList(currentWordIndex, GAME.getActiveWords())){
            return GAME.getActiveWords().get(0);
        }
        else{
            return GAME.getActiveWords().get(currentWordIndex + 1);
        }
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

    public void handleEndGame() {
        GAME.setTeamOne(new Team("Team One", new ArrayList<>(), 0));
        GAME.setTeamTwo(new Team("Team One", new ArrayList<>(), 0));
        GAME.setActiveWords(new ArrayList<>());
        GAME.setGuessedWords(new ArrayList<>());
        GAME.setCurrentWord("SAMPLE WORD");
        GAME.setCurrentPlayer(new Player("DEREK JETER"));
        GAME.setCurrentTeam(new Team("", new ArrayList<>(), 0));
    }
}
