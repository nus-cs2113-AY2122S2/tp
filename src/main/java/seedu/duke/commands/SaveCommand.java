package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.data.Module;
import seedu.duke.data.Task;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.storage.ModHappyStorageManager;
import seedu.duke.data.ModuleList;
import seedu.duke.data.TaskList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

//@@author Ch40gRv1-Mu
public class SaveCommand extends Command {


    /**
     * Saves the existing list of general tasks and modules.
     * @param moduleList List to be saved and loaded.
     */

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) {
        // Even if there is an error writing to one file, we should still try to write to the others.
        String writeStatus = "";
        try {
            // Master Task List
            TaskList taskList = moduleList.getGeneralTasks().getTaskList();
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            ModHappyStorageManager.saveTaskList(taskArrayList);
            writeStatus += StringConstants.TASK_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += e + StringConstants.LS;
            writeStatus += StringConstants.TASK_DATA_SAVE_FAILED + StringConstants.LS;
        }
        try {
            ArrayList<Module> moduleArrayList = moduleList.getModuleList();
            ModHappyStorageManager.saveModuleList(moduleArrayList);
            writeStatus += StringConstants.MODULE_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += e + StringConstants.LS;
            writeStatus += StringConstants.MODULE_DATA_SAVE_FAILED + StringConstants.LS;
        }
        try {
            ModHappyStorageManager.saveConfiguration(configuration);
            writeStatus += StringConstants.CONFIGURATION_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += e + StringConstants.LS;
            writeStatus += StringConstants.CONFIGURATION_DATA_SAVE_FAILED + StringConstants.LS;
        }
        return new CommandResult(writeStatus);
    }
}
