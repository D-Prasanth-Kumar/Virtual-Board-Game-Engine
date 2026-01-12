package com.virtualboard.service;

import com.virtualboard.games.snakesandladders.*;
import com.virtualboard.players.Player;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SnakeLadderService {
    private SnakeLadderGameManager manager;
    private List<Player> players;

    public Map<String, Object> startGame(List<String> playerNames) {
        players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(i + 1, playerNames.get(i)));
        }

        manager = new SnakeLadderGameManager(players);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Snakes & Ladders Started!");
        response.put("players", playerNames);
        return response;
    }

    public Map<String, Object> rollDice() {
        Map<String, Object> response = new HashMap<>();

        if (manager == null || manager.hasWinner()) {
            response.put("error", "Game over or not started");
            return response;
        }

        Player current = manager.getNextPlayer();

        int rollValue = manager.playTurn(current);

        response.put("rollValue", rollValue);
        response.put("currentPlayer", current.getName());
        response.put("winner", manager.getWinner() != null ? manager.getWinner().getName() : null);

        Map<String, Integer> currentPositions = new HashMap<>();
        for (Player p : players) {
            currentPositions.put(p.getName(), manager.getPlayerPosition(p));
        }
        response.put("positions", currentPositions);

        return response;
    }
}