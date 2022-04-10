package seedu.duke.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.data.Task;
import seedu.duke.exceptions.DuplicateModuleException;
import seedu.duke.exceptions.InvalidConfigurationException;
import seedu.duke.exceptions.InvalidConfigurationValueException;
import seedu.duke.exceptions.InvalidModuleCreditsException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidTaskException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.ui.TextUi;
import seedu.duke.util.Configuration;
import seedu.duke.util.Grades;
import seedu.duke.util.StringConstants;

import static seedu.duke.util.NumberConstants.MAXIMUM_MODULAR_CREDITS;
import static seedu.duke.util.NumberConstants.MINIMUM_MODULAR_CREDITS;

public class ModHappyStorageManager {


    private static Storage modHappyStorage;

    //@@author Ch40gRv1-Mu
    /**
     * Saves task list to storage.
     * @param taskArrayList ArrayList of tasks to be saved.
     * @throws ModHappyException Failed to write the task to local storage.
     */
    @SuppressWarnings("unchecked")
    public static void saveTaskList(ArrayList<Task> taskArrayList) throws ModHappyException {
        modHappyStorage = new TaskListStorage();
        modHappyStorage.writeData(taskArrayList, StringConstants.TASK_PATH);
    }

    //@@author Ch40gRv1-Mu
    /**
     * Saves module list to storage.
     * @param moduleArrayList ArrayList of modules to be saved.
     * @throws ModHappyException Failed to write the modules to local storage.
     */
    @SuppressWarnings("unchecked")
    public static void saveModuleList(ArrayList<Module> moduleArrayList) throws ModHappyException {
        modHappyStorage = new ModuleListStorage();
        modHappyStorage.writeData(moduleArrayList, StringConstants.MODULE_PATH);
    }

    //@@author Ch40gRv1-Mu
    /**
     * Saves Configuration to storage.
     * @param configuration configuration to be saved.
     * @throws ModHappyException Failed to write the configuration to local storage.
     */
    @SuppressWarnings("unchecked")
    public static void saveConfiguration(Configuration configuration) throws ModHappyException {
        modHappyStorage = new ConfigurationStorage();
        modHappyStorage.writeData(configuration, StringConstants.CONFIGURATION_PATH);
    }

    //@@author Ch40gRv1-Mu
    /**
     * Loads Configuration from storage.
     * @param configurationPath The local path that a configuration is saved.
     * @return Loaded configuration or a default configuration objects
     */
    public static Configuration loadConfiguration(String configurationPath) {
        File configurationDataFile = new File(configurationPath);
        if (!configurationDataFile.exists()) {
            modHappyStorage = new ConfigurationStorage();
            try {
                Configuration configuration = (Configuration) modHappyStorage.loadData(configurationPath);
                checkConfiguration(configuration);
                TextUi.showUnformattedMessage(StringConstants.CONFIGURATION_DATA_LOAD_SUCCESS);
                return configuration;
            } catch (ModHappyException e) {
                TextUi.showUnformattedMessage(e);
                TextUi.showUnformattedMessage(StringConstants.CONFIGURATION_DATA_LOAD_FAILED);
                return new Configuration();
            }
        } else {
            TextUi.showUnformattedMessage(StringConstants.NO_CONFIG_DATA_FILE);
            return new Configuration();
        }
    }

    //@@author chooyikai
    public static void checkConfiguration(Configuration configuration) throws ModHappyException {
        HashMap<Configuration.ConfigurationGroup,String> configMap = configuration.configurationGroupHashMap;
        for (Configuration.ConfigurationGroup key : configMap.keySet()) {
            if (key == null) {
                throw new InvalidConfigurationException();
            }
            if (!Configuration.LEGAL_VALUES.get(key).contains(configMap.get(key))) {
                throw new InvalidConfigurationValueException(key, configMap.get(key));
            }
        }
    }

    public static void checkTaskList(ArrayList<Task> list) throws ModHappyException {
        for (Task t : list) {
            if (Objects.isNull(t.getTaskName())) {
                throw new InvalidTaskException();
            }
            if (Objects.isNull(t.getWorkingTime()) || t.getWorkingTime().getTaskDuration().isNegative()
                    || t.getWorkingTime().getTaskDuration().isZero()) {
                t.setWorkingTime(null);
            }
        }
    }

    public static void checkModuleList(ArrayList<Module> list) throws ModHappyException {
        ArrayList<String> moduleCodes = new ArrayList<>();
        for (Module m : list) {
            if (Objects.isNull(m.getModuleCode())) {
                throw new InvalidModuleException();
            }
            if (Objects.isNull(m.getModuleGrade())) {
                m.setModuleGrade(Grades.NOT_ENTERED.toString());
            }
            if (moduleCodes.contains(m.getModuleCode())) {
                throw new DuplicateModuleException(m.getModuleCode());
            }
            if (m.getModularCredit() > MAXIMUM_MODULAR_CREDITS
                    || m.getModularCredit() < MINIMUM_MODULAR_CREDITS) {
                throw new InvalidModuleCreditsException(m.getModuleCode(), m.getModularCredit());
            }
            checkTaskList(m.getTaskList().getTaskList());
            moduleCodes.add(m.getModuleCode());
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadTaskList(ModuleList moduleList, String taskPath) {
        File taskDataFile = new File(taskPath);
        if (!taskDataFile.exists()) {
            return;
        }
        modHappyStorage = new TaskListStorage();
        try {
            ArrayList<Task> list = (ArrayList<Task>) modHappyStorage.loadData(taskPath);
            checkTaskList(list);
            moduleList.initialiseGeneralTasksFromTaskList(list);
            TextUi.showUnformattedMessage(StringConstants.TASK_DATA_LOAD_SUCCESS);
        } catch (ModHappyException e) {
            TextUi.showUnformattedMessage(e);
            TextUi.showUnformattedMessage(StringConstants.TASK_DATA_LOAD_FAILED);
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadModuleList(ModuleList moduleList, String modulePath) {
        File moduleDataFile = new File(modulePath);
        if (!moduleDataFile.exists()) {
            return;
        }
        modHappyStorage = new ModuleListStorage();
        try {
            ArrayList<Module> arrayListModule = (ArrayList<Module>) modHappyStorage.loadData(modulePath);
            checkModuleList(arrayListModule);
            moduleList.setModuleList(arrayListModule);
            TextUi.showUnformattedMessage(StringConstants.MODULE_DATA_LOAD_SUCCESS);
        } catch (ModHappyException e) {
            TextUi.showUnformattedMessage(e);
            TextUi.showUnformattedMessage(StringConstants.MODULE_DATA_LOAD_FAILED);
        }
    }
}
