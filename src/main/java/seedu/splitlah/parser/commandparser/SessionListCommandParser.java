package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.SessionListCommand;
import seedu.splitlah.exceptions.InvalidFormatException;

/**
 * Represents a command parser that is able to parse user arguments into a SessionListCommand object.
 */
public class SessionListCommandParser implements CommandParser<SessionListCommand> {

    public static final String COMMAND_TEXT = "session /list";

    public static final String COMMAND_FORMAT = "Syntax: session /list";

    /**
     * Returns a SessionListCommand object after parsing the input arguments from the user.
     *
     * @param commandArgs A String object representing arguments provided by the user.
     * @return A SessionListCommand object when method is called.
     */
    @Override
    public SessionListCommand getCommand(String commandArgs) throws InvalidFormatException {
        return new SessionListCommand();
    }
}
