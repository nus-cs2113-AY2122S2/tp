package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.storage.ConfigurationStorage;
import seedu.duke.storage.ModuleListStorage;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskListStorage;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;


public class SaveCommand extends Command {

    private Storage storage;

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        // Even if there is an error writing to one file, we should still try to write to the others.
        String writeStatus = "";
        try {
            // Master Task List
            storage = new TaskListStorage();
            TaskList taskList = moduleList.getGeneralTasks().getTaskList();
            ArrayList<seedu.duke.tasks.Task> taskArrayList = taskList.getTaskList();
            storage.writeData(taskArrayList, StringConstants.TASK_PATH);
            writeStatus += StringConstants.TASK_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += e + StringConstants.LS;
            writeStatus += StringConstants.TASK_DATA_SAVE_FAILED + StringConstants.LS;
        }
        try {
            storage = new ModuleListStorage();
            ArrayList<seedu.duke.tasks.Module> moduleArrayList = moduleList.getModuleList();
            storage.writeData(moduleArrayList, StringConstants.MODULE_PATH);
            writeStatus += StringConstants.MODULE_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += e + StringConstants.LS;
            writeStatus += StringConstants.MODULE_DATA_SAVE_FAILED + StringConstants.LS;
        }
        try {
            storage = new ConfigurationStorage();
            storage.writeData(configuration, StringConstants.CONFIGURATION_PATH);
            writeStatus += StringConstants.CONFIGURATION_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += e + StringConstants.LS;
            writeStatus += StringConstants.CONFIGURATION_DATA_SAVE_FAILED + StringConstants.LS;
        }
        return new CommandResult(writeStatus);
    }
}
