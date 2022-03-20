package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

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
     * Initializes a GroupViewCommand.
     *
     * @param groupId An integer that represents the group unique identifier.
     */
    public GroupViewCommand(int groupId) {
        assert groupId > 0 : Message.ASSERT_GROUPVIEW_GROUP_ID_NOT_INITIALIZED;
        this.groupId = groupId;
    }

    /**
     * Prepares user arguments for the creation of a GroupViewCommand object.
     *
     * @param commandArgs A String object that represents the user's arguments.
     * @return A GroupViewCommand object if group unique identifier was found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        try {
            int groupId = Parser.parseGroupId(commandArgs);
            assert groupId > 0 : Message.ASSERT_GROUPVIEW_GROUP_ID_NOT_INITIALIZED;
            return new GroupViewCommand(groupId);
        } catch (InvalidFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT);
        }
    }

    /**
     * Runs the command with the group unique identifier as provided by the user input and
     * prints the details of the group.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        try {
            Group group = manager.getProfile().getGroup(groupId);
            assert group.getGroupId() == groupId : Message.ASSERT_GROUPVIEW_INCORRECT_GROUP;
            String stringToBePrinted = GROUP_ID_HEADER + groupId + SEPARATOR + group;
            manager.getUi().printlnMessageWithDivider(stringToBePrinted);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
        }
    }
}
