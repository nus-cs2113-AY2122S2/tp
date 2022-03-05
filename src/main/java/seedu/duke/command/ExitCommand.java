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
     * Checks if command object is an instance of an ExitCommand.
     *
     * @param command A command object to be checked.
     * @return True if it is an ExitCommand, else false.
     */
    public static boolean isExitCommand(Command command) {
        return command instanceof ExitCommand;
    }

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
    }
}
