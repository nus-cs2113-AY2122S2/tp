package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.command.HelpCommand;

/**
 * Represents the input parser and deals with making sense of user commands.
 */
public class Parser {
    public static final int SPLIT_LIMIT = 2;
    public static final int COMMAND_INDEX = 0;

    protected String inputCommand;

    public Parser(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * Separates the user input into the command and description for easy reference.
     *
     * @return String array of user input.
     */
    public String[] parseInput() {
        String[] inputAsArray = inputCommand.split(" ", SPLIT_LIMIT);
        return inputAsArray;
    }

    /**
     * Returns a Command object with respect to their input. The command object can then be executed to perform
     * the said command.
     *
     * @return Command object with respect to user's input.
     */
    public Command parseCommand() {
        String[] parsedInput = parseInput();

        switch (parsedInput[COMMAND_INDEX]) {
        case "help":
            return new HelpCommand(true);
        case "bye":
            System.out.println("Goodbye!");
            System.exit(0);
        default:
            return new HelpCommand(false);
        }
    }
}
