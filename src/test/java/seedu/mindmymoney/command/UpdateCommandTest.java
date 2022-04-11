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

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;

public class UpdateCommandTest {
    /**
     * Assert that the command can update the expenditure list.
     */
    @Test
    void updateExpenditureCommand_updateExpenditure_listUpdated() {
        Expenditure testExpenditure = new Expenditure("Cash", "Food",
            "porridge", 5, "01/04/2022");
        Expenditure newExpenditure = new Expenditure("Cash", "Others",
            "chicken rice", (float) 4.50, "01/05/2021");
        User testUser = new User();
        testUser.setExpenditureListArray(new ExpenditureList());
        testUser.getExpenditureListArray().add(testExpenditure);
        String input = "/e 1 /pm cash /c Others /d chicken rice /a 4.50 /t 01/05/2021";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        try {
            updateCommand.executeCommand();
            assertEquals(testUser.getExpenditureListArray().get(INDEX_OF_FIRST_ITEM),
                newExpenditure);
        } catch (MindMyMoneyException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Assert that an invalid update expenditure command will throw an exception.
     */
    @Test
    void updateExpenditureCommand_invalidInput_exceptionThrown() {
        Expenditure testExpenditure = new Expenditure("Cash", "Food",
            "porridge", 5, "01/03/2022");
        User testUser = new User();
        testUser.setExpenditureListArray(new ExpenditureList());
        testUser.getExpenditureListArray().add(testExpenditure);
        String input = "invalid input";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that an invalid update expenditure command will throw an exception.
     */
    @Test
    void updateExpenditureCommand_invalidDate_exceptionThrown() {
        Expenditure testExpenditure = new Expenditure("Cash", "Food",
            "porridge", 5, "01/03/2022");
        User testUser = new User();
        testUser.setExpenditureListArray(new ExpenditureList());
        testUser.getExpenditureListArray().add(testExpenditure);
        String firstInputString = "1 /pm cash /c Personal /d Nike Shoes /a 500 /t 01/4/2022";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(firstInputString, testUser).executeCommand());
        String secondInputString = "1 /pm cash /c Personal /d Nike Shoes /a 500 /t 04/2022";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(secondInputString, testUser).executeCommand());
        String thirdInputString = "1 /pm cash /c Personal /d Nike Shoes /a 500 /t 2022";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(thirdInputString, testUser).executeCommand());

        String fourthInputString = "1 /pm cash /c Personal /d Nike Shoes /a 500 /t 38/14/2022";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(fourthInputString, testUser).executeCommand());

