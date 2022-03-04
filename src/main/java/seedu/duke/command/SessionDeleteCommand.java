package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Represents a command that deletes a session indicated by the user input.
 *
 * @author Roy
 */
public class SessionDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "session /delete";

    private static final String COMMAND_FORMAT = "session /delete /sid <SESSIONID>";

    private int sessionId;

    // Javadocs to be completed when implementing command.
    public SessionDeleteCommand(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Returns the session id for session deletion.
     *
     * @return A session id as int.
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * Runs the command to delete a session.
     *
     * @param ui      A user interface to which the command will read its input from and print its output to.
     * @param profile A Profile object from which Session, Activity and other objects are used to run
     *                the command.
     */
    @Override
    public void run(TextUI ui, Profile profile) {

    }
}
