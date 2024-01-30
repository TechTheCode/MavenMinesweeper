package org.example;

public class Minesweeper {
    private static InputHandler inputHandler = new InputHandler();
    private static boolean terminal = true;

    private static int[] getDifficultySettings() {
        System.out.println("Select difficulty level:");
        System.out.println("1. Beginner (8x8 with 10 mines)");
        System.out.println("2. Intermediate (16x16 with 40 mines)");
        System.out.println("3. Expert (30x16 with 99 mines)");
        System.out.println("4. Custom Game (Maximum of 30 rows and 24 columns with any amount of mines)");
        int choice = inputHandler.readInt("Enter your choice: ", 1, 4);

        switch (choice) {
            case 1:
                return new int[]{8, 8, 10};
            case 2:
                return new int[]{16, 16, 40};
            case 3:
                return new int[]{30, 16, 99};
            case 4:
                // Inputs for custom games
                int rows = inputHandler.readInt("Enter rows (max 30):", 1, 30);
                int columns = inputHandler.readInt("Enter columns (max 24):", 1, 24);
                int mines = inputHandler.readInt("Enter number of mines (max "
                        + (rows * columns - 1) + "):", 0, (rows * columns) - 1);
                return new int[]{rows, columns, mines};
            default:
                System.out.println("Invalid choice. Defaulting to Beginner.");
                return new int[]{8, 8, 10};
        }
    }

    public static void main(String[] args) {
        while (terminal) {
            int[] settings = getDifficultySettings();
            GameBoard board = new GameBoard(settings[0], settings[1], settings[2]);
            GameLogic game = new GameLogic(board);

            while (!game.isGameOver() && !game.hasWon()) {
                board.printBoard();

                System.out.println("Enter your action ('r' to reveal, 'f' to flag, 'End Game' to end the game): ");
                String action = inputHandler.readCommand(new String[]{"r", "f", "end game"});

                if ("end game".equalsIgnoreCase(action)) {
                    break; // Breaks the inner while loop to end the game
                }

                int row = inputHandler.readInt("Enter row: ", 0, board.getRows() - 1);
                int col = inputHandler.readInt("Enter column: ", 0, board.getColumns() - 1);

                if ("r".equalsIgnoreCase(action)) {
                    game.makeMove(row, col);
                } else if ("f".equalsIgnoreCase(action)) {
                    game.toggleFlag(row, col);
                }
            }
            if (game.isGameOver()) {
                game.revealAllMines();
                board.printBoard();
            }

            // Logic to handle the end of the game
            System.out.println(game.hasWon() ? "Congratulations, you \033[32mWON\033[0m!" :
                    "\033[31mGame over!\033[0m");
            long totalTime = game.getTimeTaken();
            System.out.println("Total time: " + totalTime + " seconds");
            System.out.println("Do you want to play again? (yes/no)");
            String playAgain = inputHandler.readString();
            inputHandler.consumeNextLine();
            if (!"yes".equalsIgnoreCase(playAgain)) {
                terminal = false;
            }
        }
    }

}
