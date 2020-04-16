package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

@Component
public class TeamPlayerNumbers {

    private Integer numPlayersTeamOne = 0;
    private Integer numPlayersTeamTwo = 0;

    public TeamPlayerNumbers(Integer numPlayersTeamOne, Integer numPlayersTeamTwo) {
        this.numPlayersTeamOne = numPlayersTeamOne;
        this.numPlayersTeamTwo = numPlayersTeamTwo;
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
}
