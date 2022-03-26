package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Profile;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;

/**
 * Represents a command object that produces a summary of expenditure for a specified Session object.
 *
 * @author Warren
 */
public class SessionViewCommand extends Command {

    private final int sessionId;

    // MISC CONSTANTS
    private static final String VIEW_HEADER_PREPEND = "-- (Session Id #";
    private static final String VIEW_HEADER_POSTPEND = ") --";

    /**
     * Initializes a SessionSummaryCommand object.
     *
     * @param sessionId An integer that uniquely identifies a session.
     */
    public SessionViewCommand(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Runs the command with the session identifier as provided by the user input and prints a
     * summary of expenditure for the session specified by the session identifier.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        Profile profile = manager.getProfile();
        TextUI ui = manager.getUi();
        Session session;
        try {
            session = profile.getSession(sessionId);
            System.out.println(VIEW_HEADER_PREPEND + sessionId + VIEW_HEADER_POSTPEND);
            System.out.println(session);
        } catch (InvalidDataException e) {
            ui.printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONVIEW_SESSION_VIEW_FAILED + "\n"
                    + e.getMessage());
            return;
        }

        Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONVIEW_SESSION_VIEWED + sessionId);
    }
}
