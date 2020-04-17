package com.aws.corona.charades.service;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import com.aws.corona.charades.domain.TeamPlayerNumbers;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameSetUpService {

    private final static String WORDS_FILE_PATH = "C:\\source\\Corona-Charades\\src\\main\\java\\com\\aws\\corona\\charades\\assets";

    private Random r = new Random();

    public void addPlayersToTeam(Integer numPlayersOnTeam, Team team) {
        //if for some reason the team is not empty...then empty it first before adding players
        //avoids resubmission issue
        if(!team.getPlayers().isEmpty()){
            team.setPlayers(new ArrayList<>());
        }
        for(int i=0; i<numPlayersOnTeam; i++) {
            Integer playerNum = i + 1;
            team.getPlayers().add(new Player("Player " + playerNum.toString()));
        }
    }

    public void addWordsToGame(TeamPlayerNumbers teamPlayerNumbers) {
        //if for some reason the game already has words...then empty it first before adding words
        //avoids resubmission issue
        if(!GameSingleton.getInstance().getWords().isEmpty()){
            GameSingleton.getInstance().setWords(new ArrayList<>());
        }
        GameSingleton.getInstance().getWords().add("test word");
        int totalPlayers = teamPlayerNumbers.getNumPlayersTeamOne() + teamPlayerNumbers.getNumPlayersTeamTwo();
        int numOfWordsForGame = totalPlayers * 5;
        List<String> gameWords = selectRandomWordsFromFile(WORDS_FILE_PATH, numOfWordsForGame);
        GameSingleton.getInstance().getWords().addAll(gameWords);
    }

    private List<String> selectRandomWordsFromFile(String fileName, int numOfWordsToGet) {
        try {
            List<String> words = Files.readAllLines(new File(fileName).toPath());
            List<String> selectedWords = new ArrayList<>();
            for(int i=0; i<numOfWordsToGet; i++){
                String selectedWord = words.get(r.nextInt(words.size()));
                selectedWords.add(selectedWord);
                words.remove(selectedWord);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
