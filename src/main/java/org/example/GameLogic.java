package org.example;

public class GameLogic {
    private final GameBoard board;
    private boolean gameOver;
    private boolean won;
    private boolean firstMove = true;
    private GameTimer timer;

    public GameLogic(GameBoard board) {
        this.board = board;
        gameOver = false;
        won = false;
        this.timer = new GameTimer();
    }

    public void makeMove(int x, int y) {
        if (firstMove) {
            timer.start();//Start timer
            board.placeMines(x, y); // Place mines ensuring the first move is safe
            firstMove = false;
        }
        if (board.isRevealed(x, y)) {
            System.out.println("Tile is already revealed.");
            return;
        }
        if (board.isFlagged(x, y)) {
            System.out.println("Cannot reveal a flagged tile. Unflag it first.");
            return;
        }
        if (board.isMine(x, y)) {
            gameOver = true;
            endGame();
        } else {
            if (board.isRevealed(x, y) || board.isFlagged(x, y)) {
                return; // Ignore the move if the cell is already revealed or flagged
            }

            if (board.getCellValue(x, y) == 0) {
                board.revealAdjacentZeros(x, y);
            } else {
                board.reveal(x, y);
            }
            checkWin();
        }

        if (hasWon()) {
            endGame();
        }
    }

    public void endGame(){
        board.revealAllMines();  // Optionally, reveal bombs when the player wins
        timer.stop();
        long timeTaken = timer.getElapsedTimeInSeconds();
        //System.out.println("Time taken: " + timeTaken + " seconds");
    }
    public void toggleFlag(int x, int y) {
        if (board.isRevealed(x, y)) {
            System.out.println("Cannot flag a revealed tile.");
        } else {
            board.toggleFlag(x, y);
        }
    }

    private void checkWin() {
        won = true;
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (!board.isMine(i, j) && !board.isRevealed(i, j)) {
                    won = false;
                    return;
                }
            }
        }
    }

    public void revealAllMines() {
        board.revealAllMines();  // Assuming GameBoard has a method to reveal all mines
    }

    public long getTimeTaken() {
        return timer.getElapsedTimeInSeconds();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean hasWon() {
        return won;
    }

}
