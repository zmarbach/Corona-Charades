package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.GameBuilder;
import com.aws.corona.charades.domain.GameCacheSingleton;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameSetUpService {

    private GameBuilder gameBuilder = new GameBuilder();
    private static final List<Game> allGames = GameCacheSingleton.getInstance().getAllGames();

    public Game initNewGame() {
        UUID uuid = UUID.randomUUID();
        Game game = gameBuilder.withUUID(uuid).build();
        allGames.add(game);
        return game;
    }
}
