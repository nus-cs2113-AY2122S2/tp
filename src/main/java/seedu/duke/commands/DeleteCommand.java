package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;
import seedu.duke.util.NumberConstants;

public class DeleteCommand extends Command {

    private static final String DELETE_MESSAGE = StringConstants.DELETE_MESSAGE;
    private static final String DELETE_ABORT = StringConstants.DELETE_ABORT;


    private String moduleCode;
    private int taskIndex = NumberConstants.INVALID_TASK_INDEX;
    private String taskModule;
    private String result;

    public String getModuleCode() {
        return moduleCode;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public String getTaskModule() {
        return taskModule;
    }

    public DeleteCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public DeleteCommand(int taskIndex, String taskModule) {
        this.taskIndex = taskIndex;
        this.taskModule = taskModule;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        if (taskIndex < 0) {
            deleteModule(moduleList);
        } else {
            Module targetModule;
            if (Objects.isNull(taskModule)) {
                targetModule = moduleList.getGeneralTasks();
            } else {
                targetModule = moduleList.getModule(taskModule);
                if (Objects.isNull(targetModule)) {
                    throw new NoSuchModuleException();
                }
            }
            deleteTaskFromModule(targetModule);
        }
        return new CommandResult(result);
    }

    /**
     * Deletes given module from moduleList.
     *
     * @param moduleList List from which the module is to be deleted from.
     */
    public void deleteModule(ModuleList moduleList) throws ModHappyException {
        Module removedModule = moduleList.removeModule(moduleCode);
        if (Objects.isNull(removedModule)) {
            result = DELETE_ABORT;
        } else {
            result = String.format(DELETE_MESSAGE, removedModule);
        }
    }

    /**
     * Deletes a task from the given module.
     *
     * @param targetModule The module from which to delete the task
     */
    public void deleteTaskFromModule(Module targetModule) throws ModHappyException {
        TaskList taskList = targetModule.getTaskList();
        result = String.format(DELETE_MESSAGE, taskList.removeTask(taskIndex));
    }
}
