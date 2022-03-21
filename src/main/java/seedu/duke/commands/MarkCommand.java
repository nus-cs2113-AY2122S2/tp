package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

public class MarkCommand extends Command {
    private static final String MARK_MESSAGE = StringConstants.MARK_MESSAGE_TOP + LS + "%s";
    private static final String UNMARK_MESSAGE = StringConstants.UNMARK_MESSAGE_TOP + LS + "%s";

    private final int taskIndex;
    private final String taskModuleString;
    private final boolean status;

    public MarkCommand(int taskIndex, String taskModuleString, boolean status) {
        this.taskIndex = taskIndex;
        this.taskModuleString = taskModuleString;
        this.status = status;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public String getTaskModuleString() {
        return taskModuleString;
    }

    /**
     * Marks the specified task as completed or uncompleted.
     * @throws NoSuchTaskException if the user-supplied index is out of bounds
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        Module targetModule = moduleList.getGeneralTasks();
        if (!Objects.isNull(taskModuleString)) {
            targetModule = moduleList.getModule(taskModuleString);
            if (Objects.isNull(targetModule)) {
                throw new NoSuchModuleException();
            }
        }
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
