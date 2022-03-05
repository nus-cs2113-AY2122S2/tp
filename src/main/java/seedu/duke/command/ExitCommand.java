package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_TEXT = "exit";

    private static final String COMMAND_FORMAT = "Syntax: exit";


    /**
     * Runs the command to exit the application.
     *
     * @param ui      A user interface to which the command will read its input from and print its output to.
     * @param profile A Profile object from which Session, Activity and other objects are used to run
     *                the command.
     */
    @Override
    public void run(TextUI ui, Profile profile) {

    }
}
