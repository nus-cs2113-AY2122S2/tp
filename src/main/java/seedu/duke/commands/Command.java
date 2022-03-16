package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public abstract class Command {

    /**
     * Represents whether a command is an exit command
     * */
    private boolean isExit;

    /**
     * By default, a command initialised as a non-exit command
     */
    public Command() {
        isExit = false;
    }

    /**
     * Returns whether a command is an exit command
     * */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes a command and prints any output to Ui. It also saves the output, if any, to Storage.
     * Different types of command will have different execution procedures.
     *
     * @param itemList ItemList containing all items in the current inventory
     * @param ui User Interface for reading inputs and/or printing outputs
     */
    public abstract void execute(ItemList itemList, Ui ui);

}
