package com.virtualboard.games.tictactoe;

import com.virtualboard.engine.Board;
import com.virtualboard.engine.GameManager;
import com.virtualboard.engine.Move;
import com.virtualboard.engine.Piece;
import com.virtualboard.players.Player;

import java.util.List;

public class TicTacToeManager extends GameManager {
    public TicTacToeManager(List<Player> players) {
        super(new TicTacToeBoard(), players);
    }

    @Override
    protected boolean isValidMove(Move move) {
        Piece p = move.getPiece();
        return p != null && p.isValidMove(move.getToRow(), move.getToCol(), board);
    }

    @Override
    protected boolean isWinningCondition() {
        Board b = board;
        for (int i = 0; i < b.getRows(); i++) {
            String a = b.getCell(i, 0), bb = b.getCell(i, 1), c = b.getCell(i, 2);
            if (a != null && !a.equals("-") && a.equals(bb) && a.equals(c)) return true;
        }
        for (int j = 0; j < b.getCols(); j++) {
            String a = b.getCell(0, j), bb = b.getCell(1, j), c = b.getCell(2, j);
            if (a != null && !a.equals("-") && a.equals(bb) && a.equals(c)) return true;
        }
        String d1 = b.getCell(0,0), d2 = b.getCell(1,1), d3 = b.getCell(2,2);
        if (d1 != null && !d1.equals("-") && d1.equals(d2) && d1.equals(d3)) return true;
        String e1 = b.getCell(0,2), e2 = b.getCell(1,1), e3 = b.getCell(2,0);
        if (e1 != null && !e1.equals("-") && e1.equals(e2) && e1.equals(e3)) return true;

        return false;
    }

    @Override
    public void playMove(Move move) {
        if (gameOver) {
            System.out.println("Game already ended!");
            return;
        }

        if (!isValidMove(move)) {
            System.out.println("Invalid move by: " + move.getPlayer().getName());
            return;
        }

        if (move.getPiece() instanceof TicTacToePiece) {
            TicTacToePiece tPiece = (TicTacToePiece) move.getPiece();
            board.setCell(move.getToRow(), move.getToCol(), tPiece.getSymbol());
            tPiece.setPosition(move.getToRow(), move.getToCol());
        } else {
            board.setCell(move.getToRow(), move.getToCol(), "X");
            move.getPiece().setPosition(move.getToRow(), move.getToCol());
        }

        board.display();

        if (isWinningCondition()) {
            System.out.println(move.getPlayer().getName() + " wins!");
            gameOver = true;
            return;
        }

        if (isBoardFull()) {
            System.out.println("Game ended in a draw!");
            gameOver = true;
            return;
        }

        nextTurn();
    }

    private boolean isBoardFull() {
        Board b = board;

        for(int i = 0; i < b.getRows(); i++) {
            for(int j = 0; j < b.getCols(); j++) {
                String val = b.getCell(i, j);
                if (val == null || val.equals("-")) return false;
            }
        }
        return true;
    }
}
