package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;

/**
 * Represents a command which deletes an Activity object from a Session object.
 */
public class ActivityDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "activity /delete";

    private static final String COMMAND_FORMAT = "Syntax: activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]";

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

    /**
     * Prepares user arguments for activity delete command.
     *
     * @param commandArgs The user's arguments.
     * @return An ActivityDeleteCommand object if necessary parameters were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        try {
            int sessionId = Parser.parseSessionId(commandArgs);
            int activityId = Parser.parseActivityId(commandArgs);
            return new ActivityDeleteCommand(sessionId, activityId);
        } catch (InvalidFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT);
        }
    }

    @Override
    public void run(Manager manager) {

    }
}
