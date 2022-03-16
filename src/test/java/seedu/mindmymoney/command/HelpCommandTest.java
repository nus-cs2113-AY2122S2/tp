package seedu.mindmymoney.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the tests for Help command.
 */
public class HelpCommandTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    /**
     * Asserts that the help page will be printed when the user requests for it.
     */
    @Test
    void helpCommand_fromUser_expectHelpPage() {
        String helpPage = "------------------------------------Help Page------------------------------------\n"
                + "1. Listing all Expenditures: list\n"
                + "2. Adding an Expenditure entry: add [DESCRIPTION] [AMOUNT]\n"
                + "3. Adding an Expenditure entry with category: add [DESCRIPTION] -c [CATEGORY] [AMOUNT]\n"
                + "4. Updating an Expenditure entry: update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]\n"
                + "5. Updating an Expenditure entry with category: update [INDEX] [NEW_DESCRIPTION] -c "
                + "[NEW_CATEGORY] [NEW_AMOUNT]\n"
                + "6. Removing an Expenditure entry: delete [INDEX]\n"
                + "7. Exiting the program: bye\n"
                + "---------------------------------------------------------------------------------\n";

        new HelpCommand(true).executeCommand();
        assertEquals(helpPage.trim(), capturedOut.toString().trim());
    }

    /**
     * Asserts that the error message will be printed if an invalid command is received.
     */
    @Test
    void helpCommand_notFromUser_expectErrorMessage() {
        String errorMessage = "Invalid command! Type \"help\" to see the list of supported commands"
                + System.lineSeparator();

        new HelpCommand(false).executeCommand();
        assertEquals(errorMessage.trim(), capturedOut.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdout);
    }
}
