package com.aws.corona.charades.controller;

import com.aws.corona.charades.domain.GameSingleton;
import com.aws.corona.charades.domain.Player;
import com.aws.corona.charades.domain.PlayerForm;
import com.aws.corona.charades.domain.TeamPlayerNumbers;
import com.aws.corona.charades.service.GameSetUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameSetUpController {

    //TODO - move GAME back up as class level variable...cleaner code!!!
    private GameSetUpService gameSetUpService = new GameSetUpService();

    public GameSetUpController() {
    }

    @GetMapping(value = "/teams")
    public String displayTeamsForm(Model model) {
        model.addAttribute("teamPlayerNumbers", new TeamPlayerNumbers(0,0));
        return "teams";
    }

    //add number of players for each team, then go to player-names page
    @PostMapping("/teams")
    public String addNumberOfPlayersToTeams(@ModelAttribute("teamPlayerNumbers") TeamPlayerNumbers teamPlayerNumbers){
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamOne(), GameSingleton.getInstance().getTeamOne());
        gameSetUpService.addPlayersToTeam(teamPlayerNumbers.getNumPlayersTeamTwo(), GameSingleton.getInstance().getTeamTwo());
        gameSetUpService.addWordsToGame(teamPlayerNumbers);
        return "redirect:/player-names-team-one";
    }

    //TODO - defect - why are players names in the game instance, but not showing up on player form????
    @GetMapping("/player-names-team-one")
    public String displayPlayerNamesForTeamOne(Model model){
        PlayerForm playerForm = new PlayerForm();
        playerForm.setPlayers(GameSingleton.getInstance().getTeamOne().getPlayers());
        model.addAttribute("playerForm", playerForm);
        model.addAttribute("numOfPlayersInPlayerForm", playerForm.getPlayers().size());
        model.addAttribute("activeWords", GameSingleton.getInstance().getActiveWords());
        model.addAttribute("teamOnePlayers", GameSingleton.getInstance().getTeamOne().getPlayers());
        model.addAttribute("teamTwoPlayers", GameSingleton.getInstance().getTeamTwo().getPlayers());
        return "player-names-team-one";
    }

    @PostMapping("/player-names-team-one")
    public String updateTeamOnePlayerNames(@ModelAttribute("playerForm") PlayerForm playerForm){
        List<Player> players = playerForm.getPlayers();
        for (int i=0; i<players.size(); i++){
            GameSingleton.getInstance().getTeamOne().getPlayers().get(i).setName(players.get(i).getName());
        }
        return "redirect:/player-names-team-two";
    }

    @GetMapping("/player-names-team-two")
    public String displayPlayerNamesForTeamTwo(Model model){
        PlayerForm playerForm = new PlayerForm();
        playerForm.setPlayers(GameSingleton.getInstance().getTeamTwo().getPlayers());
        model.addAttribute("playerForm", playerForm);
        return "player-names-team-two";
    }

    @PostMapping("/player-names-team-two")
    public String updateTeamTwoPlayerNames(@ModelAttribute("playerForm") PlayerForm playerForm){
        //update player names
        List<Player> players = playerForm.getPlayers();
        for (int i=0; i<players.size(); i++){
            GameSingleton.getInstance().getTeamTwo().getPlayers().get(i).setName(players.get(i).getName());
        }
        //finished with set up...go to game play
        return "game-play";
    }

}
