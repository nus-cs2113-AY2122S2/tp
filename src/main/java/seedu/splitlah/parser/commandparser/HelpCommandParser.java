package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.HelpCommand;
import seedu.splitlah.exceptions.InvalidFormatException;

/**
 * Represents a command parser that is able to parse user arguments into a HelpCommand object.
 */
public class HelpCommandParser implements CommandParser<HelpCommand> {

    public static final String COMMAND_TEXT = "help";

    public static final String COMMAND_FORMAT = "Syntax: help";

    /**
     * Returns a HelpCommand object after parsing the input arguments from the user.
     *
     * @param commandArgs A String object representing arguments provided by the user.
     * @return A HelpCommand object when method is called.
     */
    @Override
    public HelpCommand getCommand(String commandArgs) throws InvalidFormatException {
        return new HelpCommand();
    }
}
