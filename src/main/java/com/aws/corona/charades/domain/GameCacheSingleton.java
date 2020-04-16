package com.aws.corona.charades.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameCacheSingleton {
    private static GameCacheSingleton INSTANCE;
    private List<GameSingleton> allGameSingletons;

    private GameCacheSingleton() {
        this.allGameSingletons = new ArrayList<GameSingleton>();
    }

    public static synchronized GameCacheSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameCacheSingleton();
        }
        return INSTANCE;
    }

    public List<GameSingleton> getAllGameSingletons() {
        return allGameSingletons;
    }

}
