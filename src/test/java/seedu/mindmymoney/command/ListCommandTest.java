package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    @Test
    void listToString_happyPath_expectString() {
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

    @Test
    void listCommand_emptyList_expectException() {
        ListCommand listCommandTest = new ListCommand();
        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }
}
