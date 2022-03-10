package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.command.HelpCommand;
import seedu.mindmymoney.command.UpdateCommand;
import seedu.mindmymoney.command.AddCommand;
import seedu.mindmymoney.command.ListCommand;
import seedu.mindmymoney.command.DeleteCommand;
import seedu.mindmymoney.constants.Indexes;
import seedu.mindmymoney.helper.Functions;

/**
 * Represents the input parser and deals with making sense of user commands.
 */
public class Parser {
    public static final int SPLIT_LIMIT = 2;

    protected String inputCommand;

    public Parser(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * Returns a Command object with respect to their input. The command object can then be executed to perform
     * the said command.
     *
     * @return Command object with respect to user's input.
     */
    public Command parseCommand() {
        try {
            String[] parsedInput = Functions.parseInput(inputCommand);
            switch (parsedInput[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING].toLowerCase()) {
            case "help":
                return new HelpCommand(true);
            case "bye":
                System.out.println("Goodbye!");
                System.exit(0);
                return new HelpCommand(false); //solving fall through issue, need return something leh
            case "add":
                return new AddCommand(parsedInput[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
            case "update":
                return new UpdateCommand(parsedInput[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(inputCommand);
            default:
                return new HelpCommand(false);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing input after command!");
        }
        return new HelpCommand(false);
    }
}
