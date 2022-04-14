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


class CalculateInputCommandTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;


    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    /**
     * Asserts if user is able to calculate expenditure by date.
     */
    @Test
    void calculateInputCommand_calculateByDate_expectCorrectOutput() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        inputString = "/e /pm cash /c Food /d Coke /a 20 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        inputString = "/e /pm cash /c Entertainment /d Movie /a 10 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        inputString = "/e /pm cash /c Personal /d Nike Shoes /a 200 /t 30/03/2021";
        new AddCommand(inputString, user).executeCommand();

        setUp();
        new CalculateInputCommand("/epm 30/03/2022", user).executeCommand();
        tearDown();
        String expectedOutput = "Total expenditure in 30/03/2022 is $330.00." + System.lineSeparator()
            + System.lineSeparator() + "BREAKDOWN OF EXPENSES:" + System.lineSeparator()
            + "-----------------------------------------------" + System.lineSeparator()
            + "FOOD:          $$$$ [6.06%]" + System.lineSeparator()
            + "TRANSPORT:      [0.0%]" + System.lineSeparator()
            + "UTILITIES:      [0.0%]" + System.lineSeparator()
            + "PERSONAL:      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ [90.91%]" + System.lineSeparator()
            + "ENTERTAINMENT: $$ [3.03%]" + System.lineSeparator()
            + "OTHERS:         [0.0%]" + System.lineSeparator()
            + "-----------------------------------------------";
        assertEquals(expectedOutput, capturedOut.toString().trim());
    }

    /**
     * Asserts if user is able to calculate expenditure by month.
     */
    @Test
    void calculateInputCommand_calculateByMonth_expectCorrectOutput() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        inputString = "/e /pm cash /c Food /d Coke /a 20 /t 01/04/2022";

        new AddCommand(inputString, user).executeCommand();

        setUp();
        new CalculateInputCommand("/epm 03/2022", user).executeCommand();
        tearDown();
        String expectedOutput = "Total expenditure in 03/2022 is $300.00." + System.lineSeparator()
            + System.lineSeparator() + "BREAKDOWN OF EXPENSES:" + System.lineSeparator()
            + "-----------------------------------------------" + System.lineSeparator()
            + "FOOD:           [0.0%]" + System.lineSeparator()
            + "TRANSPORT:      [0.0%]" + System.lineSeparator()
            + "UTILITIES:      [0.0%]" + System.lineSeparator()
            + "PERSONAL:      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ [100.0%]" + System.lineSeparator()
            + "ENTERTAINMENT:  [0.0%]" + System.lineSeparator()
            + "OTHERS:         [0.0%]" + System.lineSeparator()
            + "-----------------------------------------------";
        assertEquals(expectedOutput, capturedOut.toString().trim());
    }

    /**
     * Asserts if user is able to calculate expenditure by Year.
     */
    @Test
    void calculateInputCommand_calculateByYear_expectCorrectOutput() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        inputString = "/e /pm cash /c Food /d Coke /a 20 /t 30/04/2021";

        new AddCommand(inputString, user).executeCommand();

        setUp();
        new CalculateInputCommand("/epm 2022", user).executeCommand();
        tearDown();
        String expectedOutput = "Total expenditure in 2022 is $300.00." + System.lineSeparator()
            + System.lineSeparator() + "BREAKDOWN OF EXPENSES:" + System.lineSeparator()
            + "-----------------------------------------------" + System.lineSeparator()
            + "FOOD:           [0.0%]" + System.lineSeparator()
            + "TRANSPORT:      [0.0%]" + System.lineSeparator()
            + "UTILITIES:      [0.0%]" + System.lineSeparator()
            + "PERSONAL:      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ [100.0%]" + System.lineSeparator()
            + "ENTERTAINMENT:  [0.0%]" + System.lineSeparator()
            + "OTHERS:         [0.0%]" + System.lineSeparator()
            + "-----------------------------------------------";
        assertEquals(expectedOutput, capturedOut.toString().trim());
    }

    /**
     * Asserts if user is able to use an incorrect flag.
     */
    @Test
    void calculateInputCommand_wrongFlag_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        inputString = "/e /pm cash /c Personal /d Nike Shoes /a 3000 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("/a 30/03/2022", user).executeCommand());
    }

    /**
     * Asserts if user is able to input an empty flag.
     */
    @Test
    void calculateInputCommand_emptyFlag_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        inputString = "/e /pm cash /c Personal /d Nike Shoes /a 3000 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("30/03/2022", user).executeCommand());
    }

    /**
     * Asserts if user is able to input an improper input.
     */
    @Test
    void calculateInputCommand_wrongInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        inputString = "/e /pm cash /c Personal /d Nike Shoes /a 3000 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("30/03/2022", user).executeCommand());
    }

    /**
     * Asserts if user is able to input an improper date input.
     */
    @Test
    void calculateInputCommand_wrongDateInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        inputString = "/e /pm cash /c Personal /d Nike Shoes /a 3000 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();

        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("/epm 34/03/2022", user).executeCommand());
        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("/epm 30/2022", user).executeCommand());
        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("/epm /2022", user).executeCommand());
    }

    /**
     * Asserts if user is able to calculate from an empty list.
     */
    @Test
    void calculateInputCommand_emptyList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("30/03/2022", user).executeCommand());
    }

    /**
     * Asserts if user is able to add in a flag without date.
     */
    @Test
    void calculateInputCommand_noDate_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("/epm", user).executeCommand());
    }

    /**
     * Asserts if command is able to exit.
     */
    @Test
    void calculateInputCommand_isExit_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/epm 29/02/2020";

        assertEquals(false, new CalculateInputCommand(inputString, user).isExit());

    }

    public void tearDown() {
        System.setOut(stdout);
    }
}
