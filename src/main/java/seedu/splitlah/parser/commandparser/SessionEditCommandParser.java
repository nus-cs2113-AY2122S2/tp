package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.SessionEditCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.ParserUtils;

/**
 * Represents a command parser that is able to parse user arguments into a SessionEditCommand object.
 */
public class SessionEditCommandParser implements CommandParser<SessionEditCommand> {

    public static final String COMMAND_TEXT = "session /edit";

    public static final String COMMAND_FORMAT =
            "Syntax: session /edit /sid [SESSION_ID] /n [SESSION_NAME] /d [SESSION_DATE] /pl [NAME1 NAME2...] "
                    + "[</gid [GROUP_ID]>]";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.SESSION_ID_DELIMITER,
        ParserUtils.NAME_DELIMITER,
        ParserUtils.DATE_DELIMITER,
        ParserUtils.PERSON_LIST_DELIMITER,
        ParserUtils.GROUP_ID_DELIMITER
    };

    /**
     * Returns a SessionEditCommand object after parsing the input arguments from the user.
     *
     * @param commandArgs A String object representing arguments provided by the user.
     * @return A SessionEditCommand object if all necessary arguments required for the SessionEditCommand object
     *         to function are found in the input arguments.
     * @throws InvalidFormatException If at least one of the necessary arguments cannot be found in the input arguments.
     */
    @Override
    public SessionEditCommand getCommand(String commandArgs) throws InvalidFormatException {
        return null;
    }
}
