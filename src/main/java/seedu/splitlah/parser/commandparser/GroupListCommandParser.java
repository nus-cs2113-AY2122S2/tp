package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.GroupListCommand;
import seedu.splitlah.exceptions.InvalidFormatException;

/**
 * Represents a command parser that is able to parse user arguments into a GroupListCommand object.
 *
 * @author Ivan
 */
public class GroupListCommandParser implements CommandParser<GroupListCommand> {

    public static final String COMMAND_TEXT = "group /list";

    public static final String COMMAND_FORMAT = "Syntax: group /list";

    /**
     * Returns a GroupListCommand object after parsing the input arguments from the user.
     *
     * @param commandArgs A String object representing arguments provided by the user.
     * @return A GroupListCommand object when method is called.
     */
    @Override
    public GroupListCommand getCommand(String commandArgs) throws InvalidFormatException {
        return new GroupListCommand();
    }
}
