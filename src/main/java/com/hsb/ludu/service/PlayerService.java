package com.hsb.ludu.service;

import com.hsb.ludu.dao.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player next();
    void addPlayer(Player player);
    List<Player> getPlayers();
    void resetAllPlayers();
    List<Player> addAutoPlayers();
}
