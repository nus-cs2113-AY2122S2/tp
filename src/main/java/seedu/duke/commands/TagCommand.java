package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;

import java.util.Objects;

public class TagCommand extends Command {

    private static final String ADD_COMMAND = StringConstants.ADD_COMMAND_WORD;
    private static final String ADD_TAG_MESSAGE = StringConstants.ADD_TAG_MESSAGE;
    private static final String DEL_TAG_MESSAGE = StringConstants.DEL_TAG_MESSAGE;

    private final String tagOperation;
    private final int taskIndex;
    private final String taskModule;
    private final String tagDescription;
    private String result;

    public TagCommand(String tagOperation, int taskIndex, String taskModule, String tagDescription) {
        this.tagOperation = tagOperation;
        this.taskIndex = taskIndex;
        this.taskModule = taskModule;
        this.tagDescription = tagDescription;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        Module targetModule;
        if (Objects.isNull(taskModule)) {
            targetModule = moduleList.getGeneralTasks();
            if (tagOperation.equals("add")) {
                addTag(targetModule);
            } else {
                removeTag(targetModule);
            }
        } else {
            targetModule = moduleList.getModule(taskModule);
            if (Objects.isNull(targetModule)) {
                throw new NoSuchModuleException();
            }
            if (tagOperation.equals(ADD_COMMAND)) {
                addTag(targetModule);
            } else {
                removeTag(targetModule);
            }
        }
        return new CommandResult(result);
    }

    private void addTag(Module targetModule) throws ModHappyException {
        TaskList taskList = targetModule.getTaskList();
        result  = String.format(ADD_TAG_MESSAGE, taskList.addTag(tagDescription, taskIndex), tagDescription);
    }

    private void removeTag(Module targetModule) throws ModHappyException {
        TaskList taskList = targetModule.getTaskList();
        Task task = taskList.deleteTag(tagDescription, taskIndex);
        result = String.format(DEL_TAG_MESSAGE, task, tagDescription);
    }
}
