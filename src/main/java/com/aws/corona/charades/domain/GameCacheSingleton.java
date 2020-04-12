package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GameCacheSingleton {
    private static GameCacheSingleton INSTANCE;
    private List<Game> allGames;

    private GameCacheSingleton() {
        this.allGames = new ArrayList<Game>();
    }

    public static synchronized GameCacheSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameCacheSingleton();
        }
        return INSTANCE;
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public Game getGameByUUID(UUID uuid) throws NoSuchObjectException {
        for (Game game : allGames) {
            if (uuid.equals(game.getUuid())) {
                return game;
            }
        }
        throw new NoSuchObjectException("No game was found matching UUID: " + uuid.toString());
    }
}
