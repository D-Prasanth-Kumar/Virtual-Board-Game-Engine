package com.virtualboard.games.snakesandladders;

import java.util.HashMap;
import java.util.Map;

public class SnakeLadderBoard {
    private final int size = 100;

    private final Map<Integer, Integer> specialCells = new HashMap<>();

    public SnakeLadderBoard() {
        initializeBoard();
    }

    public int getSize() {
        return size;
    }

    private void initializeBoard() {
        // Ladders (Upward)
        addLadder(3, 22);
        addLadder(8, 26);
        addLadder(20, 29);

        // Snakes (Downward)
        addSnake(97, 78);
        addSnake(95, 56);
        addSnake(88, 24);
    }

    private void addSnake(int from, int to) {
        if (from > to) {
            specialCells.put(from, to);
        }
    }

    private void addLadder(int from, int to) {
        if (from < to) {
            specialCells.put(from, to);
        }
    }

    public int getNewPosition(int position) {
        if (specialCells.containsKey(position)) {
            int finalPos = specialCells.get(position);

            if (finalPos > position) {
                System.out.println("[BOARD] LADDER! Climbing from " + position + " to " + finalPos);
            } else {
                System.out.println("[BOARD] SNAKE! Sliding from " + position + " to " + finalPos);
            }
            return finalPos;
        }
        return position;
    }

    public void printBoard() {
        System.out.println("--- Snakes & Ladders Board Layout ---");
        specialCells.forEach((start, end) -> {
            if (start > end) {
                System.out.println("SNAKE:  " + start + " -> " + end);
            } else {
                System.out.println("LADDER: " + start + " -> " + end);
            }
        });
    }
}