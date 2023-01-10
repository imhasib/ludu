package com.hsb.ludu.service;

import com.hsb.ludu.dao.Dice;
import com.hsb.ludu.dao.Player;
import com.hsb.ludu.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private PlayerService playerService;
    private static boolean gameRunning;

    public boolean isGameRunning() {
        return gameRunning;
    }

    public static void setGameRunning(boolean gameRunning) {
        GameServiceImpl.gameRunning = gameRunning;
    }

    @Override
    @Async
    public void startGame() {
        setGameRunning(true);

        boolean continueGame = true;
        while (continueGame) {
            Player currentPlayer = playerService.next();
            try {
                processScores(currentPlayer);
            } catch (Exception e) {
                // Handled dic rolling server error.
                continueGame = false;
                terminateGame();
                System.out.println("Something went wrong with dice rolling. Game is being terminated. Try again later.");
            }

            if(isWinnerFound(currentPlayer)) {
                continueGame = false;
                terminateGame();
                System.out.println(currentPlayer.getName() + " has won the game");
            }
        }
    }

    private void terminateGame() {
        setGameRunning(false);
        playerService.resetAllPlayers(); // Resetting all player's score for next game.
    }

    private int rollDice() throws Exception {
        int score = -1;

        RestTemplate template = new RestTemplate();
        Dice dice = template.getForObject(Constants.URI_FOR_ROLLING_DICE, Dice.class);
        score = dice.getScore();

        return score;
    }

    private void processScores(Player currentPlayer) throws Exception {
        boolean rollIt = true;

        while(rollIt) {
            int previousScore = currentPlayer.getPreviousScore();
            int diceScore = rollDice();
            int score = diceScore;

            if(!currentPlayer.isStarted() && diceScore == 6) {
                score = 0;
                currentPlayer.setStarted(true);
            } else if(!currentPlayer.isStarted() && diceScore == 4 && previousScore == 6) {
                score = 0;
                currentPlayer.setStarted(false);
            } else if(diceScore == 4) {
                score = -4;
            }

            currentPlayer.addScore(score);
            currentPlayer.setPreviousScore(diceScore);

            // Should rolling continue
            if(diceScore != 6 || isWinnerFound(currentPlayer)) {
                rollIt = false;
            }

            System.out.println("Player name:”"+currentPlayer.getName()+"”, Total Score:”"+currentPlayer.getScore()+"”, Current Value of Dice:”"+diceScore+"”");
        }
    }

    public boolean isWinnerFound(Player currentPlayer) {
        if(currentPlayer.getScore() >= Constants.WINING_SCORE) {
            return true;
        }
        return false;
    }
}
