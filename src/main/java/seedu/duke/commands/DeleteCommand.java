package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.TaskList;

import java.util.Objects;

public class DeleteCommand extends Command {

    private static final String DELETE_MODULE_SUCCESS = "%s has been deleted.";
    private static final String DELETE_TASK_SUCCESS = "%s has been deleted.";

    private String moduleCode;
    private int taskNumber = -1;
    private String taskModule;
    private String result;

    public String getModuleCode() {
        return moduleCode;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTaskModule() {
        return taskModule;
    }

    public DeleteCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public DeleteCommand(int taskNumber, String taskModule) {
        this.taskNumber = taskNumber;
        this.taskModule = taskModule;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        if (taskNumber < 0) {
            deleteModule(moduleList);
        } else {
            deleteTaskFromModule(moduleList);
        }
        return new CommandResult(result);
    }

    /**
     * Deletes given module from moduleList.
     *
     * @param moduleList List from which the module is to be deleted from.
     */
    public void deleteModule(ModuleList moduleList) throws ModHappyException {
        result = String.format(DELETE_MODULE_SUCCESS, moduleList.removeModule(moduleCode));
    }

    /**
     * Deletes a given task.
     *
     * @param moduleList List of modules in which to search for the task.
     */
    public void deleteTaskFromModule(ModuleList moduleList) throws ModHappyException {
        Module targetModule;
        if (Objects.isNull(taskModule)) {
            targetModule = moduleList.getGeneralTasks();
        } else {
            targetModule = moduleList.getModule(taskModule);
            if (Objects.isNull(targetModule)) {
                throw new NoSuchModuleException();
            }
        }
        TaskList taskList = targetModule.getTaskList();
        int taskIndex = taskNumber - 1;
        result = String.format(DELETE_TASK_SUCCESS, taskList.removeTask(taskIndex));
    }
}
