package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

@Component
public class TeamsViewForm {

    private Integer numPlayersTeamOne;
    private Integer numPlayersTeamTwo;
    private Integer numWordsPerPlayer;

    public TeamsViewForm(Integer numPlayersTeamOne, Integer numPlayersTeamTwo, Integer numWordsPerPlayer) {
        this.numPlayersTeamOne = numPlayersTeamOne;
        this.numPlayersTeamTwo = numPlayersTeamTwo;
        this.numWordsPerPlayer = numWordsPerPlayer;
    }

    public Integer getNumPlayersTeamOne() {
        return numPlayersTeamOne;
    }

    public void setNumPlayersTeamOne(Integer numPlayersTeamOne) {
        this.numPlayersTeamOne = numPlayersTeamOne;
    }

    public Integer getNumPlayersTeamTwo() {
        return numPlayersTeamTwo;
    }

    public void setNumPlayersTeamTwo(Integer numPlayersTeamTwo) {
        this.numPlayersTeamTwo = numPlayersTeamTwo;
    }

    public Integer getNumWordsPerPlayer() {
        return numWordsPerPlayer;
    }

    public void setNumWordsPerPlayer(Integer numWordsPerPlayer) {
        this.numWordsPerPlayer = numWordsPerPlayer;
    }
}
