package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByeCommandTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    /**
     * Asserts if command is able to exit.
     */
    @Test
    void byeCommand_isExit_expectByeMessage() {
        assertEquals(true, new ByeCommand().isExit());

    }

    public void tearDown() {
        System.setOut(stdout);
    }
}