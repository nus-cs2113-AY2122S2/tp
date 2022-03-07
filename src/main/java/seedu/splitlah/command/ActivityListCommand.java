package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents an ActivityListCommand which display activity ids, name, and totalCost of each activity within session.
 */
public class ActivityListCommand extends Command {

    public static final String COMMAND_TEXT = "activity /list";

    private static final String COMMAND_FORMAT = "Syntax: activity /list /sid <SESSION_ID>";


    private int sessionId;


    public ActivityListCommand(int sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Runs the command.
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {

    }
}
