package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class GameSetUpService {
    private CategoryMap categoryMap;
    private Random random;
    private GameService gameService;

    public GameSetUpService(CategoryMap categoryMap, Random random, GameService gameService) {
        this.categoryMap = categoryMap;
        this.random = random;
        this.gameService = gameService;
    }

    public void setUpGame(TeamsViewForm teamsViewForm, Game game) {
        addPlayersToTeam(teamsViewForm.getNumPlayersTeamOne(), game.getTeamOne());
        addPlayersToTeam(teamsViewForm.getNumPlayersTeamTwo(), game.getTeamTwo());
        addWordsToGame(teamsViewForm, game);
        gameService.updateGame(game);
    }

    public void addPlayersToTeam(Integer numPlayersOnTeam, Team team) {
        if(!team.getPlayers().isEmpty()){
            team.setPlayers(new ArrayList<>());
        }
        for(int i=0; i<numPlayersOnTeam; i++) {
            Integer playerNum = i + 1;
            team.getPlayers().add(new Player("Player " + playerNum.toString(), team));
        }
    }

    public void addWordsToGame(TeamsViewForm teamsViewForm, Game game) {
        if(!game.getActiveWords().isEmpty()){
            game.setActiveWords(new ArrayList<>());
        }
        int totalPlayers = teamsViewForm.getNumPlayersTeamOne() + teamsViewForm.getNumPlayersTeamTwo();
        int numOfWordsForGame = totalPlayers * teamsViewForm.getNumWordsPerPlayer();

        List<String> gameWords = selectRandomWordsFromFile(numOfWordsForGame, categoryMap.getCategoryFilePathMap().get(teamsViewForm.getSelectedCategoryName()));
        game.getActiveWords().addAll(gameWords);
    }

    private List<String> selectRandomWordsFromFile(int numOfWordsToGet, String filePath) {
        List<String> selectedWords = new ArrayList<>();
        try {
            InputStream in = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            List<String> words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }

            for(int i=0; i<numOfWordsToGet; i++){
                String selectedWord = words.get(random.nextInt(words.size()));
                selectedWords.add(selectedWord);
                words.remove(selectedWord);
            }
            return selectedWords;
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            List<String> list = new ArrayList<>();
            list.add("FileNotFound - " + e.getMessage());
            return list;
        } catch (IOException e) {
            List<String> list = new ArrayList<>();
            list.add("IOException - " + e.getMessage());
            return list;
        }
    }
}
