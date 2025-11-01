package com.virtualboard.games.snakesandladders;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SnakeLadderBoard {
    private int size = 100;
    private Map<Integer, Integer> specialCells = new HashMap<>();
    private Random random = new Random();

    public SnakeLadderBoard() {
        initializeBoard();
    }

    public int getSize() {
        return size;
    }

    public int getNextPosition(int position) {
        return specialCells.getOrDefault(position, position);
    }

    private void addSnake(int from, int to) {
        if(from > to) {
            specialCells.put(from, to);
        }
    }

    private void addLadder(int from, int to) {
        if(from < to) {
            specialCells.put(from, to);
        }
    }

    private void initializeBoard() {
        addLadder(3, 22);
        addLadder(8, 26);
        addLadder(20, 29);

        addSnake(97, 78);
        addSnake(95, 56);
        addSnake(88, 24);
    }

    public void printBoard() {
        System.out.println("Snakes & Ladders Board: ");
        for(Map.Entry<Integer, Integer> entry : specialCells.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();

            if(start > end) {
                System.out.println("SNAKE ~ " + start + " -> " + end);
            }else {
                System.out.println("LADDER || " + start + " -> " + end);
            }
        }
    }
}
