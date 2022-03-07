package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;


public class AddCommand extends Command{

    private static final String ADD_COMMAND_WORD = "add";
    private static final String ADD_MESSAGE = "Hey! I have added this task for you!\n"
            /*+ task + System.lineSeparator()
            + "Now you have " + task.number + " task(s) in your list!";*/

    public AddCommand(String arguments) {
        commandName = ADD_COMMAND_WORD;
    }

    @Override
    public CommandResult execute() throws ModHappyException {
        CommandResult result = new CommandResult(ADD_MESSAGE);
        return result;
    }
}
