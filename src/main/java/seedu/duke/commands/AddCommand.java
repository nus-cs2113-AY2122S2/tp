package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;


public class AddCommand extends Command {
    private static final String ADD_TASK_MESSAGE = "Hey! I have added this task for you!" + LS + "%s" + LS
            + "Now you have %d task(s) in your list!" + LS;
    private static String task = "task";
    private static String mod = "mod";

    private boolean isAddTask;
    private Task newTask;

    public AddCommand(String name, String description, boolean isTask) {
        if (isTask) {
            newTask = new Task(name, description);
            isAddTask = true;
        } else {
            isAddTask = false;
        }
    }

    @Override
    public CommandResult execute(TaskList list) throws ModHappyException {
        if (isAddTask) {
            String res = String.format(ADD_TASK_MESSAGE, list.addTask(newTask), list.size());
            return new CommandResult(res);
        }
        return null;
    }
}
