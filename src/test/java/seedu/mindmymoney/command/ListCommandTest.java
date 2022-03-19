package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    /**
     * Tests list command on a non-empty list. Prints list of size 1 first, followed by list of size 2, to check for
     * formatting
     */
    @Test
    void listToString_normalInputs_expectString() throws MindMyMoneyException{
        ExpenditureList expenditureTestList = new ExpenditureList();
        String inputString = "expenditure 12345";
        new AddCommand(inputString, expenditureTestList).executeCommand();
        String listInString = new ListCommand(expenditureTestList).listToString();
        assertEquals("1. $12345 on expenditure\n", listInString);

        String inputString2 = "expenditure2 54321";
        new AddCommand(inputString2, expenditureTestList).executeCommand();
        listInString = new ListCommand(expenditureTestList).listToString();
        assertEquals("1. $12345 on expenditure\n"
                + "2. $54321 on expenditure2\n", listInString);
    }

    /**
     * Tests list command on an empty list. Should expect an exception thrown
     */
    @Test
    void listCommand_emptyList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        ListCommand listCommandTest = new ListCommand(expenditureTestList);
        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }
}
