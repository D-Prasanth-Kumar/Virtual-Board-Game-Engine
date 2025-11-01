package com.virtualboard;

import com.virtualboard.games.snakesandladders.SnakeLadderMain;
import com.virtualboard.games.tictactoe.TicTacToeMain;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("WELCOME TO VIRTUAL BOARD GAME ENGINE ");
        System.out.println("Choose a game to play: ");
        System.out.println("1. TicTacToe");
        System.out.println("2. Snakes and Ladders");

        int choice = in.nextInt();
        in.nextLine();

        switch(choice) {
            case 1:
                TicTacToeMain.main(new String[]{});
                break;
            case 2:
                SnakeLadderMain.main(new String[]{});
                break;
            default:
                System.out.println("Invalid choice!");
        }
        in.close();
    }
}