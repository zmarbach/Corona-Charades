package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) &&
                Objects.equals(players, team.players) &&
                Objects.equals(score, team.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players, score);
    }
}
