package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents a command which deletes an Activity object from a Session object.
 */
public class ActivityDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "activity /delete";
    private static final String COMMAND_FORMAT = "Syntax: activity /delete /sid <SESSIONID> /aid <ACTIVITYID>";

    private int sessionId;
    private int activityId;

    /**
     * Constructs an ActivityDeleteCommand object.
     *
     * @param sessionId  The id of the session.
     * @param activityId The id of the activity.
     */
    public ActivityDeleteCommand(int sessionId, int activityId) {
        this.sessionId = sessionId;
        this.activityId = activityId;
    }

    @Override
    public void run(Manager manager) {

    }
}
