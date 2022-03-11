package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;

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
        try {
            Session sessionToBePrinted = manager.getProfile().getSession(sessionId);
            ArrayList<Activity> activityListToBePrinted = sessionToBePrinted.getActivityList();

            if (activityListToBePrinted.isEmpty()) {
                manager.getUi().printlnMessage(Message.ERROR_ACTIVITYLIST_ACTIVITY_EMPTY);
                return;
            }

            for (Activity activity : activityListToBePrinted) {
                manager.getUi().printlnMessageWithDivider(activity.toString());
            }

        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
        }

    }


    /**
     * Prepares user argument for activity list command.
     *
     * @param commandArgs A String object that represents the user's arguments.
     * @return An ActivityListCommand object if sessionId was found in user argument,
     *          an InvalidCommand object otherwise.
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
