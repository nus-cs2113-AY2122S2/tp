package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, ItemList itemList);

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
}
