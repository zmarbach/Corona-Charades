package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameContext {
    private String uuid;
    private Team teamOne;
    private Team teamTwo;
    private List<String> charadeWords;

    public GameContext(String uuid, Team teamOne, Team teamTwo, List<String> charadeWords) {
        this.uuid = uuid;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.charadeWords = charadeWords;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
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

    public List<String> getCharadeWords() {
        return charadeWords;
    }

    public void setCharadeWords(List<String> charadeWords) {
        this.charadeWords = charadeWords;
    }
}
