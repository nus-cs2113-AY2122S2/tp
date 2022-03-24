package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;

/**
 * Represents a command that displays the details of each Session object within a Profile object.
 *
 * @author Roy
 */
public class SessionListCommand extends Command {

    private static final String SESSION_LIST_HEADER = "List of Sessions";

    /**
     * Runs the command to list all existing sessions managed by the Profile Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        ArrayList<Session> sessionsToBePrinted = manager.getProfile().getSessionList();
        int sessionListSize = sessionsToBePrinted.size();
        if (sessionsToBePrinted.isEmpty()) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_SESSION_LIST_EMPTY);
            return;
        }
        manager.getUi().printlnMessageWithDashDivider(SESSION_LIST_HEADER);
        for (int i = 0; i < sessionListSize - 1; i++) {
            manager.getUi().printlnMessage(sessionsToBePrinted.get(i).getSessionSimplifiedString());
            manager.getUi().printDashDivider();
        }
        String lastSessionToPrint = sessionsToBePrinted.get(sessionListSize - 1).getSessionSimplifiedString();
        manager.getUi().printlnMessageWithDivider(lastSessionToPrint);
    }
}
