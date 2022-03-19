package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

public class DeleteCommand extends Command {

    private static final String DELETE_MESSAGE = StringConstants.DELETE_MESSAGE;

    private String moduleCode;
    private int taskIndex = -1;
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
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
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
        result = String.format(DELETE_MESSAGE, moduleList.removeModule(moduleCode));
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
