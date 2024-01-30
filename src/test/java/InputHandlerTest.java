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
    public void testValidIntInput(){
        String simulatedUserInput = "12";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        InputHandler inputHandler = new InputHandler();

        int result = inputHandler.readInt("Enter a number: ", 1, 12);
        Assertions.assertEquals(12, result, "The method should read the number correctly");

    }
    @Test
    public void testValidStringInput() {
        String input = "test";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputHandler inputHandler = new InputHandler();
        Assertions.assertEquals(input, inputHandler.readString(), "readString should return the user input string");
    }

    @Test
    public void testReadIntOutOfRangeInput() {
        //Enters 15 then 7
        String input = "15\n7";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputHandler inputHandler = new InputHandler();
        Assertions.assertEquals(7, inputHandler.readInt("Enter a number: ", 1, 10),
                "readInt should return the correct integer after out-of-range input");
    }

    @Test
    public void testReadIntNonIntegerInput() {
        //Enters "test" then 8
        String input = "test\n8";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputHandler inputHandler = new InputHandler();
        Assertions.assertEquals(8, inputHandler.readInt("Enter a number: ", 1, 10),
                "readInt should return the correct integer after non-integer input");
    }


}
