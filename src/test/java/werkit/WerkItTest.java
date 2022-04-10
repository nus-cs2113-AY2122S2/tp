package werkit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import storage.LogHandler;

import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class WerkItTest {
    private static final InputStream DEFAULT_STDIN = System.in;

    @AfterEach
    void rollbackStdIn() {
        System.setIn(DEFAULT_STDIN);
    }

    @Test
    void startContinuousUserPrompt_simpleExit_expectSuccess() throws IOException {
        String testCommand = "exit" + System.lineSeparator();
        ByteArrayInputStream testByteData = new ByteArrayInputStream(testCommand.getBytes());
        System.setIn(testByteData);

        LogHandler.startLogHandler();
        WerkIt werkIt = new WerkIt();

        String expectedOutput = "----------------------------------------------------------------------"
                + System.lineSeparator()
                + "Now then, what can I do for you today?"
                + System.lineSeparator()
                + "(Need help? Type 'help' for a guide!)"
                + System.lineSeparator()
                + "----------------------------------------------------------------------"
                + System.lineSeparator()
                + "> ----------------------------------------------------------------------"
                + System.lineSeparator()
                + "Thank you for using WerkIt! See you again soon..."
                + System.lineSeparator()
                + "======================================================================"
                + System.lineSeparator();
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        werkIt.startContinuousUserPrompt();
        assertEquals(expectedOutput, actualOutput.toString());
    }
}
