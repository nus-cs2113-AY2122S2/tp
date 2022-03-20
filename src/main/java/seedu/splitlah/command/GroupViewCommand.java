package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;

public class GroupViewCommand extends Command {

    public static final String COMMAND_TEXT = "group /view";

    public static final String COMMAND_FORMAT = "Syntax: group /view /gid [GROUP_ID]";

    public static final String[] COMMAND_DELIMITERS = {
            Parser.GROUP_ID_DELIMITER
    };

    private int groupId;

    private static final String GROUP_ID_HEADER = "Group Id #";
    private static final String SEPARATOR = " | ";

    /**
     * Initializes a SessionDeleteCommand.
     *
     * @param groupId An integer that represents the group unique identifier.
     */
    public GroupViewCommand(int groupId) {
        this.groupId = groupId;
    }

    public static Command prepare(String commandArgs) {
        try {
            int groupId = Parser.parseGroupId(commandArgs);
            return new GroupViewCommand(groupId);
        } catch (InvalidFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT);
        }
    }

    @Override
    public void run(Manager manager) {
        try {
            Group group = manager.getProfile().getGroup(groupId);
            String stringToBePrinted = GROUP_ID_HEADER + groupId + SEPARATOR + group.toString();
            manager.getUi().printlnMessageWithDivider(stringToBePrinted);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
        }
    }
}
