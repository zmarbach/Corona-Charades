package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.*;
import com.aws.corona.charades.service.GameService;
import com.aws.corona.charades.service.GameSetUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class GameSetUpController {

    private GameSetUpService gameSetUpService;
    private GameService gameService;

    public GameSetUpController(GameSetUpService gameSetUpService, GameService gameService) {
        this.gameSetUpService = gameSetUpService;
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
        List<String> categoryNames = new ArrayList<>(categoryMap.getCategoryFilePathMap().keySet());
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("teamsViewForm", new TeamsViewForm(gameUUID,0,0, 0, "General"));
        return "teams";
    }

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
}
