package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.*;
import com.aws.corona.charades.service.GamePlayService;
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
    private GamePlayService gamePlayService = new GamePlayService();
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

        //set player 1 from Team One as current player and Team One as current Team
        Player playerOneTeamOne = GAME.getTeamOne().getPlayers().get(0);
        GAME.setCurrentPlayer(playerOneTeamOne);
        return "redirect:/game-play";
    }

    //TODO - think about separating these methods to separate controller

    @GetMapping("/game-play")
    public String updateGamePlayPage(Model model){
        GamePlayViewForm gamePlayViewForm = new GamePlayViewForm(
                GAME.getCurrentWord(),
                GAME.getCurrentPlayer(),
                GAME.getTeamOne().getScore(),
                GAME.getTeamTwo().getScore(),
                GAME.getActiveWords(),
                GAME.isNewTurn());
        model.addAttribute("gamePlayViewForm", gamePlayViewForm);
        return "game-play";
    }

    @PostMapping("/start-turn")
    public String startTurn(){
        gamePlayService.handleStartTurn();
        return "redirect:/game-play";
    }

    @PostMapping("/correct")
    public String handleCorrectGuess(){
        gamePlayService.handleCorrect();
        return "redirect:/game-play";
    }

    @PostMapping("/skip")
    public String handleSkip(){
        gamePlayService.handleSkip();
        return "redirect:/game-play";
    }

    @PostMapping("/next-player")
    public String nextPlayer(){
        gamePlayService.handleNextPlayer();
        return "redirect:/game-play";
    }

    @PostMapping("/next-round")
    public String nextRound(){
        gamePlayService.handleNextRound();
        //update model attributes
        return "redirect:/game-play";
    }

    @PostMapping("/end-game")
    public String endGame(){
        gamePlayService.handleEndGame();
        //delete all current values in game and redirect to teams page
        return "redirect:/teams";
    }
}
