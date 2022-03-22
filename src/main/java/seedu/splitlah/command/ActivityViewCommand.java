package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;


/**
 * Represents a command which displays the details of an Activity object specified by user input in a Session object.
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

    private int sessionId;
    private int activityId;

    private static final String SESSION_ID_HEADER = "Session Id #";
    private static final String SEPARATOR = " | ";

    /**
     * Initializes an ActivityViewCommand object.
     *
     * @param sessionId  An integer that uniquely identifies a session.
     * @param activityId An integer that uniquely identifies an activity.
     */
    public ActivityViewCommand(int sessionId, int activityId) {
        this.sessionId = sessionId;
        this.activityId = activityId;
    }

    /**
     * Runs the command with the session and activity unique identifier as provided by the user input and
     * prints the details of the activity.
     * 
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        try {
            Session session = manager.getProfile().getSession(sessionId);
            Activity activityToBePrinted = session.getActivity(activityId);
            String messageToBePrinted = SESSION_ID_HEADER + sessionId + SEPARATOR + activityToBePrinted.toString();
            manager.getUi().printlnMessageWithDivider(messageToBePrinted);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
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
            int sessionId = Parser.parseSessionId(commandArgs);
            int activityId = Parser.parseActivityId(commandArgs);
            return new ActivityViewCommand(sessionId, activityId);
        } catch (InvalidFormatException e) {
            String invalidCommandMessage = e.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }
}
