package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.GamePlayViewForm;
import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
//@RequestMapping("/game-play")
public class GamePlayController {
    private String DEFAULT_WORD = "TEST WORD";
    private Team DEFAULT_TEAM = new Team("", new ArrayList<>(), 0);
    private Player DEFAULT_PLAYER = new Player("Derek Jeter");

    //TODO - need to save current games words some where so can "reshuffle" them back in after each round
        //TODO - use guessed words in Game singleton

//    @GetMapping("/")
//    public String updateGamePlayPage(Model model){
//        model.addAttribute("test", "test");
//        GameSingleton GAME = GameSingleton.getInstance();
//        GamePlayViewForm gamePlayViewForm = new GamePlayViewForm(
//                GAME.getCurrentWord(),
//                GAME.getCurrentPlayer(),
//                GAME.getCurrentTeam(),
//                GAME.getTeamOne().getScore(),
//                GAME.getTeamTwo().getScore());
//        model.addAttribute("gamePlayViewForm", gamePlayViewForm);
//        return "game-play";
//    }

    @PostMapping("/correct")
    public String handleCorrectGuess(){
        //delegate to gamePlayService method
        //update model attributes
        return "game-play";
    }

    @PostMapping("/skip")
    public String handleSkip(){
        //delegate to gamePlayService method
        //update model attributes
        return "game-play";
    }
}
