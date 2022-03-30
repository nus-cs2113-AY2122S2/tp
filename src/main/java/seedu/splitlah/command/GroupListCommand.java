package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.ui.Message;

import java.util.logging.Level;

/**
 * Represents a command that displays the details of each Group object within a Profile object.
 *
 * @author Ivan
 */
public class GroupListCommand extends Command {

    public static final String COMMAND_TEXT = "group /list";

    public static final String COMMAND_FORMAT = "Syntax: group /list";

    /**
     * Runs the command to list all existing groups managed by the Profile Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        String groupListSummary = manager.getProfile().getGroupListSummaryString();
        manager.getUi().printlnMessage(groupListSummary);
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_GROUPLIST_GROUPS_LISTED);
    }
}
