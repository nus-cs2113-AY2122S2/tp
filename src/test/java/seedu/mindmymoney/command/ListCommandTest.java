package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    /**
     * Tests list command with no date on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithNoDate_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String firstInputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(firstInputString, user).executeCommand();
        String listInString = new ListCommand("/e", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);

        String secondInputString = "/e /pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(secondInputString, user).executeCommand();
        listInString = new ListCommand("/e", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.00 was spent on Cream Pie(Food) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);

        String listInString2 = new ListCommand("/e ", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.00 was spent on Cream Pie(Food) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString2);
    }

    /**
     * Tests list command with exact date, month, year on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithExactDate_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String firstInputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(firstInputString, user).executeCommand();
        String listInString = new ListCommand("/e 30/03/2022", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);

        String secondInputString = "/e /pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(secondInputString, user).executeCommand();

        String inputString3 = "/e /pm cash /c Food /d Cream Pie /a 69 /t 01/04/2022";

        new AddCommand(inputString3, user).executeCommand();
        listInString = new ListCommand("/e 30/03/2022", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.00 was spent on Cream Pie(Food) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);
    }

    /**
     * Tests list command with exact month, year on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithExactMonth_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        String listInString = new ListCommand("/e 03/2022", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);

        String secondInputString = "/e /pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(secondInputString, user).executeCommand();

        String thirdInputString = "/e /pm cash /c Food /d Cream Pie /a 69 /t 01/04/2022";

        new AddCommand(thirdInputString, user).executeCommand();
        listInString = new ListCommand("/e 03/2022", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.00 was spent on Cream Pie(Food) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);
    }

    /**
     * Tests list command with exact year on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithExactYear_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        String listInString = new ListCommand("/e 2022", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);

        String secondInputString = "/e /pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(secondInputString, user).executeCommand();

        String thirdInputString = "/e /pm cash /c Food /d Cream Pie /a 69 /t 01/04/2021";

        new AddCommand(thirdInputString, user).executeCommand();
        listInString = new ListCommand("/e 2022", user).expenditureListToString();
        assertEquals("-----------------------------------------------" + System.lineSeparator()
                + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.00 was spent on Cream Pie(Food) using Cash [30/03/2022]\n"
                + "-----------------------------------------------" + System.lineSeparator(), listInString);
    }

    /**
     * Tests list command for expenditure on an empty list. Should expect an exception thrown.
     */
    @Test
    void listCommand_emptyList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

    /**
     * Tests list command for date, month and year on an empty list. Should expect an exception thrown.
     */
    @Test
    void listCommand_wrongDateFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e 39/14/2022", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

    /**
     * Tests list command for month and year on an empty list. Should expect an exception thrown.
     */
    @Test
    void listCommand_wrongMonthFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e 4/2022", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

    /**
     * Tests list command for year on an empty list. Should expect an exception thrown.
     */
    @Test
    void listCommand_wrongYearFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e /2022/", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

    /**
     * Test if program is able to exit. Should return false.
     */
    @Test
    void listCommand_isExit_expectFalse() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        assertEquals(false, new ListCommand("/e", user).isExit());

    }

    /**
     * Asserts if user is able to list command for credit card flag input.
     */
    @Test
    void listCommand_normalCreditCardInputs_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String firstInputString = "/cc /n DBS /cb 1.5 /cl 1000";
        new AddCommand(firstInputString, user).executeCommand();
        String secondInputString = "/cc /n POSB /cb 1.5 /cl 20";
        new AddCommand(secondInputString, user).executeCommand();
        setUp();
        new ListCommand("/cc", user).executeCommand();
        tearDown();
        String expectedOutput = "-----------------------------------------------"
            + System.lineSeparator()
            + "1. Name: DBS [Cashback: 1.50%] [Cashback gained: $0.00] [Card limit: $1000.00] "
            + "[Balance left: $1000.00]\n"
            + "2. Name: POSB [Cashback: 1.50%] [Cashback gained: $0.00] [Card limit: $20.00] [Balance left: $20.00]\n"
            + "-----------------------------------------------";
        assertEquals(expectedOutput.trim(), capturedOut.toString().trim());
    }

    /**
     * Tests list command for credit card flag input with empty list. Should expect an exception thrown.
     */
    @Test
    void listCommand_emptyListCreditCardInput_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        assertThrows(MindMyMoneyException.class,
            () -> new ListCommand("/c", user).printCreditCardList());

    }

    /**
     * Tests list command for income flag input with empty list. Should expect an exception thrown.
     */
    @Test
    void listCommand_emptyListIncomeInput_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        assertThrows(MindMyMoneyException.class,
            () -> new ListCommand("/i", user).printIncomeList());

    }

    /**
     * Asserts if user is able to list command for income flag input. Should expect a print message.
     */
    @Test
    void listCommand_normalIncomeInput_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String firstInputString = "/i /a 3000 /c Salary";
        new AddCommand(firstInputString, user).executeCommand();
        String secondInputString = "/i /a 300 /c Salary";
        new AddCommand(secondInputString, user).executeCommand();
        setUp();
        new ListCommand("/i", user).executeCommand();
        tearDown();
        String expectedOutput = "-----------------------------------------------"
            + System.lineSeparator()
            + "1. Amount: $3000\n"
            + "   Category: Salary\n"
            + "2. Amount: $300\n"
            + "   Category: Salary\n"
            + "-----------------------------------------------"
            + System.lineSeparator();
        assertEquals(expectedOutput.trim(), capturedOut.toString().trim());
    }

    /**
     * Tests user is able to list the expenditure list. Should print out expenditure list.
     */
    @Test
    void listCommand_normalExpenditureInput_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String firstInputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(firstInputString, user).executeCommand();
        String secondInputString = "/e /pm cash /c Food /d Cream Pie /a 69 /t 30/03/2021";
        new AddCommand(secondInputString, user).executeCommand();
        setUp();
        new ListCommand("/e", user).executeCommand();
        tearDown();
        String expectedOutput = "-----------------------------------------------"
            + System.lineSeparator()
            + "1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
            + "2. $69.00 was spent on Cream Pie(Food) using Cash [30/03/2021]\n"
            + "-----------------------------------------------"
            + System.lineSeparator();
        assertEquals(expectedOutput.trim(), capturedOut.toString().trim());
    }

    public void tearDown() {
        System.setOut(stdout);
    }
}
