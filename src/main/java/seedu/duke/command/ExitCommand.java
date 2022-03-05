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
     * Prints the farewell message.
     *
     * @param ui      A user interface print the farewell message
     * @param profile A Profile object from which Session, Activity and other objects are used to run
     *                the command.
     */
    @Override
    public void run(TextUI ui, Profile profile) {
        ui.printFarewell();
    public void run(Manager manager) {
    }
}
