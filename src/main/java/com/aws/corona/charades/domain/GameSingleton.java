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
    private Team currentTeam;

    private GameSingleton() {
        this.teamOne = new Team("Team One", new ArrayList<>(), 0);
        this.teamTwo = new Team("Team One", new ArrayList<>(), 0);
        this.activeWords = new ArrayList<>();
        this.guessedWords = new ArrayList<>();
        this.currentWord = "";
        this.currentPlayer = new Player("");
        this.currentTeam = new Team("", new ArrayList<>(), 0);
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

    public Team getTeamTwo() {
        return teamTwo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSingleton gameSingleton = (GameSingleton) o;
        return Objects.equals(teamOne, gameSingleton.teamOne) &&
                Objects.equals(teamTwo, gameSingleton.teamTwo) &&
                Objects.equals(activeWords, gameSingleton.activeWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamOne, teamTwo, activeWords);
    }

    public Team getCurrentTeam() {
        return null;
    }
}
