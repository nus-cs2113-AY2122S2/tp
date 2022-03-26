package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.ActivityDeleteCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.ParserUtils;

public class ActivityDeleteCommandParser implements CommandParser<ActivityDeleteCommand> {

    public static final String COMMAND_TEXT = "activity /delete";

    public static final String COMMAND_FORMAT = "Syntax: activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]";
    
    public static final String[] COMMAND_DELIMITERS = {
            ParserUtils.SESSION_ID_DELIMITER,
            ParserUtils.ACTIVITY_ID_DELIMITER
    };

    @Override
    public ActivityDeleteCommand getCommand(String commandArgs) throws InvalidFormatException {
        return null;
    }
}
