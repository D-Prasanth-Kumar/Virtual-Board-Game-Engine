package com.virtualboard.games.tictactoe;

import com.virtualboard.engine.Move;
import com.virtualboard.players.Player;

import java.util.*;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter name of the First Player: ");
        Player p1 = new Player(1, in.nextLine());
        System.out.println("Enter name of the Second Player: ");
        Player p2 = new Player(2, in.nextLine());

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        TicTacToeManager manager = new TicTacToeManager(players);

        System.out.println("Tic-Tac-Toe starting. Players: " + p1.getName() + " (X), " + p2.getName() + " (O)");
        while (!manager.isGameOver()) {
            com.virtualboard.players.Player current = manager.getCurrentPlayer();
           // manager.getBoard().display();
            System.out.println(current.getName() + "'s turn. Enter row and col (0-2): ");
            int row = in.nextInt();
            int col = in.nextInt();

            String symbol = (manager.getCurrentPlayer() == p1) ? "X" : "O";
            TicTacToePiece piece = new TicTacToePiece(current, row, col, symbol);
            Move move = new Move(current, piece, -1, -1, row, col);

            manager.playMove(move);
        }
        in.close();
    }
}
