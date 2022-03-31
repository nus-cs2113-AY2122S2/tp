package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;

/**
 * Represents a command which displays the full details of a Group object in the list of groups
 * managed by the Profile object.
 *
 * @author Ivan
 */
public class GroupViewCommand extends Command {

    private final int groupId;

    /**
     * Initializes a GroupViewCommand object.
     *
     * @param groupId An integer that represents the group unique identifier for the group to be viewed.
     */
    public GroupViewCommand(int groupId) {
        assert groupId > 0 : Message.ASSERT_GROUPVIEW_GROUP_ID_LESS_THAN_ONE;
        this.groupId = groupId;
    }

    /**
     * Runs the command with the group unique identifier as provided by the user input and
     * prints the details of the group.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        try {
            Group group = manager.getProfile().getGroup(groupId);
            assert group.getGroupId() == groupId : Message.ASSERT_GROUPVIEW_INCORRECT_GROUP;
            ui.printlnMessageWithDivider(group.toString());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPVIEW_GROUP_VIEWED + groupId);
        } catch (InvalidDataException e) {
            ui.printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPVIEW_GROUP_NOT_VIEWED + groupId);
        }
    }
}
