package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Profile;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;

/**
 * Represents a command that displays the full details of a specified Session object.
 *
 * @author Saurav
 */
public class SessionViewCommand extends Command {

    private final int sessionId;

    /**
     * Initializes a SessionViewCommand object.
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
            ui.printlnMessageWithDivider(session.toString());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONVIEW_SESSION_VIEWED + sessionId);
        } catch (InvalidDataException e) {
            ui.printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONVIEW_SESSION_VIEW_FAILED + sessionId);
        }
    }
}
