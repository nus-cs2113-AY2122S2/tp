package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.data.*;
import seedu.duke.data.Module;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;
import seedu.duke.util.NumberConstants;

//@@author heekit73098
public class EditCommand extends Command {

    private static final String EDIT_MODULE_SUCCESS = StringConstants.EDIT_MODULE_SUCCESS;
    private static final String EDIT_TASK_SUCCESS = StringConstants.EDIT_TASK_SUCCESS;
    private static final String EDIT_TASK_WITH_MODULE_SUCCESS = StringConstants.EDIT_TASK_WITH_MODULE_SUCCESS;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION_STR;
    private static final String ESTIMATED_WORKING_TIME = StringConstants.TASK_ESTIMATED_WORKING_TIME_STR;
    private static final String TASK_NAME = StringConstants.TASK_NAME_STR;

    private String moduleCode;
    private String taskModule;
    private int taskIndex = NumberConstants.INVALID_TASK_INDEX;
    private String taskParameter;
    private String result = "";
    private boolean isGeneralTask = false;
    private final String changedParameter;

    public int getTaskIndex() {
        return taskIndex;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTaskModule() {
        return taskModule;
    }

    public EditCommand(String moduleCode, String description) {
        this.moduleCode = moduleCode;
        this.changedParameter = description;
    }

    /**
     * Constructs a new EditCommand object to edit a task.
     * @param taskModule The task that the module belongs to, null if it falls under General Tasks
     * @param taskIndex The zero-based index of the task
     * @param taskParameter The parameter to be changed
     * @param taskParameterType Enumeration of TASK_NAME_ONLY, DESCRIPTION_ONLY and WORKING_TIME_ONLY
     */
    public EditCommand(String taskModule, int taskIndex,
                       String taskParameter, TaskParameters taskParameterType) {
        this.taskModule = taskModule;
        this.taskIndex = taskIndex;
        if (taskParameter.isBlank()) {
            this.changedParameter = null;
        } else {
            this.changedParameter = taskParameter;
        }
        switch (taskParameterType) {
        case DESCRIPTION_ONLY:
            this.taskParameter = TASK_DESCRIPTION;
            break;
        case WORKING_TIME_ONLY:
            this.taskParameter = ESTIMATED_WORKING_TIME;
            break;
        default:
            this.taskParameter = TASK_NAME;
        }
    }

    /**
     * Gets the module that the target task belongs to, or General Tasks if it does not belong to any module.
     * @param moduleList List of modules from which the target task belongs to, or General Tasks if it does not
     *                   belong to any module.
     * @return The module the target task belongs to, or General Tasks if it does not belong to any module.
     * @throws NoSuchModuleException If the target module does not exist
     */
    private Module getTargetModule(ModuleList moduleList) throws NoSuchModuleException {
        Module targetModule;
        if (Objects.isNull(taskModule)) {
            isGeneralTask = true;
            targetModule = moduleList.getGeneralTasks();
        } else {
            targetModule = moduleList.getModule(taskModule);
        }
        return targetModule;
    }

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        if (taskIndex < 0) {
            editModuleDescription(moduleList);
        } else {
            Module targetModule = getTargetModule(moduleList);
            editTaskFromModule(targetModule);
        }
        return new CommandResult(result);
    }

    /**
     * Changes module description of the target module.
     *
     * @param moduleList List from which the module's description is to be edited.
     * @throws NoSuchModuleException If the module to be edited does not exist
     */
    public void editModuleDescription(ModuleList moduleList) throws NoSuchModuleException {
        Module targetModule = moduleList.getModule(moduleCode);
        targetModule.setModuleDescription(changedParameter);
        result = String.format(EDIT_MODULE_SUCCESS, targetModule.getModuleCode());
    }

    /**
     * Changes task parameter (either task description or estimated working time) of the target task.
     *
     * @param targetModule The module (or General Tasks) the target task belongs to.
     * @throws ModHappyException If the task to be edited does not exist, or if the working time entered cannot be
     *                           parsed correctly
     */
    private void editTaskFromModule(Module targetModule) throws ModHappyException {
        TaskList taskList = targetModule.getTaskList();
        Task targetTask = taskList.getTask(taskIndex);
        String targetTaskName = targetTask.getTaskName();
        switch (taskParameter) {
        case TASK_DESCRIPTION:
            targetTask.setTaskDescription(changedParameter);
            break;
        case ESTIMATED_WORKING_TIME:
            targetTask.setWorkingTime(changedParameter);
            break;
        default:
            targetTask.setTaskName(changedParameter);
        }
        if (isGeneralTask) {
            result = String.format(EDIT_TASK_SUCCESS, taskParameter, targetTaskName);
        } else {
            result = String.format(EDIT_TASK_WITH_MODULE_SUCCESS, taskParameter,
                    targetTaskName, targetModule.getModuleCode());
        }
    }

}
