package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import java.util.logging.Level;

/**
 * Represents a command which deletes an Activity object from a Session object.
 *
 * @author Ivan
 */
public class ActivityDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "activity /delete";

    public static final String COMMAND_FORMAT = "Syntax: activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]";

    private static final String COMMAND_SUCCESS = "The activity was deleted successfully.";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.SESSION_ID_DELIMITER,
        ParserUtils.ACTIVITY_ID_DELIMITER 
    };
    
    private int sessionId;
    private int activityId;

    /**
     * Initializes an ActivityDeleteCommand object.
     *
     * @param sessionId  An integer that uniquely identifies a session.
     * @param activityId An integer that uniquely identifies an activity.
     */
    public ActivityDeleteCommand(int sessionId, int activityId) {
        assert sessionId > 0 : Message.ASSERT_ACTIVITYDELETE_SESSION_ID_NOT_INITIALIZED;
        assert activityId > 0 : Message.ASSERT_ACTIVITYDELETE_ACTIVITY_ID_NOT_INITIALIZED;
        this.sessionId = sessionId;
        this.activityId = activityId;
    }

    /**
     * Prepares user arguments for the creation of an ActivityDeleteCommand object.
     *
     * @param commandArgs A String object representing the user's arguments.
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
            assert session != null : Message.ASSERT_ACTIVITYDELETE_SESSION_IS_NULL;
            session.removeActivity(activityId);
            manager.getUi().printlnMessage(COMMAND_SUCCESS);
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYDELETE_ACTIVITY_REMOVED + activityId);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
        }
    }
}
