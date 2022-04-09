package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.data.Task;
import seedu.duke.data.TaskList;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

//@@author chooyikai
public class AddCommand extends Command {
    public enum AddObjectType {
        TASK, MODULE
    }

    private static final String ADD_TASK_MESSAGE = StringConstants.ADD_TASK_MESSAGE_TOP + LS + "%s" + LS
            + LS;
    private static final String ADD_MODULE_MESSAGE = StringConstants.ADD_MODULE_MESSAGE_TOP + LS + "%s";

    private final AddObjectType typeToAdd;
    private Task newTask = null;
    private String targetModuleName = null;
    private Module newModule = null;

    /**
     * Constructs a new AddCommand object to add a new task.
     * @param type Represents the type of object to be added
     * @param taskName The name of the task to be added
     * @param taskDescription The description of the task to be added, null if the description is empty
     * @param estimatedWorkingTime The estimated working time of the task, null if it is empty
     * @param taskModule The module the task falls under, null if the task falls under General Tasks
     * @throws ModHappyException If the estimated working time of the task cannot be parsed correctly
     */
    public AddCommand(AddObjectType type, String taskName, String taskDescription, String estimatedWorkingTime,
                      String taskModule) throws ModHappyException {
        assert type == AddObjectType.TASK;
        typeToAdd = type;
        newTask = new Task(taskName, taskDescription, estimatedWorkingTime);
        targetModuleName = taskModule;
    }

    /**
     * Constructs a new AddCommand object to add a new module
     * @param type Represents the type of object to be added
     * @param moduleCode The code of the module to be added
     * @param moduleDescription The description of the module to be added, null if it is empty
     * @param modularCredit The number of modular credits the module has
     */
    public AddCommand(AddObjectType type, String moduleCode, String moduleDescription, int modularCredit) {
        assert type == AddObjectType.MODULE;
        typeToAdd = type;
        newModule = new Module(moduleCode, moduleDescription, modularCredit);
    }

    public Task getNewTask() {
        return newTask;
    }

    public String getTargetModuleName() {
        return targetModuleName;
    }

    public Module getNewModule() {
        return newModule;
    }

    /**
     * Adds the specified task or module.
     * @param moduleList The list of modules
     * @param configuration The configuration settings of the application
     * @return A new {@code CommandResult} with the result string
     * @throws NoSuchModuleException If the module to be added does not exist
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws NoSuchModuleException {
        String res;
        if (typeToAdd == AddObjectType.TASK) {
            Module targetModule = moduleList.getGeneralTasks();
            if (!Objects.isNull(targetModuleName)) {
                targetModule = moduleList.getModule(targetModuleName);
            }
            TaskList taskList = targetModule.getTaskList();
            res = String.format(ADD_TASK_MESSAGE, targetModule, taskList.addTask(newTask));
        } else {
            assert typeToAdd == AddObjectType.MODULE;
            if (!moduleList.isModuleExists(newModule.getModuleCode())) {
                res = String.format(ADD_MODULE_MESSAGE, moduleList.addModule(newModule));
            } else {
                res = StringConstants.MODULE_ALREADY_EXISTS;
            }
        }
        return new CommandResult(res);
    }
}
