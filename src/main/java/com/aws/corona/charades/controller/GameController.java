package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.*;
import com.aws.corona.charades.service.GameSetUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameController {

    private GameSetUpService gameSetUpService = new GameSetUpService();
    private static final GameSingleton GAME = GameSingleton.getInstance();

    public GameController() {
    }

    @GetMapping(value = "/teams")
    public String displayTeamsForm(Model model) {
        model.addAttribute("teamPlayerNumbers", new TeamPlayerNumbers(0,0));
        return "teams";
    }

    @PostMapping("/teams")
    public String addNumberOfPlayersToTeams(@ModelAttribute("teamPlayerNumbers") TeamPlayerNumbers teamPlayerNumbers){
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamOne(), GAME.getTeamOne());
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamTwo(), GAME.getTeamTwo());
        gameSetUpService.addWordsToGame(teamPlayerNumbers);
        return "redirect:/player-names-team-one";
    }

    @GetMapping("/player-names-team-one")
    public String displayPlayerNamesForTeamOne(Model model){
        PlayerForm playerForm = new PlayerForm();
        playerForm.setPlayers(GAME.getTeamOne().getPlayers());
        model.addAttribute("playerForm", playerForm);
        model.addAttribute("activeWords", GAME.getActiveWords());
        model.addAttribute("teamOnePlayers", GAME.getTeamOne().getPlayers());
        model.addAttribute("teamTwoPlayers", GAME.getTeamTwo().getPlayers());
        return "player-names-team-one";
    }

    @PostMapping("/player-names-team-one")
    public String updateTeamOnePlayerNames(@ModelAttribute("playerForm") PlayerForm playerForm){
        List<Player> players = playerForm.getPlayers();
        for (int i=0; i<players.size(); i++){
            GAME.getTeamOne().getPlayers().get(i).setName(players.get(i).getName());
        }
        return "redirect:/player-names-team-two";
    }

    @GetMapping("/player-names-team-two")
    public String displayPlayerNamesForTeamTwo(Model model){
        PlayerForm playerForm = new PlayerForm();
        playerForm.setPlayers(GAME.getTeamTwo().getPlayers());
        model.addAttribute("playerForm", playerForm);
        return "player-names-team-two";
    }

    @PostMapping("/player-names-team-two")
    public String updateTeamTwoPlayerNames(@ModelAttribute("playerForm") PlayerForm playerForm){
        List<Player> players = playerForm.getPlayers();
        for (int i=0; i<players.size(); i++){
            GAME.getTeamTwo().getPlayers().get(i).setName(players.get(i).getName());
        }
        return "redirect:/game-play";
    }

    //TODO - think about separating these methods to separate controller

    @GetMapping("/game-play")
    public String updateGamePlayPage(Model model){
        model.addAttribute("test", "test");
        GamePlayViewForm gamePlayViewForm = new GamePlayViewForm(
                GAME.getCurrentWord(),
                GAME.getCurrentPlayer(),
                GAME.getCurrentTeam(),
                GAME.getTeamOne().getScore(),
                GAME.getTeamTwo().getScore());
        model.addAttribute("gamePlayViewForm", gamePlayViewForm);
        return "game-play";
    }

    @PostMapping("/start-turn")
    public String startTurn(){
        //delegate to gamePlayService method
        //update model attributes
        return "game-play";
    }

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

    @PostMapping("/next-player")
    public String nextPlayer(){
        //delegate to gamePlayService method
        //update model attributes
        return "game-play";
    }

    @PostMapping("/next-round")
    public String nextRound(){
        //delegate to gamePlayService method
        //update model attributes
        return "game-play";
    }
}
