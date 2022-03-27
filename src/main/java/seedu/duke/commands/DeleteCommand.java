package seedu.duke.commands;

import java.util.Objects;
import java.util.Scanner;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.data.TaskList;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;
import seedu.duke.util.NumberConstants;

public class DeleteCommand extends Command {

    private static final String DELETE_MESSAGE = StringConstants.DELETE_MESSAGE;
    private static final String DELETE_ABORT = StringConstants.DELETE_ABORT;
    private static final String DELETE_CONFIRMATION = StringConstants.DELETE_CONFIRMATION;


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
    public void deleteModule(ModuleList moduleList) throws NoSuchModuleException, ParseException {
        Module targetModule = moduleList.getModule(moduleCode);
        if (targetModule == null) {
            throw new NoSuchModuleException();
        }
        if (targetModule.getTaskList().size() > 0) {
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
     * @throws ParseException Throws an exception if user input is not "yes" or "no".
     */
    public Boolean getUserConfirmation(Module module) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String prompt = String.format(DELETE_CONFIRMATION, module);
        System.out.println(prompt);
        String userConfirmation = scanner.nextLine().toLowerCase();
        if (userConfirmation.equals("yes")) {
            return true;
        }
        if (userConfirmation.equals("no")) {
            return false;
        }
        throw new ParseException();
    }
}
