package com.aws.corona.charades.domain;

import java.util.List;

public class Team {
    private String name;
    private List<Player> players;
    private Integer score;

    public Team(String name, List<Player> players, Integer score) {
        this.name = name;
        this.players = players;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
