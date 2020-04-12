package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.GameBuilder;
import com.aws.corona.charades.domain.GameCacheSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameSetUpService {

    //if autowired doesn't work then just new it up
    @Autowired
    private GameBuilder gameBuilder;
    private static final List<Game> allGames = GameCacheSingleton.getInstance().getAllGames();

    public Game initNewGame() {
        UUID uuid = UUID.randomUUID();
        Game game = gameBuilder.withUUID(uuid).build();
        allGames.add(game);
        return game;
    }
}
