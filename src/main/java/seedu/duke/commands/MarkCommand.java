package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;

public class MarkCommand extends Command {
    private static final String MARK_MESSAGE = "Nice! I have marked this task as completed!" + LS + "%s";
    private static final String UNMARK_MESSAGE = "Ok! I have marked this task for you as uncompleted!" + LS + "%s";

    private final int taskIndex;
    private final boolean status;

    public MarkCommand(int taskIndex, boolean status) {
        this.taskIndex = taskIndex;
        this.status = status;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Marks the specified task as completed or uncompleted.
     * @throws NoSuchTaskException if the user-supplied index is out of bounds
     */
    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        Module targetModule = moduleList.getGeneralTasks();
        TaskList taskList = targetModule.getTaskList();
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new NoSuchTaskException();
        }
        Task target = taskList.getTask(taskIndex);
        target.setTaskDone(status);
        if (status) {
            return new CommandResult(String.format(MARK_MESSAGE, target));
        }
        return new CommandResult(String.format(UNMARK_MESSAGE, target));
    }
}
