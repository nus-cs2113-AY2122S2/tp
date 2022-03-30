package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;

/**
 * Represents a command which deletes a Session object from a list of sessions managed by the Profile object.
 *
 * @author Roy
 */
public class SessionDeleteCommand extends Command {

    private static final String COMMAND_SUCCESS = "The session was deleted successfully.";

    private final int sessionId;

    /**
     * Initializes a SessionDeleteCommand object.
     *
     * @param sessionId An integer that uniquely identifies a session.
     */
    public SessionDeleteCommand(int sessionId) {
        assert sessionId > 0 : Message.ASSERT_SESSIONDELETE_SESSION_ID_NOT_INITIALIZED;
        this.sessionId = sessionId;
    }

    /**
     * Runs the command to delete a Session object from the list of sessions managed by a Manager Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        try {
            manager.getProfile().removeSession(sessionId);
            manager.saveProfile();
            ui.printlnMessageWithDivider(COMMAND_SUCCESS);
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONDELETE_SESSION_REMOVED + sessionId);
        } catch (InvalidDataException dataException) {
            ui.printlnMessage(dataException.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_SESSIONDELETE_SESSION_REMOVED_FAILED + sessionId);
        }
    }
}
