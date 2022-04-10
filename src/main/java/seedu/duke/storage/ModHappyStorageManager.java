package seedu.duke.storage;

import java.io.File;
import java.util.ArrayList;

import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.data.Task;
import seedu.duke.exceptions.DuplicateModuleException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.ui.TextUi;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

import static seedu.duke.util.NumberConstants.MAXIMUM_MODULAR_CREDITS;
import static seedu.duke.util.NumberConstants.MINIMUM_MODULAR_CREDITS;

public class ModHappyStorageManager {



    private static Storage modHappyStorage;

    @SuppressWarnings("unchecked")
    public static void saveTaskList(ArrayList<Task> taskArrayList) throws ModHappyException {
        modHappyStorage = new TaskListStorage();
        modHappyStorage.writeData(taskArrayList, StringConstants.TASK_PATH);
    }

    @SuppressWarnings("unchecked")
    public static void saveModuleList(ArrayList<Module> moduleArrayList) throws ModHappyException {
        modHappyStorage = new ModuleListStorage();
        modHappyStorage.writeData(moduleArrayList, StringConstants.MODULE_PATH);
    }

    @SuppressWarnings("unchecked")
    public static void saveConfiguration(Configuration configuration) throws ModHappyException {
        modHappyStorage = new ConfigurationStorage();
        modHappyStorage.writeData(configuration, StringConstants.CONFIGURATION_PATH);
    }

    public static Configuration loadConfiguration(String configurationPath) {
        File configurationDataFile = new File(configurationPath);
        if (configurationDataFile.exists()) {
            modHappyStorage = new ConfigurationStorage();
            try {
                Configuration configuration = (Configuration) modHappyStorage.loadData(configurationPath);
                TextUi.showUnformattedMessage(StringConstants.CONFIGURATION_DATA_LOAD_SUCCESS);
                return configuration;
            } catch (ModHappyException e) {
                Configuration configuration = new Configuration();
                TextUi.showUnformattedMessage(e);
                TextUi.showUnformattedMessage(StringConstants.CONFIGURATION_DATA_LOAD_FAILED);
                return configuration;
            }
        } else {
            Configuration configuration = new Configuration();
            TextUi.showUnformattedMessage(StringConstants.NO_CONFIG_DATA_FILE);
            return configuration;
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadTaskList(ModuleList moduleList, String taskPath) {
        File taskDataFile = new File(taskPath);
        if (taskDataFile.exists()) {
            modHappyStorage = new TaskListStorage();
            try {
                moduleList.initialiseGeneralTasksFromTaskList((ArrayList<Task>) modHappyStorage.loadData(taskPath));
                TextUi.showUnformattedMessage(StringConstants.TASK_DATA_LOAD_SUCCESS);
            } catch (ModHappyException e) {
                TextUi.showUnformattedMessage(e);
                TextUi.showUnformattedMessage(StringConstants.TASK_DATA_LOAD_FAILED);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadModuleList(ModuleList moduleList, String modulePath) {
        File moduleDataFile = new File(modulePath);
        if (moduleDataFile.exists()) {
            modHappyStorage = new ModuleListStorage();
            try {
                ArrayList<String> moduleCodes = new ArrayList<>();
                ArrayList<Module> arrayListModule;
                arrayListModule = (ArrayList<Module>) modHappyStorage.loadData(modulePath);
                for (Module m : arrayListModule) {
                    if (moduleCodes.contains(m.getModuleCode())) {
                        throw new DuplicateModuleException(m.getModuleCode());
                    }
                    if (m.getModularCredit() > MAXIMUM_MODULAR_CREDITS
                            || m.getModularCredit() < MINIMUM_MODULAR_CREDITS) {
                        throw new InvalidModuleException(m.getModuleCode(), m.getModularCredit());
                    }
                    moduleCodes.add(m.getModuleCode());
                }
                moduleList.setModuleList(arrayListModule);
                TextUi.showUnformattedMessage(StringConstants.MODULE_DATA_LOAD_SUCCESS);
            } catch (ModHappyException e) {
                TextUi.showUnformattedMessage(e);
                TextUi.showUnformattedMessage(StringConstants.MODULE_DATA_LOAD_FAILED);
            }
        }
    }


}
