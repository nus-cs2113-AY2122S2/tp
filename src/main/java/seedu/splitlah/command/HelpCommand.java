package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

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
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        manager.getUi().printHelpMenu();
    }
}
