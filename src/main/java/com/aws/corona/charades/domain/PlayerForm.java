package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerForm {

    private List<Player> players;

    public PlayerForm(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
