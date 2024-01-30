import org.example.GameBoard;
import org.junit.jupiter.api.*;
public class GameBoardTest {
    @Test
    public void testBeginnerBoardCreation(){
        int rows = 8;
        int columns = 8;
        int mineCount = 10;
        GameBoard gameBoard = new GameBoard(rows, columns, mineCount);
        Assertions.assertEquals(rows, gameBoard.getRows(), "The number of rows should match the provided value");
        Assertions.assertEquals(columns, gameBoard.getColumns(), "The number of columns should match the provided value");
        Assertions.assertEquals(mineCount, gameBoard.getMineCount(), "The mine count should match the provided value");
    }
    @Test
    public void testTileIsRevealed(){
        GameBoard board = new GameBoard(8, 8, 10);
        board.reveal(3, 3);
        Assertions.assertTrue(board.isRevealed(3, 3), "The tile at (3, 3) should be revealed");
        Assertions.assertFalse(board.isRevealed(4, 4), "The tile at (4, 4) should not be revealed");
    }

    @Test
    public void testTileIsMine(){
        GameBoard board = new GameBoard(8, 8, 10);
        board.placeMine(5, 5);
        Assertions.assertTrue(board.isMine(5, 5), "The tile at (3, 3) should be a mine");
        Assertions.assertFalse(board.isMine(4, 4), "The tile at (4, 4) should not be a mine");
    }

    @Test
    public void testIsTileAdjacent() {
        GameBoard board = new GameBoard(8, 8, 10);
        Assertions.assertTrue(board.isAdjacent(1, 1, 1, 2), "Tile (1, 1) should be adjacent to (1, 2)");
        Assertions.assertFalse(board.isAdjacent(1, 1, 3, 3), "Tile (1, 1) should not be adjacent to (3, 3)");
    }
    @Test
    public void testIsCellFlagged() {
        GameBoard board = new GameBoard(8, 8, 10);
        board.toggleFlag(4, 4);
        Assertions.assertTrue(board.isFlagged(4, 4), "The cell at (4, 4) should be flagged");
        Assertions.assertFalse(board.isFlagged(5, 5), "The cell at (5, 5) should not be flagged");
        board.toggleFlag(4, 4);
        Assertions.assertFalse(board.isFlagged(4, 4), "The cell at (5, 5) should not be flagged");
    }
}
