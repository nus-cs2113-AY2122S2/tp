package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.Task;

public class ListCommand extends Command {

    private static final String LIST_COMMAND_WORD = "list";
    private static final String LIST_MESSAGE_TOP = "Ok! Here are the task(s) in your list:";

    public ListCommand(String arguments) {
        commandName = LIST_COMMAND_WORD;
        System.out.println(LIST_MESSAGE_TOP);
        for (int j = 1; j <= Task.taskList.size(); j++) {
            System.out.println(j + ". " + Task.taskList.get(j - 1));
        }
    }

    @Override
    public CommandResult execute() throws ModHappyException {
        CommandResult result = new CommandResult("");
        return result;
    }
}
