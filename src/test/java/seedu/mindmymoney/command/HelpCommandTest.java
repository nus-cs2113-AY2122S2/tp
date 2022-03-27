package seedu.mindmymoney.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;

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
    void helpCommand_fromUser_expectHelpPage() throws MindMyMoneyException {
        String helpPage = "---------------------------------------Help Page---------------------------------------\n"
                + "1. Listing all Expenditures: list /expenses\n"
                + "2. Adding an Expenditure entry: add /e [EXPENDITURE] /c [CATEGORY] "
                + "/d [DESCRIPTION] /a [AMOUNT] /t [TIME]\n"
                + "3. Calculating the total expenditure in a month: calculate /epm [MONTH]\n"
                + "4. Updating an Expenditure entry: update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]\n"
                + "5. Updating an Expenditure entry with category: update [INDEX] [NEW_DESCRIPTION] -c "
                + "[NEW_CATEGORY] [NEW_AMOUNT]\n"
                + "6. Removing an Expenditure entry: delete [INDEX]\n"
                + "7. Exiting the program: bye\n"
                + "---------------------------------------------------------------------------------------\n";

        new HelpCommand(true, "/expenses").executeCommand();
        assertEquals(helpPage.trim(), capturedOut.toString().trim());
    }

    /**
     * Asserts that the error message will be printed if an invalid command is received.
     */
    @Test
    void helpCommand_notFromUser_expectErrorMessage() throws MindMyMoneyException {
        String errorMessage = "Invalid command!\n"
                + "Type \"help /expenses\" to see the list of supported expenditure commands\n"
                + "Use \"help /cc\" to view list of all supported Credit Card commands"
                + System.lineSeparator();

        new HelpCommand(false, "/expenses").executeCommand();
        assertEquals(errorMessage.trim(), capturedOut.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdout);
    }
}
