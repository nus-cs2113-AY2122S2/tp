package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;

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
     * Asserts if user is able to calculate expenditure per month.
     */
    @Test
    void calculateInputCommand_oneInput_expectCorrectOutput() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "/e cash /c Personal /d Nike Shoes /a 300 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        inputString = "/e cash /c Personal /d Nike Shoes /a 3000 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        inputString = "/e cash /c Personal /d Nike Shoes /a 1000 /t 2022-04";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        inputString = "/e cash /c Personal /d Nike Shoes /a 200 /t 2021-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        setUp();
        new CalculateInputCommand("/epm Mar 2022", expenditureTestList).executeCommand();
        tearDown();
        String expectedOutput = "Total expenditure in the month of Mar 2022 is 3300.0";
        assertEquals(expectedOutput, capturedOut.toString().trim());
    }

    /**
     * Asserts if user is able to input a case-insensitive Month.
     */
    @Test
    void calculateInputCommand_caseInsensitiveMonth_expectCorrectOutput() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "/e cash /c Personal /d Nike Shoes /a 0.5 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        inputString = "/e cash /c Personal /d Nike Shoes /a 0.10 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        setUp();
        new CalculateInputCommand("/epm mAr 2022", expenditureTestList).executeCommand();
        tearDown();
        String expectedOutput = "Total expenditure in the month of Mar 2022 is 0.6";
        assertEquals(expectedOutput, capturedOut.toString().trim());
    }

    /**
     * Asserts if user is able to use an incorrect flag.
     */
    @Test
    void calculateInputCommand_wrongFlag_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "/e cash /c Personal /d Nike Shoes /a 300 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        inputString = "/e cash /c Personal /d Nike Shoes /a 3000 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("/a Mar 2022", expenditureTestList).executeCommand());
    }

    /**
     * Asserts if user is able to input an empty flag.
     */
    @Test
    void calculateInputCommand_emptyFlag_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "/e cash /c Personal /d Nike Shoes /a 300 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        inputString = "/e cash /c Personal /d Nike Shoes /a 3000 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("Mar 2022", expenditureTestList).executeCommand());
    }

    /**
     * Asserts if user is able to input an improper input.
     */
    @Test
    void calculateInputCommand_wrongInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        String inputString = "/e cash /c Personal /d Nike Shoes /a 300 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        inputString = "/e cash /c Personal /d Nike Shoes /a 3000 /t 2022-03";
        new AddCommand(inputString, expenditureTestList, creditCardTestList).executeCommand();
        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("asdf", expenditureTestList).executeCommand());
    }

    /**
     * Asserts if user is able to calculate from an empty list.
     */
    @Test
    void calculateInputCommand_emptyList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        assertThrows(MindMyMoneyException.class,
            () -> new CalculateInputCommand("Mar 2022", expenditureTestList).executeCommand());
    }

    public void tearDown() {
        System.setOut(stdout);
    }

}
