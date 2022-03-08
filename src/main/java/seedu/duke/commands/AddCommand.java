package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.AddParser;
import seedu.duke.parsers.ModHappyParser;
import seedu.duke.tasks.Task;

import java.util.HashMap;


public class AddCommand extends Command {

    private static final String ADD_COMMAND_WORD = "add";
    private static final String FLAG = "flag";
    private static final String TASK_FLAG = "/t";
    private static final String MOD_FLAG = "-mod";
    private static final String ADD_MESSAGE_TOP = "Hey! I have added this task for you!\n";
    //private static final String ADD_MESSAGE_BOTTOM = "Now you have " + Task.taskList.size() + " task(s) in your list!\n";
    private static String task = "task";
    private static String mod = "mod";

    public AddCommand(String arg) throws ModHappyException {
        try {
            commandName = ADD_COMMAND_WORD;
            AddParser addParser = new AddParser();
            HashMap<String, String> parsedArg = addParser.parseString(arg);
            switch (parsedArg.get(FLAG)) {
            case TASK_FLAG:
                //add tasks
                task = parsedArg.get("argument1");
                //Task.taskList.add(task);
                break;
            default:
                throw new UnsupportedOperationException();
            }
        } catch (ModHappyException e) {
            throw e;
        }
    }

    @Override
    public CommandResult execute() throws ModHappyException {
        CommandResult result = new CommandResult(ADD_MESSAGE_TOP + task + "\n");
        return result;
    }
}
