package com.virtualboard.engine;

public abstract class Game {
    private String name;
    private int maxPlayers;

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        if(maxPlayers > 0) {
            this.maxPlayers = maxPlayers;
        }
    }

    public abstract void start();
}
