package com.aws.corona.charades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

//TODO - update this request mapping path once determined in GameSetUpController
@RequestMapping("/")
public class GamePlayController {


    //TODO - need to save current games words some where so can "reshuffle" them back in after each round
        //TODO - use guessed words in Game singleton

    //methods to handle each of the buttons on main game play screen. (Start turn, Correct, Skip, Next player, etc)
    //delegate logic to GamePlayHandler

    @GetMapping("/game-play-screen")
    public String updateGamePlayScreen(){
        //do all actions necessary to update game play screen after each move and then redirect to screen again
        //update scores and which player is up
        //start turn, correct, skip, next player, next round, end game, etc
        return "game-play-screen";
    }
}
