package seedu.duke.commands;

import java.util.HashMap;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.MarkParser;
import seedu.duke.tasks.Task;

public class MarkCommand extends Command {
    private static final String MARK_COMMAND_WORD = "mark";
    private static final String FLAG = "flag";
    private static final String COMPLETED_FLAG = "/c";
    private static final String UNCOMPLETED_FLAG = "/u";
    private static final String UNCOMPLETED_STATUS = " (T)( ) ";
    private static final String COMPLETED_STATUS = " (T)(X) ";
    private static final String MARK_MESSAGE_TOP = "Nice! I have marked this task as completed!\n";
    private static final String UNMARK_MESSAGE_TOP = "Ok! I have marked this task for you as uncompleted!\n";
    private static int taskNumber = 0;
    private static String temp;
    private static boolean isCompleted;

    public MarkCommand(String arg) throws ModHappyException {
        try {
            commandName = MARK_COMMAND_WORD;
            MarkParser markParser = new MarkParser();
            HashMap<String, String> parsedArg = markParser.parseString(arg);
            int index;
            switch (parsedArg.get(FLAG)) {
            case COMPLETED_FLAG:
                //to mark as completed
                isCompleted = true;
                taskNumber = Integer.parseInt(parsedArg.get("argument1"));
                index = taskNumber - 1;
                temp = Task.taskList.get(index);
                temp = temp.replace(UNCOMPLETED_STATUS, COMPLETED_STATUS);
                //System.out.println(temp);
                Task.taskList.set(index, temp);
                break;
            case UNCOMPLETED_FLAG:
                //to mark as uncompleted
                isCompleted = false;
                taskNumber = Integer.parseInt(parsedArg.get("argument1"));
                index = taskNumber - 1;
                temp = Task.taskList.get(index);
                temp = temp.replace(COMPLETED_STATUS, UNCOMPLETED_STATUS);
                Task.taskList.set(index, temp);
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
        String res = (isCompleted) ? MARK_MESSAGE_TOP
                + temp + "\n"
                : UNMARK_MESSAGE_TOP + temp + "\n";
        CommandResult result = new CommandResult(res);
        return result;
    }
}
