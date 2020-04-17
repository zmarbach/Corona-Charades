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
    public GameSingleton initNewGame() {
        return GameSingleton.getInstance();
    }

    public void addPlayersToTeam(Integer numPlayersOnTeam, Team team) {
        for(int i=0; i<numPlayersOnTeam; i++) {
            Integer playerNum = i + 1;
            team.getPlayers().add(new Player("Player " + playerNum.toString()));
        }
    }

    public void addWordsToGame(TeamPlayerNumbers teamPlayerNumbers, GameSingleton game) {
        int totalPlayers = teamPlayerNumbers.getNumPlayersTeamOne() + teamPlayerNumbers.getNumPlayersTeamTwo();
        //TODO - maybe allow users to determine this multiple number?
        int numOfWordsForGame = totalPlayers * 5;
        List<String> gameWords = selectRandomWordsFromFile(WORDS_FILE_PATH, numOfWordsForGame);
        game.getWords().addAll(gameWords);
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
