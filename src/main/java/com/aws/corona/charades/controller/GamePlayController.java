package com.aws.corona.charades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

//TODO - update this request mapping path once determined in GameSetUpController
@RequestMapping("/")
public class GamePlayController {

    //methods to handle each of the buttons on main game play screen. (Start turn, Correct, Skip, Next player, etc)
    //delegate logic to GamePlayHandler
}
