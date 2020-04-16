package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GameBuilder {

    private static final Team DEFAULT_TEAM_ONE = new Team("Team One", Collections.<Player>emptyList(), 0);
    private static final Team DEFAULT_TEAM_TWO = new Team("Team Two", Collections.<Player>emptyList(), 0);
    private static final List<String> DEFAULT_WORDS = Collections.emptyList();

    private GameSingleton gameSingleton;

    public GameBuilder withTeamOne(Team teamOne){
        if(teamOne != null) {
            gameSingleton.setTeamOne(teamOne);
        }
        return this;
    }

    public GameBuilder withTeamTwo(Team teamTwo){
        if(teamTwo != null) {
            gameSingleton.setTeamTwo(teamTwo);
        }
        return this;
    }

    public GameBuilder withWords(List<String> words){
        if(words != null && !words.isEmpty()){
            gameSingleton.setWords(words);
        }
        return this;
    }

    public GameSingleton build(){
        return gameSingleton;
    }
}
