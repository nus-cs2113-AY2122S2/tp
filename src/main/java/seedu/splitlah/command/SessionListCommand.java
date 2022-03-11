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

    public static final String COMMAND_TEXT = "session /list";

    private static final String COMMAND_FORMAT = "Syntax: session /list";

    /**
     * Prints the list of sessions previously created by the user.
     *
     * @param manager A manager object that gets the TextUI and Profile object to print the list of sessions.
     */
    @Override
    public void run(Manager manager) {
        ArrayList<Session> sessionsToBePrinted = manager.getProfile().getSessionList();
        if (sessionsToBePrinted.isEmpty()) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_SESSION_LIST_EMPTY);
            return;
        }
        for (Session session : sessionsToBePrinted) {
            manager.getUi().printlnMessageWithDivider(session.toString());
        }
    }
}
