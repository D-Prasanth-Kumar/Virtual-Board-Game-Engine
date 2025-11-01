package com.virtualboard.engine;

public class Board {
    private int rows;
    private int cols;
    private String[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new String[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                grid[i][j] = "-";
            }
        }
    }

    public void setCell(int row, int col, String val) {
        if(row >= 0 && row < rows && col >= 0 & col < cols) {
            grid[row][col] = val;
        }
    }

    public String getCell(int row, int col) {
        if(row >= 0 && row < rows & col >= 0 && col < cols) {
            return grid[row][col];
        }
        return null;
    }

    public void display() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
