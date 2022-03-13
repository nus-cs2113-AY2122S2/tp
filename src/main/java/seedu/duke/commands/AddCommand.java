package seedu.duke.commands;

import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.ui.TextUi;

public class AddCommand extends Command {
    private static final String ADD_TASK_MESSAGE = TextUi.ADD_TASK_MESSAGE_TOP + LS + "%s" + LS
            + TextUi.ADD_TASK_MESSAGE_BOTTOM + LS;
    private static final String ADD_MODULE_MESSAGE = TextUi.ADD_MODULE_MESSAGE_TOP + LS + "%s";

    private final boolean isAddTask;
    private Task newTask = null;
    private Module newModule = null;

    public AddCommand(String name, String description, boolean isTask, String estimatedWorkingTime) {
        if (isTask) {
            newTask = new Task(name, description, estimatedWorkingTime);
            isAddTask = true;
        } else {
            newModule = new Module(name, description);
            isAddTask = false;
        }
    }

    public Task getNewTask() {
        return newTask;
    }

    public Module getNewModule() {
        return newModule;
    }

    /**
     * Adds the specified task or module.
     */
    @Override
    public CommandResult execute(ModuleList moduleList) {
        String res = "";
        if (isAddTask) {
            // TODO: change this once support for -mod is implemented
            Module targetModule = moduleList.getGeneralTasks();
            TaskList taskList = targetModule.getTaskList();
            res = String.format(ADD_TASK_MESSAGE, targetModule, taskList.addTask(newTask), taskList.size());
        } else {
            if (!moduleList.isModuleExists(newModule.getModuleCode())) {
                res = String.format(ADD_MODULE_MESSAGE, moduleList.addModule(newModule));
            } else {
                res = TextUi.MODULE_ALREADY_EXISTS;
            }
        }
        return new CommandResult(res);
    }
}
