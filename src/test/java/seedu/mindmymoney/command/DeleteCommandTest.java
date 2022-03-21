package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
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
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "expenditure 12345";
        ArrayList<Expenditure> testList = new ArrayList<>();
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        testList.add(new Expenditure(null, null, "expenditure", 12345, null));
        String deleteInputString = "delete 1";
        new DeleteCommand(deleteInputString, expenditureTestList).executeCommand();
        testList.remove(0);
        assertEquals(testList.size(), expenditureTestList.size());
    }

    /**
     * Asserts if the index input is out of bounds.
     */
    @Test
    void deleteCommand_wrongInputValue_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "expenditure 12345";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        String deleteInputString = "delete 0";
        assertThrows(MindMyMoneyException.class,
                () -> new DeleteCommand(deleteInputString, expenditureTestList).executeCommand());
        String delInputString2 = "delete 5";
        assertThrows(MindMyMoneyException.class,
                () -> new DeleteCommand(delInputString2, expenditureTestList).executeCommand());
    }

    /**
     * Asserts if the index input is in the correct number format.
     */
    @Test
    void deleteCommand_wrongInputFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        String delInputString = "delete ONE";
        assertThrows(MindMyMoneyException.class,
                () -> new DeleteCommand(delInputString, expenditureTestList).executeCommand());
    }

    /**
     * Asserts if there is a missing index input in the command.
     */
    @Test
    void deleteCommand_missingInput_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        String deleteInputString = "delete";
        String delInputString2 = "delete ";
        assertThrows(MindMyMoneyException.class,
                () -> new DeleteCommand(deleteInputString, expenditureTestList).executeCommand());
        assertThrows(MindMyMoneyException.class,
                () -> new DeleteCommand(delInputString2, expenditureTestList).executeCommand());
    }

    /**
     * Asserts if user is able to delete from an empty list.
     */
    @Test
    void deleteCommand_addToEmptyList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        String deleteInputString = "delete 1";
        assertThrows(MindMyMoneyException.class,
                () -> new DeleteCommand(deleteInputString, expenditureTestList).executeCommand());
    }
}
