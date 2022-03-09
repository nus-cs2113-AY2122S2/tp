package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents a command which displays the details of an Activity object specified by user input in a Session object.
 * 
 * @author Tianle
 */
public class ActivityViewCommand extends Command {

    public static final String COMMAND_TEXT = "activity /view";

    private static final String COMMAND_FORMAT = "Syntax: activity /view /sid <SESSIONID> /aid <ACTIVITYID>";

    private int sessionId;
    private int activityId;

    public ActivityViewCommand(int sessionId, int activityId) {
        this.sessionId = sessionId;
        this.activityId = activityId;
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
