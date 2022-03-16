package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ExitCommand extends Command {
    public void execute(ItemList itemList, Ui ui) {
        ui.showOutput("bye");
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
