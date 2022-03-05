package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Represents an ActivityListCommand which display activity ids, name, and totalCost of each activity within session.
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
     * @param ui      A user interface to which the command will read its input from and print its output to.
     * @param profile A Profile object from which Session, Activity and other objects are used to run.
     */
    @Override
    public void run(TextUI ui, Profile profile) {
    public void run(Manager manager) {

    }
}
