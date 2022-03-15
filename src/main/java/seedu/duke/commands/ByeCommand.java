package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String COMMAND_NAME = "Exit";
    public static final String USAGE_MESSAGE = "Terminates the program";
    public static final String COMMAND_FORMAT = COMMAND_WORD;
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    public void execute(Ui ui, ItemList itemList) {
        ui.showOutput("bye");
    }
}
