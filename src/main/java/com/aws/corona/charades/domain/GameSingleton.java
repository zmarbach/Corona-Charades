package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class GameSingleton {
    private static GameSingleton INSTANCE;
    private Team teamOne;
    private Team teamTwo;
    private List<String> activeWords;
    private List<String> guessedWords;
    private String currentWord;
    private Player currentPlayer;
    private Player teamOnePreviousPlayer;
    private Player teamTwoPreviousPlayer;
    private boolean isBeginningOfNewTurn;

    private GameSingleton() {
        this.teamOne = new Team("Team One", new ArrayList<>(), 0);
        this.teamTwo = new Team("Team Two", new ArrayList<>(), 0);
        this.activeWords = new ArrayList<>();
        this.guessedWords = new ArrayList<>();
        this.currentWord = "Click Start Turn to begin!";
        this.currentPlayer = new Player("DEREK JETER", new Team("", new ArrayList<>(), 0));
        this.isBeginningOfNewTurn = true;
    }

    public static synchronized GameSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameSingleton();
        }
        return INSTANCE;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public List<String> getActiveWords() {
        return activeWords;
    }

    public void setActiveWords(List<String> activeWords) {
        this.activeWords = activeWords;
    }

    public List<String> getGuessedWords() {
        return guessedWords;
    }

    public void setGuessedWords(List<String> guessedWords) {
        this.guessedWords = guessedWords;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getTeamOnePreviousPlayer() {
        return teamOnePreviousPlayer;
    }

    public void setTeamOnePreviousPlayer(Player teamOnePreviousPlayer) {
        this.teamOnePreviousPlayer = teamOnePreviousPlayer;
    }

    public Player getTeamTwoPreviousPlayer() {
        return teamTwoPreviousPlayer;
    }

    public void setTeamTwoPreviousPlayer(Player teamTwoPreviousPlayer) {
        this.teamTwoPreviousPlayer = teamTwoPreviousPlayer;
    }

    public boolean isBeginningOfNewTurn() {
        return isBeginningOfNewTurn;
    }

    public void setBeginningOfNewTurn(boolean beginningOfNewTurn) {
        isBeginningOfNewTurn = beginningOfNewTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSingleton that = (GameSingleton) o;
        return Objects.equals(teamOne, that.teamOne) &&
                Objects.equals(teamTwo, that.teamTwo) &&
                Objects.equals(activeWords, that.activeWords) &&
                Objects.equals(guessedWords, that.guessedWords) &&
                Objects.equals(currentWord, that.currentWord) &&
                Objects.equals(currentPlayer, that.currentPlayer) &&
                Objects.equals(teamOnePreviousPlayer, that.teamOnePreviousPlayer) &&
                Objects.equals(teamTwoPreviousPlayer, that.teamTwoPreviousPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamOne, teamTwo, activeWords, guessedWords, currentWord, currentPlayer, teamOnePreviousPlayer, teamTwoPreviousPlayer);
    }
}
