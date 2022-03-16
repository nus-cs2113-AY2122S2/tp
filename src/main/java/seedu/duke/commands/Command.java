package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public abstract class Command {

    /**
     * Executes a command and prints any output to Ui. It also saves the output, if any, to Storage.
     * Different types of command will have different execution procedures.
     *
     * @param itemList ItemList containing all items in the current inventory
     * @param ui User Interface for reading inputs and/or printing outputs
     */
    public abstract void execute(Ui ui, ItemList itemList);

    /**
     * Returns false by default if the command is not a command to exit the program.
     * Override this method to return true if it is an exit command.
     *
     * @return False if the command is a command to exit the program. Returns True otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
