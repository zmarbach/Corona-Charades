package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class Game {
    private UUID uuid;
    private Team teamOne;
    private Team teamTwo;
    private List<String> words;

    public Game(UUID uuid, Team teamOne, Team teamTwo, List<String> words) {
        this.uuid = uuid;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.words = words;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(uuid, game.uuid) &&
                Objects.equals(teamOne, game.teamOne) &&
                Objects.equals(teamTwo, game.teamTwo) &&
                Objects.equals(words, game.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, teamOne, teamTwo, words);
    }
}
