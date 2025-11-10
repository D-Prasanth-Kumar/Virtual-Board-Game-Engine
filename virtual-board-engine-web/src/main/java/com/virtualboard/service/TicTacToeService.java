package com.virtualboard.service;

import com.virtualboard.engine.Move;
import com.virtualboard.games.tictactoe.TicTacToeBoard;
import com.virtualboard.games.tictactoe.TicTacToeManager;
import com.virtualboard.games.tictactoe.TicTacToePiece;
import com.virtualboard.players.Player;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicTacToeService {

    private TicTacToeManager manager;
    private List<Player> players;

    public Map<String, Object> startGame(String player1, String player2) {
        Player p1 = new Player(1, player1);
        Player p2 = new Player(2, player2);
        players = new ArrayList<>(Arrays.asList(p1, p2));

        manager = new TicTacToeManager(players);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Game started successfully!");
        response.put("board", getBoardState());
        response.put("currentPlayer", manager.getCurrentPlayer().getName());
        return response;
    }

    public Map<String, Object> makeMove(int row, int col) {
        Map<String, Object> response = new HashMap<>();

        if(manager == null) {
            response.put("error", "No active game. Please start a new one.");
            return response;
        }

        if(manager.isGameOver()) {
            response.put("error", "Game is already Over.");
            return response;
        }

        Player current = manager.getCurrentPlayer();
        String symbol = (current.getPlayerId() == 1) ? "X" : "O";

        TicTacToePiece piece = new TicTacToePiece(current, row, col, symbol);
        Move move = new Move(current, piece, -1, -1, row, col);

        manager.playMove(move);

        response.put("board", getBoardState());
        response.put("currentPlayer", manager.isGameOver() ? null : manager.getCurrentPlayer().getName());
        response.put("Winner", manager.isGameOver() ? current.getName() : null);
        response.put("GameOver", manager.isGameOver());

        return response;
    }

    public Map<String, Object> getGameState() {
        Map<String, Object> state = new HashMap<>();

        if(manager == null) {
            state.put("error", "No active game yet.");
            return state;
        }

        state.put("board", getBoardState());
        state.put("currentPlayer", manager.getCurrentPlayer().getName());
        state.put("GameOver", manager.isGameOver());
        return state;
    }

    private String[][] getBoardState() {
        TicTacToeBoard board = (TicTacToeBoard) manager.getBoard();
        int rows = board.getRows();
        int cols = board.getCols();
        String[][] state = new String[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; i < cols; j++) {
                String val = board.getCell(i, j);

                state[i][j] = (val == null) ? "-" : val;
            }
        }

        return state;
    }

}
