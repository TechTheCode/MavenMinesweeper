package org.example;

import java.util.Random;

public class GameBoard {
    private final int rows;
    private final int columns;
    private final int[][] board;
    private final boolean[][] revealed;
    private final boolean[][] flagged;
    private final int mineCount;

    public GameBoard(int rows, int columns, int mineCount) {
        this.rows = rows;
        this.columns = columns;

        this.mineCount = mineCount;
        board = new int[rows][columns];
        revealed = new boolean[rows][columns];
        flagged = new boolean[rows][columns];
    }


    public void placeMines(int safeX, int safeY) {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int x = random.nextInt(rows);
            int y = random.nextInt(columns);

            if ((x != safeX || y != safeY) && !isAdjacent(x, y, safeX, safeY) && board[x][y] != -1) {
                board[x][y] = -1; // Place a mine, specific tile is set to -1 to show it's a mine
                minesPlaced++;
                updateAdjacentCells(x, y);
            }
        }
    }

    public boolean isAdjacent(int x, int y, int targetX, int targetY) {
        return Math.abs(x - targetX) <= 1 && Math.abs(y - targetY) <= 1;
    }

    private void initializeBoard() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int x = random.nextInt(rows);
            int y = random.nextInt(columns);

            if (board[x][y] != -1) {
                board[x][y] = -1; // -1 represents a mine
                minesPlaced++;
                updateAdjacentCells(x, y);
            }
        }
    }

    private void updateAdjacentCells(int x, int y) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < rows && ny >= 0 && ny < columns && board[nx][ny] != -1) {
                    board[nx][ny]++;
                }
            }
        }
    }

    public boolean isMine(int x, int y) {
        return board[x][y] == -1;
    }

    public boolean isRevealed(int x, int y) {
        return revealed[x][y];
    }

    public void reveal(int x, int y) {
        revealed[x][y] = true;
    }

    public void toggleFlag(int x, int y) {
        flagged[x][y] = !flagged[x][y];
    }

    public boolean isFlagged(int x, int y) {
        return flagged[x][y];
    }


    public void printBoard() {

        System.out.println("Total Mines: " + mineCount + " | Flags Placed: " + countFlags());
        System.out.println();

        // Print column labels
        System.out.print("  "); // Spacing for row labels is double whitespace
        for (int i = 0; i < columns; i++) {
            if (i < 10) {
                System.out.printf("  %d", i);
            } else {
                System.out.printf(" %d", i);
            }
        }
        System.out.println();

        // Print each row with a row label
        for (int i = 0; i < rows; i++) {
            if (i < 10) {
                System.out.print(i + " "); // Row label
            } else {
                System.out.print(i);
            }
            for (int j = 0; j < columns; j++) {
                System.out.print("[");
                if (revealed[i][j]) {
                    if (board[i][j] == 0) {
                        // Green for 0
                        System.out.print("\033[32m" + board[i][j] + "\033[0m");
                    } else if (board[i][j] == -1) {
                        // Blue for bombs
                        System.out.print("\033[34m*\033[0m");
                    } else {
                        // Yellow for numbers larger than 0
                        System.out.print("\033[33m" + board[i][j] + "\033[0m");
                    }
                } else if (flagged[i][j]) {
                    // Red for flags
                    System.out.print("\033[31mF\033[0m");
                } else {
                    System.out.print(" ");
                }
                System.out.print("]");  // Close square bracket
            }
            System.out.println();
        }
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    public void revealAllMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == -1) { // if it's a Mines
                    revealed[i][j] = true;
                }
            }
        }
    }

    public void revealAdjacentZeros(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= columns || revealed[x][y] || flagged[x][y]) {
            return; // Do not reveal if the cell is out of bounds, already revealed, or flagged
        }

        revealed[x][y] = true;

        if (board[x][y] == 0) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        revealAdjacentZeros(x + dx, y + dy);
                    }
                }
            }
        }
    }
    public int getCellValue(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < columns) {
            return board[x][y];
        }
        return -1; // Invalid coordinates or cell contains a mine
    }
    public int countFlags() {
        int flagCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (flagged[i][j]) {
                    flagCount++;
                }
            }
        }
        return flagCount;
    }

    public int getMineCount() {
        return this.mineCount;
    }

    public void placeMine(int x, int y) {
        board[x][y]=-1;
    }
}
