package com.virtualboard.games.snakesandladders;

import com.virtualboard.engine.Dice;
import com.virtualboard.players.Player;

import java.util.*;

public class SnakeLadderGameManager {
    private SnakeLadderBoard board;
    private Dice dice;
    private List<Player> players;
    private Map<Player, Integer> positions;
    private Player winner;

    public SnakeLadderGameManager(List<Player> players) {
        this.board = new SnakeLadderBoard();
        this.dice = new Dice();
        this.players = players;
        this.positions = new HashMap<>();

        for(Player player : players) {
            positions.put(player, 0);
        }
        this.winner = null;
    }

    public SnakeLadderGameManager(SnakeLadderBoard board, List<Player> players) {
        this.board = board;
        this.dice = new Dice();
        this.players = players;
        this.positions = new HashMap<>();

        for(Player player : players) {
            positions.put(player, 0);
        }
        this.winner = null;
    }

    public void playTurn(Player player) {
        if(winner != null) return;

        int currentPos = positions.get(player);
        int roll = dice.roll();
        int nextPos = currentPos + roll;

        if(nextPos > board.getSize()) {
            System.out.println(player.getName() + " rolled " + roll + " but cannot move (out of the board).");
            return;
        }

        int finalPos = board.getNextPosition(nextPos);
        positions.put(player, finalPos);

        System.out.println(player.getName() + " rolled " + roll + " -> moved from " + currentPos + " to " + finalPos);

        if(finalPos == board.getSize()) {
            winner = player;
            System.out.println(player.getName() + " is the WINNER!");
        }
    }

    public boolean hasWinner() {
        return winner != null;
    }

    public Player getWinner() {
        return winner;
    }

    public void printPositions() {
        for(Player player : players) {
            System.out.println(player.getName() + " is at position " + positions.get(player));
        }
    }

    public void playGame() {
        Scanner in = new Scanner(System.in);

        if(players == null || players.size() < 2) {
            System.out.println("Atleast 2 players required to start the game!");
            return;
        }

        int idx = 0;
        while(!hasWinner()) {
            Player current = players.get(idx);

            System.out.println(current.getName() + "'s turn -- press ENTER to roll the dice");
            in.nextLine();

            playTurn(current);
            printPositions();
            System.out.println();

            if(hasWinner()) break;
            idx = (idx + 1) % players.size();

            try {
                Thread.sleep(300);
            }catch (InterruptedException ignored) {}
        }

        System.out.println("GAME OVER! " + winner.getName() + " is the WINNER.");
    }
}
