package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.Lists;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Represents the tests for delete command.
 */
class DeleteCommandTest {

    /**
     * Asserts that the correct item has been deleted.
     *
     * @throws MindMyMoneyException if incorrect item has been deleted.
     */
    @Test
    void deleteCommand_oneInput_expectListUpdated() throws MindMyMoneyException {
        Lists itemList = new Lists();
        String inputString = "expenditure 12345";
        ArrayList<Expenditure> testList = new ArrayList<>();
        new AddCommand(inputString, itemList).executeCommand();
        testList.add(new Expenditure("expenditure", 12345));
        String deleteInputString = "delete 1";
        new DeleteCommand(deleteInputString, itemList).executeCommand();
        testList.remove(0);
        assertEquals(testList.size(), itemList.size());
    }

    /**
     * Asserts if the index input is out of bounds.
     */
    @Test
    void deleteCommand_wrongInputValue_expectException() {
        Lists itemList = new Lists();
        String inputString = "expenditure 12345";
        new AddCommand(inputString, itemList).executeCommand();
        String deleteInputString = "delete 0";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, itemList).executeCommand());
        String delInputString2 = "delete 5";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(delInputString2, itemList).executeCommand());
    }

    /**
     * Asserts if the index input is in the correct number format.
     */
    @Test
    void deleteCommand_wrongInputFormat_expectException() {
        Lists itemList = new Lists();
        String delInputString = "delete ONE";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(delInputString, itemList).executeCommand());
    }

    /**
     * Asserts if there is a missing index input in the command.
     */
    @Test
    void deleteCommand_missingInput_expectException() {
        Lists itemList = new Lists();
        String deleteInputString = "delete";
        String delInputString2 = "delete ";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, itemList).executeCommand());
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(delInputString2, itemList).executeCommand());
    }

    /**
     * Asserts if user is able to delete from an empty list.
     *
     */
    @Test
    void deleteCommand_addToEmptyList_expectException() {
        Lists itemList = new Lists();
        String deleteInputString = "delete 1";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, itemList).executeCommand());
    }
}
