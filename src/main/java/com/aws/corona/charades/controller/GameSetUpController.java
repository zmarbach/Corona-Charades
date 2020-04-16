package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.TeamPlayerNumbers;
import com.aws.corona.charades.service.GameSetUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameSetUpController {

    private final String siteName;
    private GameSetUpService gameSetUpService = new GameSetUpService();
    private static final GameSingleton GAME = GameSingleton.getInstance();

    public GameSetUpController(final String siteName) {
        this.siteName = siteName;
    }

    //create new game, then go to teams page
    @GetMapping("/game/new")
    public String newGame(Model model){
        model.addAttribute("game", GAME);
        return "/teams";
    }

    //add number of players for each team, then go to player-names page
    @PostMapping("/add-num-of-players")
    public String addNumberOfPlayersToTeams(@ModelAttribute("teamPlayerNumbers")TeamPlayerNumbers teamPlayerNumbers, Model model){
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamOne(), GAME.getTeamOne());
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamTwo(), GAME.getTeamTwo());
        model.addAttribute("game", GAME);
        return "/player-names";
    }

    //TODO - how to pass the game UUID after game is created so can add players, score, manipulate words, etc
        //pass it through the url somehow. Review spring pet clinic project for example

    //TODO - POST mapping for after user indicates # of players per team and clicks submit
    // pass through as parameters
        //take number of players multiply by 5? 10? Allow them to determine # of words/player?
        //pull that many words randomly from word.txt and put in list of words for specific game
        //default players' names to be Player 1, Player 2, etc

    //TODO - POST mapping for after user player names and clicks submit
        //same idea as above

}
