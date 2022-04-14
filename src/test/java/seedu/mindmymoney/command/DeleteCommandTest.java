package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.CreditCard;
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
    void deleteCommand_oneExpenditureInput_expectExpenditureListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("cash", "Personal", "Nike Shoes",
            300, "30/03/2022"));

        String deleteInputString = "delete /e 1";
        new DeleteCommand(deleteInputString, user).executeCommand();
        testList.remove(0);

        assertEquals(testList.size(), expenditureTestList.size());
    }

    /**
     * Asserts that the credit card balance will be updated upon successful deletion.
     *
     * @throws MindMyMoneyException if incorrect item has been deleted.
     */
    @Test
    void deleteCommand_creditCardAndExpenditureInput_expectOriginalCreditBalance() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringCC = "/cc /n DBS /cb 1.5 /cl 500";
        new AddCommand(inputStringCC, user).executeCommand();
        String inputStringExpenditure = "/e /pm DBS /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        assertEquals(200.0, creditCardTestList.get(0).getBalanceLeft());
        String deleteInputString = "delete /e 1";
        new DeleteCommand(deleteInputString, user).executeCommand();
        assertEquals(500.0, creditCardTestList.get(0).getBalanceLeft());
    }

    /**
     * Asserts that the correct item has been deleted.
     */
    @Test
    void deleteCommand_oneCreditCardInput_expectCreditCardListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/cc /n DBS /cb 1.5 /cl 500";
        new AddCommand(inputString, user).executeCommand();

        ArrayList<CreditCard> testList = new ArrayList<>();
        testList.add(new CreditCard("DBS", 1.5, 500));

        String deleteInputString = "delete /cc 1";
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
    void deleteCommand_oneIncomeInput_expectIncomeListUpdated() throws MindMyMoneyException {
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
    void deleteCommand_wrongExpenditureInputValue_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";

        new AddCommand(inputString, user).executeCommand();
        String deleteInputString = "delete /e -1";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
        String delInputString2 = "delete /e 5";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(delInputString2, user).executeCommand());
    }

    /**
     * Asserts if the index input is in the correct number format.
     */
    @Test
    void deleteCommand_wrongExpenditureInputFormat_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";

        new AddCommand(inputString, user).executeCommand();
        String delInputString = "delete /e ONE";

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(delInputString, user).executeCommand());
    }

    /**
     * Asserts if there is a missing index input in the command.
     */
    @Test
    void deleteCommand_missingExpenditureInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";

        new AddCommand(inputString, user).executeCommand();
        String deleteInputString = "delete /e";
        String deleteInputString2 = "delete /e";

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString2, user).executeCommand());
    }

    /**
     * Asserts if user is able to delete from an empty expenditure list.
     */
    @Test
    void deleteCommand_deleteFromEmptyExpenditureList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String deleteInputString = "delete /e 1";

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to delete from an empty credit card list.
     */
    @Test
    void deleteCommand_deleteFromEmptyCreditCardList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String deleteInputString = "delete /cc 1";

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to delete from an empty income list.
     */
    @Test
    void deleteCommand_deleteFromEmptyIncomeList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String deleteInputString = "delete /i 1";

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Asserts if there is a missing index input in the command.
     */
    @Test
    void deleteCommand_missingCreditCardInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/cc /n DBS /cb 1.5 /cl 500";

        new AddCommand(inputString, user).executeCommand();
        String deleteInputString = "delete /cc";

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Asserts if there is a missing index input in the command.
     */
    @Test
    void deleteCommand_missingIncomeInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String deleteInputString = "delete /i";
        String inputString = "/i /a 3000 /c salary";
        new AddCommand(inputString, user).executeCommand();

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Asserts if the credit card index input is out of bounds.
     */
    @Test
    void deleteCommand_wrongCreditCardInputValue_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/cc /n DBS /cb 1.5 /cl 500";

        new AddCommand(inputString, user).executeCommand();
        String deleteInputString = "delete /cc -1";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
        String delInputString2 = "delete /cc 3";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(delInputString2, user).executeCommand());
    }

    /**
     * Asserts if the credit card index input can be non-numerical.
     */
    @Test
    void deleteCommand_nonNumericalCreditCardIndexInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/cc /n DBS /cb 1.5 /cl 500";

        new AddCommand(inputString, user).executeCommand();
        String deleteInputString = "delete /cc asd";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Asserts if the income index input is out of bounds.
     */
    @Test
    void deleteCommand_wrongIncomeInputValue_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/i /a 3000 /c salary";

        new AddCommand(inputString, user).executeCommand();
        String deleteInputString = "delete /i -1";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
        String delInputString2 = "delete /i 3";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(delInputString2, user).executeCommand());
    }

    /**
     * Asserts if there is a missing flag in the command.
     */
    @Test
    void deleteCommand_missingFlag_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String deleteInputString = "delete";

        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Asserts if the income index input can be not a number.
     */
    @Test
    void deleteCommand_nonNumericalIncomeIndexInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/i /a 3000 /c salary";
        new AddCommand(inputString, user).executeCommand();

        String deleteInputString = "delete /i abc";
        assertThrows(MindMyMoneyException.class, () -> new DeleteCommand(deleteInputString, user).executeCommand());
    }

    /**
     * Test if program is able to exit.
     */
    @Test
    void deleteCommand_isExit_expectFalse() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/i /a 3000 /c salary";
        new AddCommand(inputString, user).executeCommand();

        String deleteInputString = "delete /i 1";
        assertEquals(false, new DeleteCommand(deleteInputString, user).isExit());

    }

}
