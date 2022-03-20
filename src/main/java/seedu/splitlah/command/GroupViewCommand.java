package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

public class GroupViewCommand extends Command {

    public static final String COMMAND_TEXT = "group /view";

    public static final String COMMAND_FORMAT = "Syntax: group /view /gid [GROUP_ID]";

    public static final String[] COMMAND_DELIMITERS = {
            Parser.GROUP_ID_DELIMITER
    };

    private int groupId;

    @Override
    public void run(Manager manager) {
        
    }
}
