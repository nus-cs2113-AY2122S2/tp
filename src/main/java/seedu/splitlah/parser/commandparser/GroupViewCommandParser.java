package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.GroupViewCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.ParserUtils;

public class GroupViewCommandParser implements CommandParser<GroupViewCommand> {

    public static final String COMMAND_TEXT = "group /view";

    public static final String COMMAND_FORMAT = "Syntax: group /view /gid [GROUP_ID]";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.GROUP_ID_DELIMITER
    };
    
    @Override
    public GroupViewCommand getCommand(String commandArgs) throws InvalidFormatException {
        return null;
    }
}
