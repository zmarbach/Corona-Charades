package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Service;

@Service
public class GameSetUpService {

    public GameSingleton initNewGame() {
        return GameSingleton.getInstance();
    }

    public void addPlayersToTeam(Integer numPlayersOnTeam, Team team) {
        for(int i=0; i<numPlayersOnTeam; i++) {
            team.getPlayers().add(new Player("Player " + i+1));
        }
    }
}
