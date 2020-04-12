package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class GameBuilder {

    private static final UUID DEFAULT_UUID = UUID.fromString("1");
    private static final Team DEFAULT_TEAM_ONE = new Team("Team One", Collections.<Player>emptyList(), 0);
    private static final Team DEFAULT_TEAM_TWO = new Team("Team Two", Collections.<Player>emptyList(), 0);
    private static final List<String> DEFAULT_WORDS = Collections.emptyList();

    private Game game = new Game(DEFAULT_UUID, DEFAULT_TEAM_ONE, DEFAULT_TEAM_TWO, DEFAULT_WORDS);

    public GameBuilder withUUID(UUID uuid){
        if(uuid != null) {
            game.setUuid(uuid);
        }
        return this;
    }

    public GameBuilder withTeamOne(Team teamOne){
        if(teamOne != null) {
            game.setTeamOne(teamOne);
        }
        return this;
    }

    public GameBuilder withTeamTwo(Team teamTwo){
        if(teamTwo != null) {
            game.setTeamTwo(teamTwo);
        }
        return this;
    }

    public GameBuilder withWords(List<String> words){
        if(words != null && !words.isEmpty()){
            game.setWords(words);
        }
        return this;
    }

    public Game build(){
        return game;
    }
}
