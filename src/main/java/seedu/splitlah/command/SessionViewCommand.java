package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Profile;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

/**
 * Represents a command object that produces a summary of expenditure for a specified Session object.
 *
 * @author Warren
 */
public class SessionViewCommand extends Command {

    private int sessionId;

    // MISC CONSTANTS
    private static final String SUMMARY_HEADER_PREPEND = "(Session Id #";
    private static final String SUMMARY_HEADER_POSTPEND = ") --";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final int ZERO_INDEXING_OFFSET = 1;

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
            System.out.println(session);
        } catch (InvalidDataException exception) {
            ui.printlnMessage(exception.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONVIEW_SESSION_VIEW_FAILED + sessionId);
            return;
        }

        Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONSUMMARY_SESSION_SUMMARY_PRINTED + sessionId);
    }
}
