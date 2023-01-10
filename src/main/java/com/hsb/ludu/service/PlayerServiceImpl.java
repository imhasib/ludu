package com.hsb.ludu.service;

import com.hsb.ludu.dao.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static List<Player> players = new ArrayList();
    private int currentPlayer = 0;

    public List<Player> addAutoPlayers() {
        Player p1 = new Player();
        p1.setName("P1");
        p1.setAge(32);
        Player p2 = new Player();
        p2.setName("P2");
        p2.setAge(15);
        Player p3 = new Player();
        p3.setName("P3");
        p3.setAge(20);
        Player p4 = new Player();
        p4.setName("P4");
        p4.setAge(40);

        getPlayers().add(p1);
        getPlayers().add(p2);
        getPlayers().add(p3);
        getPlayers().add(p4);

        return getPlayers();
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public Player next() {
        Player player = this.getPlayers().get(currentPlayer);

        if(currentPlayer < this.getPlayers().size()) {
            currentPlayer++;
        }
        if (currentPlayer >= this.getPlayers().size()) {
            currentPlayer = 0;
        }

        return player;
    }

    @Override
    public void resetAllPlayers() {
        for(Player player: players) {
            player.reset();
        }
    }
}
