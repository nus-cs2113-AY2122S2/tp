package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_NAME = "Exit";
    public static final String USAGE_MESSAGE = "Terminates the application";
    public static final String COMMAND_FORMAT = COMMAND_WORD;
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    public void execute(ItemList itemList, Ui ui) {
        ui.showMessages("bye");
    }

    /**
     * Returns True to override the default as this is a command to exit the program.
     *
     * @return True to indicate this is a Command to exit the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
