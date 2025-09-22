package com.virtualboard.games.tictactoe;

import com.virtualboard.engine.Board;
import com.virtualboard.engine.Piece;
import com.virtualboard.players.Player;

public class TicTacToePiece extends Piece {
    private final String symbol;

    public TicTacToePiece(Player owner, int row, int col, String symbol) {
        super(owner, row, col);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Board board) {
        String cell = board.getCell(newRow, newCol);
        return cell != null && cell.equals("-");
    }
}
