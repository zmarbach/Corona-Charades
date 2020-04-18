package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import com.aws.corona.charades.domain.TeamPlayerNumbers;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class GameSetUpService {

    private final static String WORDS_FILE_PATH = "C:\\source\\Corona-Charades\\words.txt";
    private Random r = new Random();

    public void addPlayersToTeam(Integer numPlayersOnTeam, Team team) {
        if(!team.getPlayers().isEmpty()){
            team.setPlayers(new ArrayList<>());
        }
        for(int i=0; i<numPlayersOnTeam; i++) {
            Integer playerNum = i + 1;
            team.getPlayers().add(new Player("Player " + playerNum.toString()));
        }
    }

    public void addWordsToGame(TeamPlayerNumbers teamPlayerNumbers) {
        if(!GameSingleton.getInstance().getActiveWords().isEmpty()){
            GameSingleton.getInstance().setActiveWords(new ArrayList<>());
        }
        int totalPlayers = teamPlayerNumbers.getNumPlayersTeamOne() + teamPlayerNumbers.getNumPlayersTeamTwo();
        int numOfWordsForGame = totalPlayers * 5;
//        List<String> gameWords = selectRandomWordsFromFile(WORDS_FILE_PATH, numOfWordsForGame);
        List<String> gameWords = new ArrayList<>();
        gameWords.add("test1");
        gameWords.add("test2");
        gameWords.add("test3");
        gameWords.add("test4");
        gameWords.add("test5");
        gameWords.add("test6");
        GameSingleton.getInstance().getActiveWords().addAll(gameWords);
    }

    private List<String> selectRandomWordsFromFile(String filePath, int numOfWordsToGet) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            List<String> words = new ArrayList<>();
            while(scanner.hasNext()){
                words.add(scanner.next());
            }

            List<String> selectedWords = new ArrayList<>();
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
            list.add("FILENOTFOUND" + e.getMessage());
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            List<String> list = new ArrayList<>();
            list.add("IOEXCEPTION" + e.getMessage());
            return list;
        }
    }
}
