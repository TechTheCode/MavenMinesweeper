import org.example.GameBoard;
import org.example.GameLogic;
import org.junit.jupiter.api.*;

public class GameLogicTest {
    @Test
    public void testValidMove() {
        GameLogic gameLogic = new GameLogic(new GameBoard(10, 10, 10));
        gameLogic.makeMove(5, 5); // Assuming this is a valid move

        Assertions.assertFalse(gameLogic.isGameOver(), "Game should not be over after a valid move");
        Assertions.assertFalse(gameLogic.hasWon(), "Game should not be won after just one valid move");
    }
}
