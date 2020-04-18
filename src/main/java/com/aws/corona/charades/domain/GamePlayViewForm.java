package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

@Component
public class GamePlayViewForm {
    private String currentWord;
    private Player currentPlayer;
    private Team currentTeam;
    private Integer teamOneScore;
    private Integer teamTeamTwoScore;

    public GamePlayViewForm() {
    }

    public GamePlayViewForm(String currentWord, Player currentPlayer, Team currentTeam, Integer teamOneScore, Integer teamTeamTwoScore) {
        this.currentWord = currentWord;
        this.currentPlayer = currentPlayer;
        this.currentTeam = currentTeam;
        this.teamOneScore = teamOneScore;
        this.teamTeamTwoScore = teamTeamTwoScore;
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

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public Integer getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(Integer teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public Integer getTeamTeamTwoScore() {
        return teamTeamTwoScore;
    }

    public void setTeamTeamTwoScore(Integer teamTeamTwoScore) {
        this.teamTeamTwoScore = teamTeamTwoScore;
    }
}
