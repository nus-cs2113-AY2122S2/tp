package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;


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

    public static final String SESSION_ID_HEADER = "Session Id #";
    public static final String SEPARATOR = " | ";

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
