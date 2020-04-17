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
    private List<String> words;

    private GameSingleton() {
        this.teamOne = new Team("Team One", new ArrayList<>(), 0);
        this.teamTwo = new Team("Team One", new ArrayList<>(), 0);
        this.words = new ArrayList<>();
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

    public List<String> getWords() {
        return words;
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
