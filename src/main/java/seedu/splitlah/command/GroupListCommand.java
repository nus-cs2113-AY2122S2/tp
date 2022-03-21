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

    /**
     * Prints the list of groups previously created by the user.
     *
     * @param manager A manager object that gets the TextUI and Profile object to print the list of groups.
     */
    @Override
    public void run(Manager manager) {
        /*
        ArrayList<Group> groupsToBePrinted = manager.getProfile().getGroupList();
        if (groupsToBePrinted.isEmpty()) {
            assert groupsToBePrinted.size() == 0 : Message.ASSERT_GROUPLIST_GROUP_SIZE_NOT_ZERO;
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_SESSION_LIST_EMPTY);
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPLIST_GROUPS_NOT_LISTED);
            return;
        }

        manager.getUi().printlnMessageWithDashDivider(GROUP_LIST_HEADER);
        int groupListSize = groupsToBePrinted.size();
        assert groupListSize > 0 : Message.ASSERT_GROUPLIST_GROUP_SIZE_LESS_THAN_ONE;
        for (int i = 0; i < groupListSize - 1; i++) {
            manager.getUi().printlnMessage(groupsToBePrinted.get(i).getGroupSimplifiedString());
        }
        String lastGroupToPrint = groupsToBePrinted.get(groupListSize - 1).getGroupSimplifiedString();
        manager.getUi().printlnMessageWithDivider(lastGroupToPrint);
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPLIST_GROUPS_LISTED);
        */
    }
}
