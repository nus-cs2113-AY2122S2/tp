package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.ui.Message;

import java.util.logging.Level;

/**
 * Represents a command that displays the details of each Session object within a Profile object.
 *
 * @author Roy
 */
public class SessionListCommand extends Command {

    /**
     * Runs the command to list all existing sessions managed by the Profile Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        String sessionListSummary = manager.getProfile().getSessionListSummaryString();
        manager.getUi().printlnMessage(sessionListSummary);
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONLIST_SESSIONS_LISTED);
    }
}
