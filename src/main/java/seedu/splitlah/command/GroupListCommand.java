package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Represents a command that displays the details of each Group object within a Profile object.
 *
 * @author Ivan
 */
public class GroupListCommand extends Command {

    public static final String COMMAND_TEXT = "group /list";

    public static final String COMMAND_FORMAT = "Syntax: group /list";

    private static final String GROUP_LIST_HEADER = "List of Groups";

    private static final String GROUP_LIST_TITLE = "# | Name | Number of Persons";

    /**
     * Runs the command to list all existing groups managed by the Profile Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        ArrayList<Group> groupsToBePrinted = manager.getProfile().getGroupList();
        if (groupsToBePrinted.isEmpty()) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_GROUP_LIST_EMPTY);
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPLIST_GROUPS_NOT_LISTED);
            return;
        }

        manager.getUi().printlnMessageWithDashDivider(GROUP_LIST_HEADER);
        manager.getUi().printlnMessage(GROUP_LIST_TITLE);
        int groupListSize = groupsToBePrinted.size();
        assert groupListSize > 0 : Message.ASSERT_GROUPLIST_GROUP_SIZE_LESS_THAN_ONE;
        for (int i = 0; i < groupListSize - 1; i++) {
            manager.getUi().printlnMessage(groupsToBePrinted.get(i).getGroupSummary());
        }
        String lastGroupToPrint = groupsToBePrinted.get(groupListSize - 1).getGroupSummary();
        manager.getUi().printlnMessageWithDivider(lastGroupToPrint);
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPLIST_GROUPS_LISTED);
    }
}
