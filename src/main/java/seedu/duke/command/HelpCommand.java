package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Represents a command that when run, prints a help menu to assist the user
 * by informing the user of all available commands.
 * 
 * @author Warren
 */
public class HelpCommand extends Command {
    public static final String COMMAND_TEXT = "help";

    private static final String COMMAND_FORMAT = "Syntax: help";

    /**
     * Prints a help message and displays all available commands.
     *
     * @param ui      A user interface print the help message
     * @param profile A Profile object from which Session, Activity and other objects are used to run
     *                the command.
     */
    @Override
    public void run(TextUI ui, Profile profile) {
        ui.printHelpMenu();
    }
}
