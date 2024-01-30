import org.example.GameBoard;
import org.example.InputHandler;
import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
public class InputHandlerTest {
    private final InputStream originalIn = System.in;
    @AfterEach
    public void restoreInput() {
        System.setIn(originalIn);
    }
    @Test
    public void testReadInt(){
        String simulatedUserInput = "12";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        InputHandler inputHandler = new InputHandler();

        int result = inputHandler.readInt("Enter a number: ", 1, 12);
        Assertions.assertEquals(12, result, "The method should read the number correctly");

    }
}
