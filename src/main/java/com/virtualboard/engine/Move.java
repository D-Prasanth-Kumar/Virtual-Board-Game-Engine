package com.virtualboard.engine;

import com.virtualboard.players.Player;

public class Move {
    private Player player;
    private Piece piece;
    private int fromRow, fromCol;
    private int toRow, toCol;


    public Move(Player player, Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        this.player = player;
        this.piece = piece;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public Player getPlayer() {
        return player;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public String toString() {
        return player.getName() + " moved" + piece.getClass().getSimpleName() + " from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ")";
    }
}
