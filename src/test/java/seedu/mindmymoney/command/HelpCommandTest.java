package seedu.mindmymoney.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Represents the tests for Help command.
 */
public class HelpCommandTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    /**
     * Asserts that the expenditure help page will be printed when the user requests for it.
     */
    @Test
    void helpCommand_fromUser_expectExpenditureHelpPage() throws MindMyMoneyException {
        String helpPage = "---------------------------------------Expenditure Help Page------------------------"
            + "---------------\n"
            + "1. Listing all Expenditures: list /e {DATE}\n"
            + "2. Adding an Expenditure entry: add /e /pm [PAYMENT_METHOD] /c [CATEGORY] "
            + "/d [DESCRIPTION] /a [AMOUNT] /t [DATE]\n"
            + "3. Calculating the total expenditure in a month: calculate /epm [DATE]\n"
            + "4. Updating an Expenditure: update /e [NEW_INDEX] /pm [NEW_PAYMENT_METHOD] /c [NEW_CATEGORY] "
            + "/d [NEW_DESCRIPTION] /a [NEW_AMOUNT] /t [NEW_DATE]\n"
            + "5. Removing an Expenditure entry: delete /e [INDEX]\n"
            + "6. Exiting the program: bye\n"
            + "----------------------------------------------------------------------------------------------"
            + "-----\n";

        new HelpCommand(true, "/e").executeCommand();
        assertEquals(helpPage.trim(), capturedOut.toString().trim());
    }

    /**
     * Asserts that the error message will be printed if an invalid command is received.
     */
    @Test
    void helpCommand_notFromUser_expectErrorMessage() throws MindMyMoneyException {
        String errorMessage = "Invalid command! \n"
            + "Type \"help /e\" to view the list of supported expenditure commands\n"
            + "Type \"help /cc\" to view the list of supported Credit Card commands\n"
            + "Type \"help /i\" to view the list of supported income commands\n";

        new HelpCommand(false, "/e").executeCommand();
        assertEquals(errorMessage.trim(), capturedOut.toString().trim());
    }

    /**
     * Asserts that the income help page will be printed when the user requests for it.
     */
    @Test
    void helpCommand_incomeFlag_expectIncomeHelpPage() throws MindMyMoneyException {
        String helpPage = "--------------------------------Income Help Page------------------------------"
            + "---------\n"
            + "1. Listing all Incomes: list /i\n"
            + "2. Adding an Income entry: add /i /a [AMOUNT] /c [CATEGORY]\n"
            + "3. Updating an Income entry: update /i [INDEX] /a [NEW_AMOUNT] /c [NEW_CATEGORY]\n"
            + "4. Removing an Income entry: delete /i [INDEX]\n"
            + "---------------------------------------------------------------------------------------\n";

        new HelpCommand(true, "/i").executeCommand();
        assertEquals(helpPage.trim(), capturedOut.toString().trim());
    }

    /**
     * Asserts that the credi card help page will be printed when the user requests for it.
     */
    @Test
    void helpCommand_creditCardFlag_expectCreditHelpPage() throws MindMyMoneyException {
        String helpPage = "---------------------------------------Credit Card Help Page--------------------------"
            + "-------------\n"
            + "1. Listing all Credit Cards: list /cc\n"
            + "2. Adding a Credit Card: add /cc /n [CREDIT_CARD_NAME] /cb [CASHBACK] /cl [CREDIT_LIMIT]\n"
            + "3. Updating a Credit Card: update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] "
            + "/cl [NEW_CREDIT_LIMIT]\n"
            + "4. Removing a credit card: delete /cc [INDEX]\n"
            + "5. Exiting the program: bye\n"
            + "-----------------------------------------------------------------------------------------------"
            + "----\n";

        new HelpCommand(true, "/cc").executeCommand();
        assertEquals(helpPage.trim(), capturedOut.toString().trim());
    }

    /**
     * Asserts that the error message will be printed when incorrect flag is provided.
     */
    @Test
    void helpCommand_invalidFlag_expectException() throws MindMyMoneyException {
        assertThrows(MindMyMoneyException.class,
            () -> new HelpCommand(true, "/abc").executeCommand());
    }

    /**
     * Test if program is able to exit.
     */
    @Test
    void helpCommand_isExit_expectFalse() {
        assertEquals(false, new HelpCommand(true, "").isExit());

    }

    /**
     * Asserts that the all help pages will be printed when the user requests for it.
     */
    @Test
    void helpCommand_fromUser_expectAllHelpPages() throws MindMyMoneyException {
        String helpPage = "---------------------------------------Expenditure Help Page------------------------"
            + "---------------\n"
            + "1. Listing all Expenditures: list /e {DATE}\n"
            + "2. Adding an Expenditure entry: add /e /pm [PAYMENT_METHOD] /c [CATEGORY] /d [DESCRIPTION] "
            + "/a [AMOUNT] /t [DATE]\n"
            + "3. Calculating the total expenditure in a month: calculate /epm [DATE]\n"
            + "4. Updating an Expenditure: update /e [NEW_INDEX] /pm [NEW_PAYMENT_METHOD] /c [NEW_CATEGORY] "
            + "/d [NEW_DESCRIPTION] /a [NEW_AMOUNT] /t [NEW_DATE]\n"
            + "5. Removing an Expenditure entry: delete /e [INDEX]\n"
            + "6. Exiting the program: bye\n"
            + "----------------------------------------------------------------------------------------------"
            + "-----\n"
            + System.lineSeparator()
            + "---------------------------------------Credit Card Help Page--------------------------"
            + "-------------\n"
            + "1. Listing all Credit Cards: list /cc\n"
            + "2. Adding a Credit Card: add /cc /n [CREDIT_CARD_NAME] /cb [CASHBACK] /cl [CREDIT_LIMIT]\n"
            + "3. Updating a Credit Card: update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] "
            + "/cl [NEW_CREDIT_LIMIT]\n"
            + "4. Removing a credit card: delete /cc [INDEX]\n"
            + "5. Exiting the program: bye\n"
            + "-----------------------------------------------------------------------------------------------"
            + "----\n"
            + System.lineSeparator()
            + "--------------------------------Income Help Page------------------------------"
            + "---------\n"
            + "1. Listing all Incomes: list /i\n"
            + "2. Adding an Income entry: add /i /a [AMOUNT] /c [CATEGORY]\n"
            + "3. Updating an Income entry: update /i [INDEX] /a [NEW_AMOUNT] /c [NEW_CATEGORY]\n"
            + "4. Removing an Income entry: delete /i [INDEX]\n"
            + "---------------------------------------------------------------------------------------\n"
            + System.lineSeparator();

        new HelpCommand(true, "").executeCommand();
        assertEquals(helpPage.trim(), capturedOut.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdout);
    }
}
