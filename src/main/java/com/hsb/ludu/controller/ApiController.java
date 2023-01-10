package com.hsb.ludu.controller;

import com.hsb.ludu.dao.Player;
import com.hsb.ludu.service.GameService;
import com.hsb.ludu.service.PlayerService;
import com.hsb.ludu.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
@Validated
public class ApiController {

	@Autowired
	private PlayerService playerService;
	@Autowired
	private GameService gameService;

	// Create a new player:
	@PostMapping("/players")
	public Player createPlayer(@Valid @RequestBody Player player) {
		if(playerService.getPlayers().size() < Constants.MAX_PLAYER) {
			playerService.addPlayer(player);
		}
		return player;
	}


	@GetMapping("/players")
	public List<Player> getAllPlayers() {
		return playerService.getPlayers();
	}

	@GetMapping("/auto-players")
	public List<Player> addAutoPlayers() {
		return playerService.addAutoPlayers();
	}

	// Start game:
	@PostMapping("/start")
	public String startGame() {
		if(playerService.getPlayers().size() >= Constants.MIN_PLAYER) {
			gameService.startGame();
			return "Game started!";
		} else {
			return "Waiting for players...";
		}
	}

	// Retrieve current scores:
	@GetMapping("/score")
	public String currentScore() {
		String allPlayersScore = "";

		if(!gameService.isGameRunning()) {
			allPlayersScore = "There is no ongoing game.";
		} else {
			for(int i=0; i<playerService.getPlayers().size(); i++) {
				Player player = playerService.getPlayers().get(i);
				allPlayersScore += "Player name:”"+player.getName() + "”, Score:”" + player.getScore() + "”";

				if(i != playerService.getPlayers().size()-1) {
					allPlayersScore += " | ";
				}
			}
		}

		return allPlayersScore;
	}
}
