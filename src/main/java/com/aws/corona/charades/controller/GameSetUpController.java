package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.service.GameSetUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GameSetUpController {

    private final String siteName;
    private GameSetUpService gameSetUpService = new GameSetUpService();

    public GameSetUpController(final String siteName) {
        this.siteName = siteName;
    }

    @GetMapping
    public String test(Model model) {
        model.addAttribute("siteName", this.siteName);
        return "index";
    }

    @GetMapping("/game/new")
    public String newGame(Model model){
        Game game = gameSetUpService.initNewGame();
        model.addAttribute("game", game);
        return "relevant-jsp-name";
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
