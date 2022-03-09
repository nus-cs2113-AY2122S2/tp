package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents a command which displays the details of each Activity object within a Session object.
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
     * 
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {

    }
}
