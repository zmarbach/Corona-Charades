package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.handler.GameSetUpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class GameSetUpController {

    private final String siteName;
    private final GameSetUpHandler gameSetUpHandler;

    public GameSetUpController(final String siteName, GameSetUpHandler gameSetUpHandler) {
        this.siteName = siteName;
        this.gameSetUpHandler = gameSetUpHandler;
    }

    @GetMapping
    public ModelAndView test() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("siteName", this.siteName);
        return mav;
    }

    @GetMapping("/new-game")
    public ModelAndView newGame(){
        ModelAndView mav = new ModelAndView("relevant-jsp-name");
        Game game = gameSetUpHandler.initNewGame();
        mav.addObject("game", game);
        return mav;
    }

    //TODO - how to pass the game UUID after game is created so can add players, score, manipulate words, etc
        //pass it through the url somehow. Review spring pet clinic project for example

    //TODO - POST mapping for after user indicates # of players per team and clicks submit
        //take number of players multiply by 5? 10? Allow them to determine # of words/player?
        //pull that many words randomly from word.txt and put in list of words for specific game
        //default players' names to be Player 1, Player 2, etc

    //TODO - POST mapping for after user player names and clicks submit
        //same idea as above

}
