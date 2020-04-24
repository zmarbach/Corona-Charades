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
        GAME.setNewTurn(false);
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
        setCurrentTeamsPreviousPlayer(GAME.getCurrentPlayer());
        setNewCurrentPlayer();
        GAME.setNewTurn(true);
    }

    private void setNewCurrentPlayer() {
        Team currentTeam = GAME.getCurrentPlayer().getTeam();
        Team newTeam;
        //switch teams
        if(currentTeam.equals(GAME.getTeamOne())){
            newTeam = GAME.getTeamTwo();
        }
        else{
            newTeam = GAME.getTeamOne();
        }

        //set currentPlayer based on new team and previousPlayer
        int prevPlayerIndex = newTeam.getPlayers().indexOf(newTeam.getPreviousPlayer());
        if (currentElementIsLastElementInList(prevPlayerIndex, newTeam.getPlayers())) {
            GAME.setCurrentPlayer(newTeam.getPlayers().get(0));
        } else {
            GAME.setCurrentPlayer(newTeam.getPlayers().get(prevPlayerIndex + 1));
        }

//        if(currentTeam.equals(GAME.getTeamOne())){
//            //currentPlayer should be on Team Two
//            int indexOfTeamTwoPreviousPlayer = GAME.getTeamTwo().getPlayers().indexOf(GAME.getTeamTwoPreviousPlayer());
//            if(currentElementIsLastElementInList(indexOfTeamTwoPreviousPlayer, GAME.getTeamTwo().getPlayers())){
//                GAME.setCurrentPlayer(GAME.getTeamTwo().getPlayers().get(0));
//            }
//            else{
//                GAME.setCurrentPlayer(GAME.getTeamTwo().getPlayers().get(indexOfTeamTwoPreviousPlayer + 1));
//            }
//        }
//        else {
//            //otherwise currentPlayer should be Team One
//            int indexOfTeamOnePreviousPlayer = GAME.getTeamOne().getPlayers().indexOf(GAME.getTeamOnePreviousPlayer());
//            if (currentElementIsLastElementInList(indexOfTeamOnePreviousPlayer, GAME.getTeamOne().getPlayers())) {
//                GAME.setCurrentPlayer(GAME.getTeamOne().getPlayers().get(0));
//            } else {
//                GAME.setCurrentPlayer(GAME.getTeamOne().getPlayers().get(indexOfTeamOnePreviousPlayer + 1));
//            }
//        }
    }

    private void setCurrentTeamsPreviousPlayer(Player currentPlayer) {
        currentPlayer.getTeam().setPreviousPlayer(currentPlayer);
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
        GAME.setTeamOne(new Team("Team One", new ArrayList<>(), 0, new Player()));
        GAME.setTeamTwo(new Team("Team Two", new ArrayList<>(), 0, new Player()));
        GAME.setActiveWords(new ArrayList<>());
        GAME.setGuessedWords(new ArrayList<>());
        GAME.setCurrentWord("SAMPLE WORD");
        GAME.setCurrentPlayer(new Player("DEREK JETER", new Team("", new ArrayList<>(), 0, new Player())));
    }

    public void handleNextRound() {
        GAME.setActiveWords(GAME.getGuessedWords());
        GAME.setGuessedWords(new ArrayList<>());

        Collections.shuffle(GAME.getActiveWords());

        setNewCurrentPlayer();

        GAME.setNewTurn(true);
    }
}
