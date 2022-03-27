package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Represents a command which displays the details of each Activity object within a Session object.
 */
public class ActivityListCommand extends Command {

    public static final String COMMAND_TEXT = "activity /list";

    public static final String COMMAND_FORMAT = "Syntax: activity /list /sid [SESSION_ID]";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.SESSION_ID_DELIMITER
    };

    private int sessionId;

    private static final String LIST_HEADER_PREPEND = "List of activities (Session Id #";
    private static final String LIST_CLOSER_POSTPEND = ")";

    /**
     * Initializes an ActivityListCommand object.
     *
     * @param sessionId An integer that uniquely identifies a session.
     */
    public ActivityListCommand(int sessionId) {
        assert sessionId > 0 : Message.ASSERT_ACTIVITYLIST_SESSION_ID_LESS_THAN_ONE;
        this.sessionId = sessionId;
    }

    /**
     * Runs the command to list all existing activities in a Session object managed by the Profile Object.
     * 
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        try {
            Session sessionToBePrinted = manager.getProfile().getSession(sessionId);
            ArrayList<Activity> activityListToBePrinted = sessionToBePrinted.getActivityList();
            int activityListSize = activityListToBePrinted.size();
            if (activityListToBePrinted.isEmpty()) {
                manager.getUi().printlnMessage(Message.ERROR_ACTIVITYLIST_ACTIVITY_EMPTY);
                return;
            }

            manager.getUi().printlnMessageWithDashDivider(LIST_HEADER_PREPEND + sessionId + LIST_CLOSER_POSTPEND);
            for (int i = 0; i < activityListSize - 1; i++) {
                manager.getUi().printlnMessage(activityListToBePrinted.get(i).getActivitySummaryString());
            }
            String lastActivityToPrint = activityListToBePrinted.get(activityListSize - 1).getActivitySummaryString();
            manager.getUi().printlnMessageWithDivider(lastActivityToPrint);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
            manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYLIST_SESSION_ID_NOT_FOUND + sessionId);
        }
    }

    /**
     * Prepares user arguments for the creation of an ActivityListCommand object.
     *
     * @param  commandArgs A String object that represents the user's arguments.
     * @return An ActivityListCommand object if sessionId was found in user argument,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        try {
            int sessionId = Parser.parseSessionId(commandArgs);
            return new ActivityListCommand(sessionId);
        } catch (InvalidFormatException e) {
            String invalidCommandMessage = e.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }
}
