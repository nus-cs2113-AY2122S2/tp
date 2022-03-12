package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

/**
 * Represents a command which deletes an Activity object from a Session object.
 *
 * @author Ivan
 */
public class ActivityDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "activity /delete";

    private static final String COMMAND_FORMAT = "Syntax: activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]";

    private static final String COMMAND_CONFIRMATION = "Are you sure you want to delete activity id: ";

    private static final String COMMAND_ABORT = "Okay! Activity was not deleted.";

    private static final String COMMAND_SUCCESS = "The activity was deleted successfully.";

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
     * Prepares user arguments for the creation of an ActivityDeleteCommand object.
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

    /**
     * Runs the command to delete an Activity object from the list of activities managed by a Session Object.
     * Gets the Session object using a unique session identifier.
     * Requests for confirmation from user to delete the Activity object.
     * If user confirms, proceeds to remove activity from a Session object,
     * the command aborts otherwise.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        Session session = null;
        try {
            session = manager.getProfile().getSession(sessionId);
            boolean isActivityExists = session.hasActivity(activityId);
            if (!isActivityExists) {
                manager.getUi().printlnMessage(Message.ERROR_SESSION_ACTIVITY_ID_NOT_IN_LIST);
                return;
            }
            String confirmationPrompt = COMMAND_CONFIRMATION + activityId + "?";
            boolean isConfirmed = manager.getUi().getUserConfirmation(confirmationPrompt);
            if (isConfirmed) {
                session.removeActivity(activityId);
                manager.getUi().printlnMessage(COMMAND_SUCCESS);
            } else {
                manager.getUi().printlnMessage(COMMAND_ABORT);
            }
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
        }
    }
}
