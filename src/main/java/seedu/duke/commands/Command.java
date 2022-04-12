package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

import java.util.List;

public abstract class Command {

    /**
     * Represents whether a command is an exit command.
     * */
    private boolean isExit;

    /**
     * Used only for tests. Applicable only for commands that do not directly modify an ItemList.
     * They can use this to temporarily store their results, and can be referenced via immutable copies.
     * Since its a List, note that order matters.
     */
    List<Item> results;

    /**
     * By default, a command initialised as a non-exit command.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Returns whether a command is an exit command.
     * */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes a command and prints any output to Ui. It also saves the output, if any, to Storage.
     * Different types of command will have different execution procedures.
     * Only child classes should call this function.
     *
     * @param itemList ItemList containing all items in the current inventory.
     * @param ui User Interface for reading inputs and/or printing outputs.
     */
    public void execute(ItemList itemList, Ui ui) throws InvMgrException {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the result of this Command, if applicable.
     * This will throw a NullPointerExecption if {@code results} is not initialised.
     *
     * @return a List of Items representing the result for a Command.
     */
    public List<Item> getResults() {
        return List.copyOf(this.results);
    }

}
