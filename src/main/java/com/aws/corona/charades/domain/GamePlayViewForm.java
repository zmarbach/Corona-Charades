package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GamePlayViewForm {
    private String currentWord;
    private Player currentPlayer;
    private Integer teamOneScore;
    private Integer teamTwoScore;
    private List<String> activeWords;
    private boolean isBeginningOfNewTurn;

    public GamePlayViewForm() {
    }

    public GamePlayViewForm(String currentWord, Player currentPlayer, Integer teamOneScore, Integer teamTwoScore, List<String> activeWords, boolean isBeginningOfNewTurn) {
        this.currentWord = currentWord;
        this.currentPlayer = currentPlayer;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
        this.activeWords = activeWords;
        this.isBeginningOfNewTurn = isBeginningOfNewTurn;
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

    public Integer getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(Integer teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public Integer getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(Integer teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public List<String> getActiveWords() {
        return activeWords;
    }

    public void setActiveWords(List<String> activeWords) {
        this.activeWords = activeWords;
    }

    public boolean isBeginningOfNewTurn() {
        return isBeginningOfNewTurn;
    }

    public void setBeginningOfNewTurn(boolean beginningOfNewTurn) {
        isBeginningOfNewTurn = beginningOfNewTurn;
    }
}
