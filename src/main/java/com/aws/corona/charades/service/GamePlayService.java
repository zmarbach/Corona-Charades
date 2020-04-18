package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import org.springframework.stereotype.Service;

@Service
public class GamePlayService {
    private final static GameSingleton GAME = GameSingleton.getInstance();

    public void handleStartTurn(){
        GAME.setCurrentWord(GAME.getActiveWords().get(0));
    }
}
