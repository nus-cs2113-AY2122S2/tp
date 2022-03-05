package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

public class HelpCommand extends Command {
    public static final String COMMAND_TEXT = "help";

    private static final String COMMAND_FORMAT = "Syntax: help";

    @Override
    public void run(TextUI ui, Profile profile) {
        ui.printHelpMenu();
    }
}
