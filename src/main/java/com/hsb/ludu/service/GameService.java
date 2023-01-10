package com.hsb.ludu.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface GameService {
    @Async
    void startGame();
    boolean isGameRunning();
}
