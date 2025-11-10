package com.virtualboard.controller;

import com.virtualboard.service.EngineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EngineController {
    private final EngineService engineService = new EngineService();

    @GetMapping("/api/engine/status")
    public String getEngineStatus() {
        return engineService.getStatus();
    }
}
