package com.aws.corona.charades.repositories;

import com.aws.corona.charades.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class GameRepository {
    private Map<UUID, Game> gameMap;
    private UUID uuid;

    public GameRepository(Map<UUID, Game> gameMap) {
        this.gameMap = gameMap;
    }

    public Game getGame(UUID uuid) {
        try{
            return gameMap.get(uuid);
        }
        catch (Exception e){
            System.out.println("No game found for id of : " + uuid.toString());
            return null;
        }
    }

    public Game createNewGame(){
        Game game = new Game(UUID.randomUUID());
        gameMap.put(game.getUuid(), game);
        return game;
    }

    public void updateGame(Game game){
        if(gameMap.containsKey(game.getUuid())){
            //override old game with new game
            gameMap.put(game.getUuid(), game);
        }
    }

    public void deleteGame(Game game){
        if(gameMap.containsKey(game.getUuid())){
            gameMap.remove(game.getUuid());
        }
    }
}
