package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    /**
     * Tests list command on a non-empty list. Prints list of size 1 first, followed by list of size 2, to check for
     * formatting
     */
    @Test
    void listToString_normalInputs_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "/e cash /c Personal /d Nike Shoes /a 300 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        String listInString = new ListCommand(expenditureTestList).listToString();
        assertEquals("1. $300.0 on Nike Shoes from Personal\n", listInString);

        String inputString2 = "/e cash /c Food /d Cream Pie /a 69 /t 2022-03";
        new AddCommand(inputString2, expenditureTestList, creditCardTestList).executeCommand();
        listInString = new ListCommand(expenditureTestList).listToString();
        assertEquals("1. $300.0 on Nike Shoes from Personal\n"
                + "2. $69.0 on Cream Pie from Food\n", listInString);
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
