package seedu.mindmymoney;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.command.ByeCommand;
import seedu.mindmymoney.command.CalculateInputCommand;
import seedu.mindmymoney.command.DeleteCommand;
import seedu.mindmymoney.command.ListCommand;
import seedu.mindmymoney.command.AddCommand;
import seedu.mindmymoney.command.UpdateCommand;
import seedu.mindmymoney.command.HelpCommand;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    public static final int TEST_PRICE = 1; //arbitrary number for testing
    public static final int TEST_INDEX = 0; //arbitrary number for testing

    /**
     * Checks Parser.parseCommand() that it returns a Command object for each test input.
     */
    @Test
    void parseCommand_normalInput_expectCorrectCommandObject() {
        String testInput = "help";
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeTestList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeTestList);
        Parser testParser = new Parser();

        assertTrue(testParser.parseCommand(testInput, user) instanceof HelpCommand);
        assert testParser.parseCommand(testInput, user)
            instanceof HelpCommand : "testParser should return an " + "instance of HelpCommand";

        testInput = "add description " + TEST_PRICE;
        assertTrue(testParser.parseCommand(testInput, user) instanceof AddCommand);

        testInput = "update " + TEST_INDEX + " description " + TEST_PRICE;
        assertTrue(testParser.parseCommand(testInput, user) instanceof UpdateCommand);

        testInput = "list /e";
        assertTrue(testParser.parseCommand(testInput, user) instanceof ListCommand);

        testInput = "delete " + TEST_INDEX;
        assertTrue(testParser.parseCommand(testInput, user) instanceof DeleteCommand);

        testInput = "bye";
        assertTrue(testParser.parseCommand(testInput, user) instanceof ByeCommand);

        testInput = "calculate /epm 01/02/2022";
        assertTrue(testParser.parseCommand(testInput, user) instanceof CalculateInputCommand);
    }

    /**
     * Invalid input by user should return a HelpCommand object
     * and print an invalid command message.
     */
    @Test
    void parseCommand_invalidInput_expectHelpCommand() {
        Parser testParser = new Parser();
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeTestList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeTestList);

        assertTrue(testParser.parseCommand("", user) instanceof HelpCommand);
    }

    /**
     * "Help /cc" by user should return the credit card help page
     * and print an invalid command message.
     */
    @Test
    void parseCommand_helpWithParameters_expectHelpCommand() throws MindMyMoneyException {
        Parser testParser = new Parser();
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeTestList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeTestList);
        setUp();
        String expectedOutput =
            "---------------------------------------Credit Card Help Page--------------------------"
                + "-------------\n"
                + "1. Listing all Credit Cards: list /cc\n"
                + "2. Adding a Credit Card: add /cc /n [CREDIT_CARD_NAME] /cb [CASHBACK] /cl [CREDIT_LIMIT]\n"
                + "3. Updating a Credit Card: update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] "
                + "/cl [NEW_CREDIT_LIMIT]\n"
                + "4. Removing a credit card: delete /cc [INDEX]\n"
                + "5. Exiting the program: bye\n"
                + "-----------------------------------------------------------------------------------------------"
                + "----";
        (testParser.parseCommand("help /cc", user)).executeCommand();
        tearDown();
        assertEquals(expectedOutput, capturedOut.toString().trim());
    }

    /**
     * Add input without parameters should return a HelpCommand object
     * and print an invalid command message.
     */
    @Test
    void parseCommand_addWithoutParameters_expectException() throws MindMyMoneyException {
        Parser testParser = new Parser();
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeTestList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeTestList);
        setUp();
        String expectedOutput = "Invalid command! \n"
            + "Type \"help /e\" to view the list of supported expenditure commands\n"
            + "Type \"help /cc\" to view the list of supported Credit Card commands\n"
            + "Type \"help /i\" to view the list of supported income commands";
        (testParser.parseCommand("add", user)).executeCommand();
        tearDown();
        assertEquals(expectedOutput, capturedOut.toString().trim());
    }

    public void tearDown() {
        System.setOut(stdout);
    }
}
