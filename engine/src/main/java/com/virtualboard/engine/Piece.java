package com.virtualboard.engine;

import com.virtualboard.players.Player;

public abstract class Piece {
    private Player owner;
    private int row;
    private int col;

    public Piece(Player owner, int row, int col) {
        this.owner = owner;
        this.row = row;
        this.col = col;
    }

    public Player getOwner() {
        return owner;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract boolean isValidMove(int newRow, int newCol, Board board);
}
