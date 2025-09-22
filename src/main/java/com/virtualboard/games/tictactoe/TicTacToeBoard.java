package com.virtualboard.games.tictactoe;

import com.virtualboard.engine.Board;

public class TicTacToeBoard extends Board {
    public TicTacToeBoard() {
        super(3, 3);
    }

    public void display() {
        for(int i = 0; i < getRows(); i++) {
            for(int j = 0; j < getCols(); j++) {
                String v = getCell(i, j);
                System.out.print(" " + (v == null ? "-" : v) + " ");
                if(j < getCols() - 1) System.out.print("|");
            }
            System.out.println();
            if (i < getRows() - 1) System.out.println("---+---+---");
        }
        System.out.println();
    }
}
