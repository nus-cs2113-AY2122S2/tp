package seedu.duke.commands;

import seedu.duke.exceptions.InstantiateAbstractClassException;

/**
 * Parent class of all commands in Mod Happy.
 */
public abstract class Command {
    protected String commandName = "Command";

    public String execute() throws InstantiateAbstractClassException {
        throw new InstantiateAbstractClassException(commandName);
    }
}
