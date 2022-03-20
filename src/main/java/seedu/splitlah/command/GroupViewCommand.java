package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import java.util.logging.Level;

public class GroupViewCommand extends Command {

    public static final String COMMAND_TEXT = "group /view";

    public static final String COMMAND_FORMAT = "Syntax: group /view /gid [GROUP_ID]";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.GROUP_ID_DELIMITER
    };

    private int groupId;

    /**
     * Initializes a GroupViewCommand.
     *
     * @param groupId An integer that represents the group unique identifier.
     */
    public GroupViewCommand(int groupId) {
        assert groupId > 0 : Message.ASSERT_GROUPVIEW_GROUP_ID_LESS_THAN_ONE;
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
            manager.getUi().printlnMessageWithDivider(group.toString());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPVIEW_GROUP_VIEWED + groupId);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPVIEW_GROUP_NOT_VIEWED + groupId);
        }
    }
}
