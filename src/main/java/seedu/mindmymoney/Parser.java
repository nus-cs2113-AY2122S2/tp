package seedu.mindmymoney;

import seedu.mindmymoney.command.*;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.helper.GeneralFunctions;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM_IN_STRING;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM_IN_STRING;

/**
 * Represents the input parser and deals with making sense of user commands.
 */
public class Parser {

    /**
     * Returns a Command object with respect to their input. The command object can then be executed to perform
     * the said command.
     *
     * @param input    The command to be parsed.
     * @param itemList The list of expenditures.
     * @return Command object with respect to user's input.
     */
    public static Command parseCommand(String input, ExpenditureList itemList) {
        try {
            String[] parsedInput = GeneralFunctions.parseInput(input);
            assert parsedInput[INDEX_OF_FIRST_ITEM_IN_STRING] != null : "First element in parsedInput is null";

            switch (parsedInput[INDEX_OF_FIRST_ITEM_IN_STRING].toLowerCase()) {
            case "help":
                return new HelpCommand(true);
            case "bye":
                return new ByeCommand();
            case "add":
                return new AddCommand(parsedInput[INDEX_OF_SECOND_ITEM_IN_STRING], itemList);
            case "update":
                return new UpdateCommand(parsedInput[INDEX_OF_SECOND_ITEM_IN_STRING], itemList);
            case "list":
                return new ListCommand(itemList);
            case "delete":
                return new DeleteCommand(input, itemList);
            case "calculate":
                return new CalculateCommand(parsedInput[INDEX_OF_SECOND_ITEM_IN_STRING], itemList);
            default:
                return new HelpCommand(false);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing input after command!");
        }
        return new HelpCommand(false);
    }
}
