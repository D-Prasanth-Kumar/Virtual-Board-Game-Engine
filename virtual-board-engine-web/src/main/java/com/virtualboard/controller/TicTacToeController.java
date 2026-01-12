package com.virtualboard.controller;

import com.virtualboard.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {

    @Autowired
    private TicTacToeService service;

    @PostMapping("/start")
    public Map<String, Object> startGame(@RequestBody Map<String, String> body) {
        String player1 = body.get("player1");
        String player2 = body.get("player2");

        if (player1 == null || player2 == null) {
            return Map.of("error", "Both player names are required!");
        }

        return service.startGame(player1, player2);
    }

    @PostMapping("/move")
    public Map<String, Object> makeMove(@RequestBody Map<String, Integer> moveData) {
        Integer row = moveData.get("row");
        Integer col = moveData.get("col");

        if (row == null || col == null) {
            return Map.of("error", "Row and Column must be provided!");
        }

        return service.makeMove(row, col);
    }

    @GetMapping("/state")
    public Map<String, Object> getGameState() {
        return service.getGameState();
    }
}
