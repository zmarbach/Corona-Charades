package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.*;
import com.aws.corona.charades.service.GameService;
import com.aws.corona.charades.service.GamePlayService;
import com.aws.corona.charades.service.GameSetUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
public class GameController {

    private GameSetUpService gameSetUpService;
    private GamePlayService gamePlayService;
    private GameService gameService;

    public GameController(GameSetUpService gameSetUpService, GamePlayService gamePlayService, GameService gameService) {
        this.gameSetUpService = gameSetUpService;
        this.gamePlayService = gamePlayService;
        this.gameService = gameService;
    }

    @GetMapping(value = "/home")
    public String displayHomePage(){
      return "home";
    }

    @PostMapping(value = "/home")
    public String createNewGame(){
        Game game = gameService.createNewGame();
        return "redirect:/" + game.getUuid() + "/teams";
    }

    @GetMapping(value = "/{gameUUID}/teams")
    public String displayTeamsForm(Model model, @PathVariable UUID gameUUID) {
        CategoryMap categoryMap = new CategoryMap(new HashMap<>());
        List<String> categoryNames = new ArrayList<>();
        categoryNames.addAll(categoryMap.getCategoryFilePathMap().keySet());
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("teamsViewForm", new TeamsViewForm(gameUUID,0,0, 0, "General"));
        return "teams";
    }

    //Have this cuz I'm lazy and dont want to write JS on front end
    //Teams page just posts to here. Server finds game and passed that to
    @PostMapping("/teams")
    public String teamsRedirect(@ModelAttribute("teamsViewForm") TeamsViewForm teamsViewForm){
        Game game = gameService.findGameById(teamsViewForm.getGameUUID());
        gameSetUpService.setUpGame(teamsViewForm, game);
        return "redirect:/" + teamsViewForm.getGameUUID() + "/player-names-team-one";
    }

    @GetMapping("/{gameUUID}/player-names-team-one")
    public String displayPlayerNamesForTeamOne(Model model, @PathVariable UUID gameUUID){
        Game game = gameService.findGameById(gameUUID);
        PlayerForm playerForm = new PlayerForm(game.getTeamOne().getPlayers(), gameUUID);
        model.addAttribute("playerForm", playerForm);
        return "player-names-team-one";
    }

    @PostMapping("/player-names-team-one")
    public String updateTeamOnePlayerNames(@ModelAttribute("playerForm") PlayerForm playerForm){
        Game game = gameService.findGameById(playerForm.getGameUUID());
        List<Player> players = playerForm.getPlayers();
        for (int i=0; i<players.size(); i++){
            game.getTeamOne().getPlayers().get(i).setName(players.get(i).getName());
        }
        return "redirect:/" + game.getUuid() + "/player-names-team-two";
    }

    @GetMapping("/{gameUUID}/player-names-team-two")
    public String displayPlayerNamesForTeamTwo(Model model, @PathVariable UUID gameUUID){
        Game game = gameService.findGameById(gameUUID);
        PlayerForm playerForm = new PlayerForm(game.getTeamTwo().getPlayers(), gameUUID);
        model.addAttribute("playerForm", playerForm);
        return "player-names-team-two";
    }

    @PostMapping("/player-names-team-two")
    public String updateTeamTwoPlayerNames(@ModelAttribute("playerForm") PlayerForm playerForm){
        Game game = gameService.findGameById(playerForm.getGameUUID());
        List<Player> players = playerForm.getPlayers();
        for (int i=0; i<players.size(); i++){
            game.getTeamTwo().getPlayers().get(i).setName(players.get(i).getName());
        }

        //set player 1 from Team One as current player and Team One as current Team
        Player playerOneTeamOne = game.getTeamOne().getPlayers().get(0);
        game.setCurrentPlayer(playerOneTeamOne);
        return "redirect:/" + game.getUuid() + "/game-play";
    }

    //TODO - think about separating these methods to separate controller

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
