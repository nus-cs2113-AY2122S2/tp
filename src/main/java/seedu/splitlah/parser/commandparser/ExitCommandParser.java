package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.ExitCommand;
import seedu.splitlah.exceptions.InvalidFormatException;

/**
 * Represents a command parser that is able to parse user arguments into a ExitCommand object.
 *
 * @author Roy
 */
public class ExitCommandParser implements CommandParser<ExitCommand> {

    public static final String COMMAND_TEXT = "exit";

    public static final String COMMAND_FORMAT = "Syntax: exit";

    /**
     * Returns an ExitCommand object after parsing the input arguments from the user.
     *
     * @param commandArgs A String object representing arguments provided by the user.
     * @return An ExitCommand object when method is called.
     */
    @Override
    public ExitCommand getCommand(String commandArgs) throws InvalidFormatException {
        return new ExitCommand();
    }
}
