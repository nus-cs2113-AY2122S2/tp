package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.data.TaskList;
import seedu.duke.ui.TextUi;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;
import seedu.duke.util.NumberConstants;

//@@author ngys117
public class DeleteCommand extends Command {

    private static final String DELETE_MESSAGE = StringConstants.DELETE_MESSAGE;
    private static final String DELETE_ABORT = StringConstants.DELETE_ABORT;
    private static final String DELETE_CONFIRMATION = StringConstants.DELETE_CONFIRMATION;
    private static final String DELETE_CONFIRMATION_INPUT_ERROR = StringConstants.DELETE_CONFIRMATION_INPUT_ERROR;

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
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        if (taskIndex < 0) { //If invalid task number or no task number was provided
            deleteModule(moduleList);
            return new CommandResult(result);
        }
        Module targetModule;
        if (Objects.isNull(taskModule)) {
            targetModule = moduleList.getGeneralTasks();
        } else {
            targetModule = moduleList.getModule(taskModule);
        }
        if (Objects.isNull(targetModule)) {
            throw new NoSuchModuleException();
        }
        deleteTaskFromModule(targetModule);
        return new CommandResult(result);
    }

    /**
     * Deletes given module from moduleList.
     *
     * @param moduleList List from which the module is to be deleted from.
     */
    public void deleteModule(ModuleList moduleList) throws NoSuchModuleException {
        Module targetModule = moduleList.getModule(moduleCode);
        if (targetModule.getTaskList().getSize() > 0) {
            Boolean hasDeleteConfirmation = getUserConfirmation(targetModule);
            if (!hasDeleteConfirmation) {
                result = DELETE_ABORT;
                return;
            }
        }
        Module removedModule = moduleList.removeModule(moduleCode);
        result = String.format(DELETE_MESSAGE, removedModule);
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

    /**
     * Gets confirmation from user to delete given module.
     *
     * @param module Module to be deleted.
     * @return Returns true if user input is "yes", false if "no".
     */
    public Boolean getUserConfirmation(Module module) {
        String prompt = String.format(DELETE_CONFIRMATION, module);
        TextUi.showMessage(prompt);
        String userConfirmation;
        while (true) {
            userConfirmation = TextUi.getUserCommand();
            if (userConfirmation.equals("yes")) {
                return true;
            } else if (userConfirmation.equals("no")) {
                return false;
            } else {
                TextUi.showMessage(DELETE_CONFIRMATION_INPUT_ERROR);
            }
        }
    }
}
