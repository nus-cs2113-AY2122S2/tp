package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {

    @Test
    void listCommand_emptyList_expectException() {
        ListCommand listCommandTest = new ListCommand();
        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

}