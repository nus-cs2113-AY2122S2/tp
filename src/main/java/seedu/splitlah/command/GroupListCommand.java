package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;

/**
 * Represents a command that displays the details of each Group object within a Profile object.
 *
 * @author Ivan
 */
public class GroupListCommand extends Command {

    public static final String COMMAND_TEXT = "group /list";

    public static final String COMMAND_FORMAT = "Syntax: group /list";

    private static final String GROUP_LIST_HEADER = "List of Groups";

    @Override
    public void run(Manager manager) {
        /*
        ArrayList<Group> groupsToBePrinted = manager.getProfile().getGroupList();
        if (groupsToBePrinted.isEmpty()) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_SESSION_LIST_EMPTY);
            return;
        }

        manager.getUi().printlnMessageWithDashDivider(GROUP_LIST_HEADER);
        int groupListSize = groupsToBePrinted.size();
        for (int i = 0; i < groupListSize - 1; i++) {
            manager.getUi().printlnMessage(groupsToBePrinted.get(i).getGroupSimplifiedString());
        }
        String lastGroupToPrint = groupsToBePrinted.get(groupListSize - 1).getGroupSimplifiedString();
        manager.getUi().printlnMessageWithDivider(lastGroupToPrint);
         */
    }
}
