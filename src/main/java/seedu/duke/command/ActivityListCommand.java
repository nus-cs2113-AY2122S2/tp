package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Represents an ActivityListCommand which display activity ids, name, cost and payer within session".
 */

public class ActivityListCommand extends Command {

    public static final String COMMAND_TEXT = "activity /list";

    private static final String COMMAND_FORMAT = "Syntax: activity /list /s <SESSION_ID>";


    private int sessionId;


    public ActivityListCommand(int sessionId) {
        this.sessionId = sessionId;
    }


    @Override
    public void run(TextUI ui, Profile profile) {

    }
}
