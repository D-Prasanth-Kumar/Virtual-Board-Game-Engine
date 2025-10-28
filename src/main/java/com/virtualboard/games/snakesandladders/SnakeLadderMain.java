package com.virtualboard.games.snakesandladders;

import com.virtualboard.players.Player;
import java.util.*;

public class SnakeLadderMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter total number of players wants to play (2 - 4): ");
        int numPlayers = in.nextInt();
        in.nextLine();

        if(numPlayers < 2 || numPlayers > 4) {
            System.out.println("Invalid! Enter number of players between 2 and 4: ");
            return;
        }

        List<Player> players = new ArrayList<>();

        for(int i = 1; i <= numPlayers; i++) {
            System.out.println("Enter Name for Player " + i + ": ");
            String name = in.nextLine();
            players.add(new Player(i, name));
        }

        System.out.println("Starting Snakes & Ladders Game with " + numPlayers + " players!");

        SnakeLadderBoard board = new SnakeLadderBoard();
        SnakeLadderGameManager manager = new SnakeLadderGameManager(board, players);

        manager.playGame();

        in.close();
    }
}
