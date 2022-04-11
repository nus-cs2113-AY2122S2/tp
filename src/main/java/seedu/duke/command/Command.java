package seedu.duke.command;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;

import java.io.IOException;

/**
 * Abstract class that generalizes all Commands.
 * Contains an abstract "execute" method and keeps track of
 * whether the program should exit.
 */

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    /**
     * Abstract execute command that all Command classes should
     * override based on their specific behavior.
     * @param ui The instance of the Ui class (used for printing additional messages when a command is executed.
     * @throws HotelLiteManagerException If there is any error with user input.
     */
    public abstract Object execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, IOException;

    public boolean isExit() {
        return this.isExit;
    }


}
