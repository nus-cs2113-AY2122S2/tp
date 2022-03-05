package seedu.duke.command;

import seedu.duke.data.Manager;

/**
 * Represents a command that deletes a session indicated by the user input.
 *
 * @author Roy
 */
public class SessionDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "session /delete";

    private static final String COMMAND_FORMAT = "Syntax: session /delete /sid <SESSIONID>";

    private int sessionId;

    // Javadocs to be completed when implementing command.
    public SessionDeleteCommand(int sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Runs the command to delete a session.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {

    }
}
