package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.Game;
import com.aws.corona.charades.domain.GamePlayViewForm;
import com.aws.corona.charades.service.GamePlayService;
import com.aws.corona.charades.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class GamePlayController {

    private GamePlayService gamePlayService;
    private GameService gameService;

    public GamePlayController(GamePlayService gamePlayService, GameService gameService) {
        this.gamePlayService = gamePlayService;
        this.gameService = gameService;
    }

    @GetMapping("/{gameUUID}/game-play")
    public String updateGamePlayPage(Model model, @PathVariable UUID gameUUID){
        Game game = gameService.findGameById(gameUUID);
        GamePlayViewForm gamePlayViewForm = new GamePlayViewForm(
                gameUUID,
                game.getCurrentWord(),
                game.getCurrentPlayer(),
                game.getTeamOne().getScore(),
                game.getTeamTwo().getScore(),
                game.getActiveWords(),
                game.isNewTurn());
        model.addAttribute("gamePlayViewForm", gamePlayViewForm);
        return "game-play";
    }

    @PostMapping("/start-turn")
    public String startTurn(@RequestParam("gameUUID") UUID gameUUID){
        gamePlayService.handleStartTurn(gameService.findGameById(gameUUID));
        return "redirect:/" + gameUUID + "/game-play";
    }

    @PostMapping("/correct")
    public String handleCorrectGuess(@RequestParam UUID gameUUID){
        gamePlayService.handleCorrect(gameService.findGameById(gameUUID));
        return "redirect:/" + gameUUID + "/game-play";
    }

    @PostMapping("/skip")
    public String handleSkip(@RequestParam UUID gameUUID){
        gamePlayService.handleSkip(gameService.findGameById(gameUUID));
        return "redirect:/" + gameUUID + "/game-play";
    }

    @PostMapping("/next-player")
    public String nextPlayer(@RequestParam UUID gameUUID){
        gamePlayService.handleNextPlayer(gameService.findGameById(gameUUID));
        return "redirect:/" + gameUUID + "/game-play";
    }

    @PostMapping("/next-round")
    public String nextRound(@RequestParam UUID gameUUID){
        gamePlayService.handleNextRound(gameService.findGameById(gameUUID));
        return "redirect:/" + gameUUID + "/game-play";
    }

    @PostMapping("/end-game")
    public String endGame(@RequestParam UUID gameUUID){
        gamePlayService.handleEndGame(gameService.findGameById(gameUUID));
        return "/home";
    }
}
