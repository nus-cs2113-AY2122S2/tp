package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;

/**
 * Parent class of all commands in Mod Happy.
 */
public abstract class Command {
    protected String commandName = "Command";

    public abstract CommandResult execute() throws ModHappyException;

    public String getCommandName() {
        return commandName;
    }
}
