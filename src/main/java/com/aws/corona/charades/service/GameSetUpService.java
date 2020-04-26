package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import com.aws.corona.charades.domain.TeamsViewForm;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class GameSetUpService {

    private Random r = new Random();

    public void addPlayersToTeam(Integer numPlayersOnTeam, Team team) {
        if(!team.getPlayers().isEmpty()){
            team.setPlayers(new ArrayList<>());
        }
        for(int i=0; i<numPlayersOnTeam; i++) {
            Integer playerNum = i + 1;
            team.getPlayers().add(new Player("Player " + playerNum.toString(), team));
        }
    }

    public void addWordsToGame(TeamsViewForm teamsViewForm) {
        if(!GameSingleton.getInstance().getActiveWords().isEmpty()){
            GameSingleton.getInstance().setActiveWords(new ArrayList<>());
        }
        int totalPlayers = teamsViewForm.getNumPlayersTeamOne() + teamsViewForm.getNumPlayersTeamTwo();
        int numOfWordsForGame = totalPlayers * teamsViewForm.getNumWordsPerPlayer();

        List<String> gameWords = selectRandomWordsFromFile(numOfWordsForGame);
        GameSingleton.getInstance().getActiveWords().addAll(gameWords);
    }

    private List<String> selectRandomWordsFromFile(int numOfWordsToGet) {
        List<String> selectedWords = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("C:\\source\\Corona-Charades\\words.txt"));
            List<String> words = new ArrayList<>();
            while(scanner.hasNextLine()){
                words.add(scanner.nextLine());
            }

            for(int i=0; i<numOfWordsToGet; i++){
                String selectedWord = words.get(r.nextInt(words.size()));
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
