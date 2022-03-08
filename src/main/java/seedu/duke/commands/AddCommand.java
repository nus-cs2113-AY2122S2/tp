package seedu.duke.commands;

import java.util.HashMap;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.AddParser;
import seedu.duke.tasks.Task;



public class AddCommand extends Command {

    private static final String ADD_COMMAND_WORD = "add";
    private static final String FLAG = "flag";
    private static final String UNCOMPLETED_STATUS_TASK = " (T)( ) ";
    private static final String TASK_FLAG = "/t";
    private static final String MOD_FLAG = "-mod";
    private static final String ADD_MESSAGE_TOP = "Hey! I have added this task for you!\n";
    private static String task = "task";
    private static String mod = "mod";
    private static String taskWithStatus;
    private static String res;

    public AddCommand(String arg) throws ModHappyException {
        try {
            commandName = ADD_COMMAND_WORD;
            AddParser addParser = new AddParser();
            HashMap<String, String> parsedArg = addParser.parseString(arg);

            switch (parsedArg.get(FLAG)) {
            case TASK_FLAG:
                //add tasks
                task = parsedArg.get("argument1");
                taskWithStatus = UNCOMPLETED_STATUS_TASK + task;
                Task.taskList.add(taskWithStatus);
                res = ADD_MESSAGE_TOP
                        +  taskWithStatus + "\n"
                        + "Now you have " + Task.taskList.size() + " task(s) in your list!\n";
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
        CommandResult result = new CommandResult(res);
        return result;
    }
}
