package com.virtualboard.players;

import com.virtualboard.engine.Game;  //Aggregation: Player knows about Game

public class Player {
    private String name;
    private int score;
    private int playerId;

    private Game currentGame;

    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.score = 0;
        this.currentGame = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score >= 0) {
            this.score = score;
        }else {
            System.out.println("Score cannot be negative!");
        }
    }

    public int getPlayerId() {
        return playerId;
    }

    // Assign Game (Aggregation)
    public void joinGame(Game game) {
        this.currentGame = game;
        System.out.println(name + " joined " + game.getName());
    }

    public void leaveGame() {
        if(currentGame != null) {
            System.out.println((name + " left " + currentGame.getName()));
            currentGame = null;
        }else {
            System.out.println(name + " is not in any game!");
        }
    }

    public void displayInfo() {
        System.out.println("Player ID: " + playerId + ", Name: " + name + ", Score: " + score);
        if(currentGame != null) {
            System.out.println("Current Game: " + currentGame.getName());
        }
    }
}