        String fifthInputString = "1 /pm cash /c Food /d Porridge /a 4.50 /t 31/11/2021";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(fifthInputString, testUser).executeCommand());

        String sixthInputString = "1 /pm cash /c Food /d Porridge /a 4.50 /t 29/02/2021";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(sixthInputString, testUser).executeCommand());

        String seventhInputString = "1 /pm cash /c Food /d Porridge /a 4.50 /t 30/02/2020";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(seventhInputString, testUser).executeCommand());

        String eighthInputString = "1 /pm cash /c Food /d Porridge /a 4.50 /t 31/04/2020";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(eighthInputString, testUser).executeCommand());
    }

    /**
     * Assert that when the update command fields are similar to the expenditure in the list, an exception is thrown.
     */
    @Test
    void updateExpenditureCommand_updateFieldSimilarToExpenditureInList_exceptionThrown() {
        Expenditure testExpenditure = new Expenditure("Cash", "Food",
            "porridge", 5, "01/04/2022");
        User testUser = new User();
        testUser.setExpenditureListArray(new ExpenditureList());
        testUser.getExpenditureListArray().add(testExpenditure);
        String input = "/e 1 /pm cash /c food /d porridge /a 5 /t 01/04/2022";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that the command can update the credit card list.
     */
    @Test
    void updateCreditCardCommand_updateCreditCard_listUpdated() {
        CreditCard testCreditCard = new CreditCard("UOB", 2, 1000);
        CreditCard newCreditCard = new CreditCard("DBS", 5, 2000);
        User testUser = new User();
        testUser.setCreditCardListArray(new CreditCardList());
        testUser.getCreditCardListArray().add(testCreditCard);
        String input = "/cc 1 /n DBS /cb 5 /cl 2000";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        try {
            updateCommand.executeCommand();
            assertEquals(testUser.getCreditCardListArray().get(INDEX_OF_FIRST_ITEM),
                newCreditCard);
        } catch (MindMyMoneyException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Assert that an invalid update credit card command will throw an exception.
     */
    @Test
    void updateCreditCardCommand_invalidInput_exceptionThrown() {
        CreditCard testCreditCard = new CreditCard("DBS", 2, 1000);
        User testUser = new User();
        testUser.setCreditCardListArray(new CreditCardList());
        testUser.getCreditCardListArray().add(testCreditCard);
        String input = "/cc invalid input";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that when the update command fields are similar to the credit card in the list, an exception is thrown.
     */
    @Test
    void updateCreditCardCommand_updateFieldSimilarToCreditCardInList_exceptionThrown() {
        CreditCard testCreditCard = new CreditCard("DBS", 2, 1000);
        User testUser = new User();
        testUser.setCreditCardListArray(new CreditCardList());
        testUser.getCreditCardListArray().add(testCreditCard);
        String input = "/cc 1 /n DBS /cb 2 /cl 1000";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that when the update command credit card limit is smaller than total expenditure on card,
     * an exception is thrown.
     */
    @Test
    void updateCreditCardCommand_newCreditCardLimitSmallerThanExpenditure_exceptionThrown()
        throws MindMyMoneyException {
        CreditCard testCreditCard = new CreditCard("DBS", 2, 1000);
        User testUser = new User();
        testUser.setCreditCardListArray(new CreditCardList());
        testUser.getCreditCardListArray().add(testCreditCard);
        String inputStringExpenditure = "/e /pm DBS /c Personal /d Nike Shoes /a 500 /t 28/02/2018";
        new AddCommand(inputStringExpenditure, testUser).executeCommand();
        String input = "/cc 1 /n DBS /cb 2 /cl 200";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that the command can update the income list.
     */
    @Test
    void updateIncomeCommand_updateIncome_listUpdated() {
        Income testIncome = new Income(1000, "Salary");
        Income newIncome = new Income(3000, "Salary");
        User testUser = new User();
        testUser.setIncomeListArray(new IncomeList());
        testUser.getIncomeListArray().add(testIncome);
        String input = "/i 1 /a 3000 /c salary";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        try {
            updateCommand.executeCommand();
            assertEquals(testUser.getIncomeListArray().get(INDEX_OF_FIRST_ITEM), newIncome);
        } catch (MindMyMoneyException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Assert that an invalid update income command will throw an exception.
     */
    @Test
    void updateIncomeCommand_invalidInput_exceptionThrown() {
        Income testIncome = new Income(1000, "Salary");
        User testUser = new User();
        testUser.setIncomeListArray(new IncomeList());
        testUser.getIncomeListArray().add(testIncome);
        String input = "/i invalid input";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that an update expenditure command with invalid index will throw an exception.
     */
    @Test
    void updateIncomeCommand_invalidIndexExpenditure_exceptionThrown() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringExpenditure = "/e /pm cash /c Personal /d Nike Shoes /a 500 /t 28/02/2018";
        String inputStringUpdate = "/e 2 /pm cash /c Personal /d Nike Shoes /a 10 /t 28/02/2018";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        UpdateCommand updateCommand = new UpdateCommand(inputStringUpdate, user);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that an update expenditure command with non-numerical amount will throw an exception.
     */
    @Test
    void updateIncomeCommand_nonNumericalAmountExpenditure_exceptionThrown() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringExpenditure = "/e /pm cash /c Personal /d Nike Shoes /a 500 /t 28/02/2018";
        String inputStringUpdate = "/e 1 /pm cash /c Personal /d Nike Shoes /a asd /t 28/02/2018";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        UpdateCommand updateCommand = new UpdateCommand(inputStringUpdate, user);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that an update credit card command with invalid index will throw an exception.
     */
    @Test
    void updateIncomeCommand_invalidIndexCreditCard_exceptionThrown() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringExpenditure = "/cc /n DBS /cb 2 /cl 1000";
        String inputStringUpdate = "/cc 2 /n DBS /cb 2 /cl 13000";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        UpdateCommand updateCommand = new UpdateCommand(inputStringUpdate, user);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that an update credit card command with non-numerical Cashback will throw an exception.
     */
    @Test
    void updateIncomeCommand_nonNumericalCashback_exceptionThrown() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringExpenditure = "/cc /n DBS /cb 2 /cl 1000";
        String inputStringUpdate = "/cc 1 /n DBS /cb asd /cl 13000";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        UpdateCommand updateCommand = new UpdateCommand(inputStringUpdate, user);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that an update income command with invalid index will throw an exception.
     */
    @Test
    void updateIncomeCommand_invalidIndexIncome_exceptionThrown() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringExpenditure = "/i /a 1000 /c salary";
        String inputStringUpdate = "/i 2 /a 100 /c salary";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        UpdateCommand updateCommand = new UpdateCommand(inputStringUpdate, user);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that an update income command with non numerical amount will throw an exception.
     */
    @Test
    void updateIncomeCommand_nonNumericalAmountIncome_exceptionThrown() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringExpenditure = "/i /a 1000 /c salary";
        String inputStringUpdate = "/i 1 /a asd /c salary";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        UpdateCommand updateCommand = new UpdateCommand(inputStringUpdate, user);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Assert that when the update command fields are similar to the income in the list, an exception is thrown.
     */
    @Test
    void updateIncomeCommand_updateFieldSimilarToIncomeInList_exceptionThrown() {
        Income testIncome = new Income(1000, "Salary");
        User testUser = new User();
        testUser.setIncomeListArray(new IncomeList());
        testUser.getIncomeListArray().add(testIncome);
        String input = "/i 1 /a 1000 /c salary";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }

    /**
     * Test if program is able to exit.
     */
    @Test
    void updateCommand_isExit_expectFalse() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringExpenditure = "/e /pm cash /c Personal /d Nike Shoes /a 500 /t 12/02/2018";
        String inputStringUpdate = "/e 1 /pm cash /c Personal /d Nike Shoes /a 500 /t 12/02/2022";
        new AddCommand(inputStringExpenditure, user).executeCommand();
        assertEquals(false, new UpdateCommand(inputStringUpdate, user).isExit());

    }
}
