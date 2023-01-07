package com.hsb.ludu;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ApiController {


	@PostMapping("/players")
	public String players(@RequestBody Player player) {
		return player.name;
	}

	@PostMapping("/start")
	public void startGame() {
		System.out.println("Start Game");
	}

	@GetMapping("/score")
	public String currentScore() {
		return "Current score";
	}
}
