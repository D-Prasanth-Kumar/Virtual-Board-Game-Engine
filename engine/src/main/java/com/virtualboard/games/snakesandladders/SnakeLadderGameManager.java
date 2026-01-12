package com.virtualboard.games.snakesandladders;

import com.virtualboard.engine.Dice;
import com.virtualboard.players.Player;

import java.util.*;

public class SnakeLadderGameManager {
    private int turnIndex = 0;

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

    public int getPlayerPosition(Player player) {

        return positions.getOrDefault(player, 0);
    }

    public Player getNextPlayer() {
        Player p = players.get(turnIndex);
        turnIndex = (turnIndex + 1) % players.size();
        return p;
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

    public int playTurn(Player player) {
        int roll = dice.roll();

        int currentPos = positions.getOrDefault(player, 1);
        int nextPos = currentPos + roll;

        if (nextPos <= 100) {
            nextPos = board.getNewPosition(nextPos);
            positions.put(player, nextPos);

            if (nextPos == 100) {
                winner = player;
            }
        }

        return roll;
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
