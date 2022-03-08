package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    private static final String LIST_COMMAND_WORD = "list";
    private static final String LIST_MESSAGE_TOP = "Ok! Here are the task(s) in your list:\n";
    //private static String list = "";
    private static String listLine = "";
    //private static String LIST_MESSAGE = list;
    public static ArrayList<String> list = new ArrayList<>();

    public ListCommand(String arguments) {
        commandName = LIST_COMMAND_WORD;
    }

    @Override
    public CommandResult execute() throws ModHappyException {
        CommandResult result = new CommandResult(LIST_MESSAGE_TOP + Task.taskList.toString().replaceAll(",", "\n"));
        return result;
    }
}
