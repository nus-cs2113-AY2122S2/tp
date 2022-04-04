package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;


/**
 * Represents a command which displays the full details of an Activity object in a Session object.
 * 
 * @author Tianle
 */
public class ActivityViewCommand extends Command {

    public static final String COMMAND_TEXT = "activity /view";

    public static final String COMMAND_FORMAT = "Syntax: activity /view /sid [SESSION_ID] /aid [ACTIVITY_ID]";

    public static final String[] COMMAND_DELIMITERS = { 
        ParserUtils.SESSION_ID_DELIMITER,
        ParserUtils.ACTIVITY_ID_DELIMITER
    };

    private final int sessionId;
    private final int activityId;

    private static final String SESSION_ID_HEADER = "Session Id #";
    private static final String ACTIVITY_ID_HEADER = "Activity Id #";
    private static final String SEPARATOR = " | ";

    /**
     * Initializes an ActivityViewCommand object.
     *
     * @param sessionId  An integer that uniquely identifies a session.
     * @param activityId An integer that uniquely identifies an activity.
     */
    public ActivityViewCommand(int sessionId, int activityId) {
        assert activityId > 0 : Message.ASSERT_ACTIVITYVIEW_ACTIVITY_ID_LESS_THAN_ONE;
        assert sessionId > 0 : Message.ASSERT_ACTIVITYVIEW_SESSION_ID_LESS_THAN_ONE;
        this.sessionId = sessionId;
        this.activityId = activityId;
    }

    /**
     * Runs the command with the session and activity unique identifier as provided by the user input and
     * prints the details of the activity.
     * 
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        String logMessage = SESSION_ID_HEADER + sessionId + SEPARATOR + ACTIVITY_ID_HEADER + activityId;
        try {
            Session session = manager.getProfile().getSession(sessionId);
            Activity activityToBePrinted = session.getActivity(activityId);
            String messageToBePrinted = SESSION_ID_HEADER + sessionId + SEPARATOR + activityToBePrinted.toString();
            ui.printlnMessage(messageToBePrinted);
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYVIEW_ACTIVITY_VIEWED + logMessage);
        } catch (InvalidDataException e) {
            ui.printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYVIEW_ACTIVITY_NOT_VIEWED + logMessage);
        }
    }

    /**
     * Prepares user argument for activity view command.
     *
     * @param  commandArgs  A String object that represents the user's arguments.
     * @return An ActivityViewCommand object if sessionId and activityId were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        try {
            int sessionId = ParserUtils.parseSessionId(commandArgs);
            int activityId = ParserUtils.parseActivityId(commandArgs);
            return new ActivityViewCommand(sessionId, activityId);
        } catch (InvalidFormatException e) {
            String invalidCommandMessage = e.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }
}
