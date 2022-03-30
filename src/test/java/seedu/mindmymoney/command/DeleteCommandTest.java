package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.Income;
import seedu.mindmymoney.userfinancial.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("cash", "Personal", "Nike Shoes",
                300, "30/03/2022"));

        String deleteInputString = "delete 1";
        new DeleteCommand(deleteInputString, user).executeCommand();
        testList.remove(0);

        assertEquals(testList.size(), expenditureTestList.size());
    }

    /**
     * Asserts that the correct income entry is deleted.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    @Test
    void deleteCommand_oneIncomeInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/i /a 3000 /c salary";
        new AddCommand(inputString, user).executeCommand();

        ArrayList<Income> testList = new ArrayList<>();
        testList.add(new Income(3000, "Salary"));

        String deleteInputString = "delete /i 1";
        new DeleteCommand(deleteInputString, user).executeCommand();
        testList.remove(INDEX_OF_FIRST_ITEM);

        assertEquals(testList.size(), expenditureTestList.size());
    }

    /**
     * Asserts if the index input is out of bounds.
     */
    @Test
    void deleteCommand_wrongInputValue_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";

        new AddCommand(inputString, user).executeCommand();
        String deleteInputString = "delete 0";
        assertThrows(MindMyMoneyException.class,
            () -> new DeleteCommand(deleteInputString, user).executeCommand());
        String delInputString2 = "delete 5";
        assertThrows(MindMyMoneyException.class,
            () -> new DeleteCommand(delInputString2, user).executeCommand());
    }

    /**
     * Asserts if the index input is in the correct number format.
     */
    @Test
    void deleteCommand_wrongInputFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String delInputString = "delete ONE";

        assertThrows(MindMyMoneyException.class,
            () -> new DeleteCommand(delInputString, user).executeCommand());
    }

    /**
     * Asserts if there is a missing index input in the command.
     */
    @Test
    void deleteCommand_missingInput_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String deleteInputString = "delete";
        String delInputString2 = "delete ";

        assertThrows(MindMyMoneyException.class,
            () -> new DeleteCommand(deleteInputString, user).executeCommand());
        assertThrows(MindMyMoneyException.class,
            () -> new DeleteCommand(delInputString2, user).executeCommand());
    }

    /**
     * Asserts if user is able to delete from an empty list.
     */
    @Test
    void deleteCommand_addToEmptyList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String deleteInputString = "delete 1";

        assertThrows(MindMyMoneyException.class,
            () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }
}
