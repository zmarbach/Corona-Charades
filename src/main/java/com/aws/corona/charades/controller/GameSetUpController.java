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

    //HOME PAGE - initialize game and team player numbers form is displayed
    @GetMapping(value = "/teams/add-num-of-players")
    public String displayTeamsForm(Model model) {
        model.addAttribute("game", GAME);
        model.addAttribute("teamPlayerNumbers", new TeamPlayerNumbers(0,0));
        return "teams";
    }
    //add number of players for each team, then go to player-names page
    @PostMapping("/teams/add-num-of-players")
    public String addNumberOfPlayersToTeams(@ModelAttribute("teamPlayerNumbers") TeamPlayerNumbers teamPlayerNumbers, Model model){
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamOne(), GAME.getTeamOne());
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamTwo(), GAME.getTeamTwo());
        gameSetUpService.addWordsToGame(teamPlayerNumbers, GAME);
        model.addAttribute("game", GAME);
        return "player-names";
    }

    @GetMapping("/player-names")
    public String addPlayerNames(){
        //update player names
        return "";
    }

    @GetMapping("/game-play-screen")
    public String updateGamePlayScreen(){
        //do all actions necessary to update game play screen after each move and then redirect to screen again
            //update scores and which player is up
            //start turn, correct, skip, next player, next round, end game, etc
        return "game-play-screen";
    }

    //TODO - POST mapping for after user indicates # of players per team and clicks submit
    // pass through as parameters
        //take number of players multiply by 5? 10? Allow them to determine # of words/player?
        //pull that many words randomly from word.txt and put in list of words for specific game
        //default players' names to be Player 1, Player 2, etc

    //TODO - POST mapping for after user player names and clicks submit
        //same idea as above

}
