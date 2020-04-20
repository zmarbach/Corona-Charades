package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GamePlayService {
    private static GameSingleton GAME = GameSingleton.getInstance();
    private Random r = new Random();

    public void handleStartTurn(){
        List<String> activeWords = GAME.getActiveWords();
        String randomWord = activeWords.get(r.nextInt(activeWords.size()));
        GAME.setCurrentWord(randomWord);
        GAME.setBeginningOfNewTurn(false);
    }

    public void handleCorrect() {
        String correctlyGuessedWord = GAME.getCurrentWord();
        int currentWordIndex = determineCurrentWordIndex();

        //if more than one word left
        if(GAME.getActiveWords().size() > 1){
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
        setCurrentTeamsPreviousPlayer();
        setNewCurrentPlayer();
        GAME.setBeginningOfNewTurn(true);
    }

    private void setNewCurrentPlayer() {
        //current player is now prev player and current player
        //current team is now other team
        if(GAME.getCurrentPlayer().getTeam().equals(GAME.getTeamOne())){
            int indexOfTeamOnePreviousPlayer = GAME.getTeamOne().getPlayers().indexOf(GAME.getTeamOnePreviousPlayer());
            if(currentElementIsLastElementInList(indexOfTeamOnePreviousPlayer, GAME.getTeamOne().getPlayers())){
                GAME.setCurrentPlayer(GAME.getTeamOne().getPlayers().get(0));
            }
            else{
                GAME.setCurrentPlayer(GAME.getTeamOne().getPlayers().get(indexOfTeamOnePreviousPlayer + 1));
            }
        }
        else{
            int indexOfTeamTwoPreviousPlayer = GAME.getTeamTwo().getPlayers().indexOf(GAME.getTeamTwoPreviousPlayer());
            if(currentElementIsLastElementInList(indexOfTeamTwoPreviousPlayer, GAME.getTeamTwo().getPlayers())){
                GAME.setCurrentPlayer(GAME.getTeamTwo().getPlayers().get(0));
            }
            else{
                GAME.setCurrentPlayer(GAME.getTeamTwo().getPlayers().get(indexOfTeamTwoPreviousPlayer + 1));
            }
        }
    }

    private void setCurrentTeamsPreviousPlayer() {
        if(GAME.getCurrentPlayer().getTeam().equals(GAME.getTeamOne())){
            GAME.setTeamOnePreviousPlayer(GAME.getCurrentPlayer());
        }
        else{
            GAME.setTeamTwoPreviousPlayer(GAME.getCurrentPlayer());
        }
    }

    private void incrementCurrentTeamScore() {
        Team currentTeam = GAME.getCurrentPlayer().getTeam();
        currentTeam.setScore(currentTeam.getScore() + 1);
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

    public void handleEndGame() {
        GAME.setTeamOne(new Team("Team One", new ArrayList<>(), 0));
        GAME.setTeamTwo(new Team("Team Two", new ArrayList<>(), 0));
        GAME.setActiveWords(new ArrayList<>());
        GAME.setGuessedWords(new ArrayList<>());
        GAME.setCurrentWord("SAMPLE WORD");
        GAME.setCurrentPlayer(new Player("DEREK JETER", new Team("", new ArrayList<>(), 0)));
        GAME.setTeamOnePreviousPlayer(new Player("Team One default previous player", new Team("", new ArrayList<>(), 0)));
        GAME.setTeamTwoPreviousPlayer(new Player("Team Two default previous player", new Team("", new ArrayList<>(), 0)));
    }

    public void handleNextRound() {
        GAME.setActiveWords(GAME.getGuessedWords());
        GAME.setGuessedWords(new ArrayList<>());

        Collections.shuffle(GAME.getActiveWords());

        setNewCurrentPlayer();
    }
}
