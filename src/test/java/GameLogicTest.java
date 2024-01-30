import org.example.GameBoard;
import org.example.GameLogic;
import org.junit.jupiter.api.*;

public class GameLogicTest {
    @Test
    public void testValidMove() {
        GameLogic gameLogic = new GameLogic(new GameBoard(8, 8, 10));
        gameLogic.makeMove(5, 5); // Assuming this is a valid move

        Assertions.assertFalse(gameLogic.isGameOver(), "Game should not be over after a valid move");
        Assertions.assertFalse(gameLogic.hasWon(), "Game should not be won after just one valid move");
    }
    @Test
    public void testGameStart() {
        GameLogic gameLogic = new GameLogic(new GameBoard(8, 8, 10));
        Assertions.assertFalse(gameLogic.isGameOver(), "Game should not be over at start");
        Assertions.assertFalse(gameLogic.hasWon(), "Game should not be won at start");
    }
    @Test
    public void testWinCondition() {
        GameLogic gameLogic = new GameLogic(new GameBoard(1, 1, 0));
        gameLogic.makeMove(0, 0);
        Assertions.assertTrue(gameLogic.hasWon(), "Game should be won when all non-mine tiles are revealed");
    }


}
