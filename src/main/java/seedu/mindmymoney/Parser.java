package seedu.mindmymoney;

import seedu.mindmymoney.command.AddCommand;
import seedu.mindmymoney.command.CalculateInputCommand;
import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.command.HelpCommand;
import seedu.mindmymoney.command.ByeCommand;
import seedu.mindmymoney.command.UpdateCommand;
import seedu.mindmymoney.command.DeleteCommand;
import seedu.mindmymoney.command.ListCommand;
import seedu.mindmymoney.helper.GeneralFunctions;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Flags.EMPTY_PARAMETER;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;

/**
 * Represents the input parser and deals with making sense of user commands.
 */
public class Parser {

    /**
     * Returns a Command object with respect to their input. The command object can then be executed to perform
     * the said command.
     *
     * @param input The command to be parsed.
     * @param user  The user object, which contains income, expenditure and credit card list.
     * @return Command object with respect to user's input.
     */
    public static Command parseCommand(String input, User user) {
        try {
            String[] parsedInput = GeneralFunctions.parseInput(input);
            assert parsedInput[INDEX_OF_FIRST_ITEM] != null : "First element in parsedInput is null";

            switch (parsedInput[INDEX_OF_FIRST_ITEM].toLowerCase()) {
            case "help":
                if (hasAdditionalParameters(parsedInput)) {
                    return new HelpCommand(true, parsedInput[INDEX_OF_SECOND_ITEM]);
                }
                return new HelpCommand(true, EMPTY_PARAMETER);
            case "bye":
                return new ByeCommand();
            case "add":
                return new AddCommand(parsedInput[INDEX_OF_SECOND_ITEM], user);
            case "update":
                return new UpdateCommand(parsedInput[INDEX_OF_SECOND_ITEM], user);
            case "list":
                return new ListCommand(parsedInput[INDEX_OF_SECOND_ITEM], user);
            case "delete":
                return new DeleteCommand(input, user);
            case "calculate":
                return new CalculateInputCommand(parsedInput[INDEX_OF_SECOND_ITEM], user);
            default:
                return new HelpCommand(false, FLAG_OF_EXPENSES);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("");
        }
        return new HelpCommand(false, FLAG_OF_EXPENSES);
    }

    private static boolean hasAdditionalParameters(String[] input) {
        return input.length > 1;
    }
}
