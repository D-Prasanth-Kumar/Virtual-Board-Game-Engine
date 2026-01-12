package com.virtualboard.controller;

import com.virtualboard.service.SnakeLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/snakes")
@CrossOrigin(origins = "*")
public class SnakeLadderController {

    @Autowired
    private SnakeLadderService service;

    @PostMapping("/start")
    public Map<String, Object> startGame(@RequestBody List<String> playerNames) {
        return service.startGame(playerNames);
    }

    @PostMapping("/roll")
    public Map<String, Object> rollDice() {
        return service.rollDice();
    }
}