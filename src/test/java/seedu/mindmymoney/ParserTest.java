package seedu.mindmymoney;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.command.DeleteCommand;
import seedu.mindmymoney.command.ListCommand;
import seedu.mindmymoney.command.AddCommand;
import seedu.mindmymoney.command.UpdateCommand;
import seedu.mindmymoney.command.HelpCommand;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

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

        testInput = "list /expenses";
        assertTrue(testParser.parseCommand(testInput, user) instanceof ListCommand);

        testInput = "delete " + TEST_INDEX;
        assertTrue(testParser.parseCommand(testInput, user) instanceof DeleteCommand);
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
}
