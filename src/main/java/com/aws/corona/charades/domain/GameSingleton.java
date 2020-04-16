package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class GameSingleton {
    private static GameSingleton INSTANCE;
    private Team teamOne;
    private Team teamTwo;
    private List<String> words;

    private GameSingleton(Team teamOne, Team teamTwo, List<String> words) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.words = words;
    }

    public static synchronized GameSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameSingleton(
                    new Team("Team One", Collections.<Player>emptyList(), 0),
                    new Team("Team Two", Collections.<Player>emptyList(), 0),
                    Collections.<String>emptyList());
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
        GameSingleton gameSingleton = (GameSingleton) o;
        return Objects.equals(teamOne, gameSingleton.teamOne) &&
                Objects.equals(teamTwo, gameSingleton.teamTwo) &&
                Objects.equals(words, gameSingleton.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamOne, teamTwo, words);
    }
}
