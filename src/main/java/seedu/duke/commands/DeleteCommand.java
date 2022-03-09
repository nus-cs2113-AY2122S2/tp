package seedu.duke.commands;

import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.TaskList;

public class DeleteCommand extends Command {

    private static final String DELETE_MODULE_SUCCESS = "%s has been deleted.";
    private static final String DELETE_TASK_SUCCESS = "%s has been deleted.";

    private String moduleCode = "";
    private int taskNumber = -1;
    private String result = "";

    public String getModuleCode() {
        return moduleCode;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public DeleteCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public DeleteCommand(String moduleCode, int taskNumber) {
        this.moduleCode = moduleCode;
        this.taskNumber = taskNumber;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws NoSuchTaskException, NoSuchModuleException {
        if (taskNumber < 0) {
            deleteModule(moduleList);
        } else if (moduleCode.isBlank()) {
            deleteTask(moduleList);
        } else {
            deleteTaskFromModule();
        }
        return new CommandResult(result);
    }

    /**
     * Deletes given module from moduleList.
     *
     * @param moduleList List from which the module is to be deleted from.
     */
    public void deleteModule(ModuleList moduleList) throws NoSuchModuleException {
        result = String.format(DELETE_MODULE_SUCCESS, moduleList.removeModule(moduleCode));
    }

    /**
     * Deletes given task from generalTasks in moduleList.
     *
     * @param moduleList List from which the task is to be deleted from.
     */
    private void deleteTask(ModuleList moduleList) throws NoSuchTaskException {
        Module targetModule = moduleList.getGeneralTasks();
        TaskList taskList = targetModule.getTaskList();
        int taskIndex = taskNumber - 1;
        result = String.format(DELETE_TASK_SUCCESS, taskList.removeTask(taskIndex));
    }

    // TODO: Implement this after module and task has been linked
    public void deleteTaskFromModule() {

    }
}
