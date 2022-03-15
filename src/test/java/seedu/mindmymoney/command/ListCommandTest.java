package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    /**
     * Tests list command on a non-empty list. Prints list of size 1 first, followed by list of size 2, to check for
     * formatting
     */
    @Test
    void listToString_normalInputs_expectString() {
        String inputString = "expenditure 12345";
        new AddCommand(inputString).executeCommand();
        String listInString = new ListCommand().listToString();
        assertEquals("1. $12345 on expenditure\n", listInString);

        String inputString2 = "expenditure2 54321";
        new AddCommand(inputString2).executeCommand();
        listInString = new ListCommand().listToString();
        assertEquals("1. $12345 on expenditure\n"
                + "2. $54321 on expenditure2\n", listInString);
    }

    /**
     * Tests list command on an empty list. Should expect an exception thrown
     */
    @Test
    void listCommand_emptyList_expectException() {
        ListCommand listCommandTest = new ListCommand();
        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }
}
