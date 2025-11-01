package com.virtualboard.engine;

import com.virtualboard.players.Player;

import java.util.List;

public abstract class GameManager {
    protected Board board;
    protected List<Player> players;
    protected int currentTurn;
    protected boolean gameOver;

    public GameManager(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currentTurn = 0;
        this.gameOver = false;
    }

    public Player getCurrentPlayer() {
        return players.get(currentTurn);
    }

    protected void nextTurn() {
        currentTurn = (currentTurn + 1) % players.size();
    }

    public com.virtualboard.engine.Board getBoard() {
        return this.board;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    protected abstract boolean isValidMove(Move move);

    protected abstract boolean isWinningCondition();

    public void playMove(Move move) {
        if(gameOver) {
            System.out.println("Game already ended!");
            return;
        }

        if(!isValidMove(move)) {
            System.out.println("Invalid move by: " +  move.getPlayer().getName());
            return;
        }

        move.getPiece().setPosition(move.getToRow(), move.getToCol());
        board.setCell(move.getToRow(), move.getToCol(), move.getPiece().getClass().getSimpleName());

        board.display();

        if(isWinningCondition()) {
            System.out.println(move.getPlayer().getName() + " wins!");
            gameOver = true;
            return;
        }

        nextTurn();
    }
}
