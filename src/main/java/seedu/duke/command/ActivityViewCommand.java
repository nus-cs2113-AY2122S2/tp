package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Represents an ActivityViewCommand which display activity ids, name, and totalCost of each activity within session.
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

    @Override
    public void run(TextUI ui, Profile profile) {

    }
}
