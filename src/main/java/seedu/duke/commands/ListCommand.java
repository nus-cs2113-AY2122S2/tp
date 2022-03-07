package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;

public class ListCommand extends Command{

    private static final String LIST_COMMAND_WORD = "list";
    private static final String LIST_MESSAGE_TOP = "Ok! Here are the task(s) in your list:";

    public ListCommand(String arguments) {
        commandName = LIST_COMMAND_WORD;
    }

    @Override
    public CommandResult execute() throws ModHappyException {
        CommandResult result = new CommandResult("\n");
        return result;
    }
}
