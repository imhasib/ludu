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

	@PostMapping("/start")
	public String startGame() {
		if(playerService.getPlayers().size() >= Constants.MIN_PLAYER) {
			gameService.startGame();
			return "Game started!";
		} else {
			return "Waiting for players...";
		}
	}

	@GetMapping("/score")
	public String currentScore() {
		String allPlayersScore = "";

		for(Player player: playerService.getPlayers()) {
			allPlayersScore.concat(player.getName());
			allPlayersScore.concat(" : " + player.getScore() + " | ");
		}

		if(playerService.getPlayers().isEmpty()) {
			allPlayersScore = "There are no players. please add some player.";
		}

		return allPlayersScore;
	}
}
