package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.GamePlayViewForm;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class GamePlayController {
    private String DEFAULT_WORD = "TEST WORD";
    private Team DEFAULT_TEAM = new Team("", new ArrayList<>(), 0);
    private Player DEFAULT_PLAYER = new Player("Derek Jeter");

    //TODO - need to save current games words some where so can "reshuffle" them back in after each round
        //TODO - use guessed words in Game singleton

    //methods to handle each of the buttons on main game play screen. (Start turn, Correct, Skip, Next player, etc)
    //delegate logic to GamePlayHandler

    @GetMapping("/game-play")
    public String updateGamePlayPage(Model model){
        model.addAttribute("test", "test");
        model.addAttribute("gamePlayViewForm", new GamePlayViewForm(DEFAULT_WORD, DEFAULT_PLAYER, DEFAULT_TEAM,0,0));
        //do all actions necessary to update game play screen after each move and then redirect to screen again
        //update scores and which player is up
        //start turn, correct, skip, next player, next round, end game, etc
        return "game-play";
    }
}
