package seedu.duke;

import java.io.File;
import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.ModHappyParser;
import seedu.duke.storage.ConfigurationStorage;
import seedu.duke.storage.ModuleListStorage;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskListStorage;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.ui.TextUi;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;


public class Main {
    private final String modulePath = StringConstants.MODULE_PATH;
    private final String taskPath = StringConstants.TASK_PATH;
    private final String configurationPath = StringConstants.CONFIGURATION_PATH;
    private final String moduleLoadErrorMessage = StringConstants.MODULE_DATA_LOAD_FAILED;
    private final String moduleLoadSuccessMessage = StringConstants.MODULE_DATA_LOAD_SUCCESS;
    private final String taskLoadErrorMessage = StringConstants.TASK_DATA_LOAD_FAILED;
    private final String taskLoadSuccessMessage = StringConstants.TASK_DATA_LOAD_SUCCESS;
    private final String configurationLoadSuccessMessage = StringConstants.CONFIGURATION_DATA_LOAD_SUCCESS;
    private final String configurationLoadErrorMessage = StringConstants.CONFIGURATION_DATA_LOAD_FAILED;


    private TextUi ui;
    private ModHappyParser modHappyParser;
    private ModuleList moduleList;
    private seedu.duke.util.Configuration configuration;
    private Storage modHappyStorage;

    /**
     * Main entry-point for the application.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the program until termination.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     **/
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects.
     * @param args arguments supplied by the user at program launch.
     */
    private void start(String[] args) {
        try {
            ui = new TextUi();
            modHappyParser = new ModHappyParser();
            moduleList = new ModuleList();
            loadDataFromFile();
            ui.showHelloMessage();
        } catch (Exception e) {
            ui.showInitFailedMessage();
        }
    }

    /**
     * Initialises the program data by attempting to read from the data files, if possible.
     * If a data file is not found or contains invalid data, the file will be treated as blank instead.
     */
    private void loadDataFromFile() {
        File moduleDataFile = new File(modulePath);
        if (moduleDataFile.exists()) {
            modHappyStorage = new ModuleListStorage();
            try {
                moduleList.setModuleList((ArrayList<Module>) modHappyStorage.loadData(modulePath));
                ui.showUnformattedMessage(moduleLoadSuccessMessage);
            } catch (ModHappyException e) {
                ui.showUnformattedMessage(e);
                ui.showUnformattedMessage(moduleLoadErrorMessage);
            }
        }
        File taskDataFile = new File(taskPath);
        if (taskDataFile.exists()) {
            modHappyStorage = new TaskListStorage();
            try {
                moduleList.initialiseGeneralTasksFromTaskList((ArrayList<Task>) modHappyStorage.loadData(taskPath));
                ui.showUnformattedMessage(taskLoadSuccessMessage);
            } catch (ModHappyException e) {
                ui.showUnformattedMessage(e);
                ui.showUnformattedMessage(taskLoadErrorMessage);
            }
        }
        File configurationDataFile = new File(configurationPath);
        if (configurationDataFile.exists()) {
            modHappyStorage = new ConfigurationStorage();
            try {
                configuration = (Configuration) modHappyStorage.loadData(configurationPath);
                ui.showUnformattedMessage(configurationLoadSuccessMessage);
            } catch (ModHappyException e) {
                ui.showUnformattedMessage(e);
                ui.showUnformattedMessage(configurationLoadErrorMessage);
            }
        } else {
            configuration = new Configuration();
        }
    }

    /**
     * Reads the user command and executes it, until the user calls the exit command.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     **/
    private void runCommandLoopUntilExitCommand() {
        Command command = null;
        String userCommandText;
        do {
            try {
                userCommandText = ui.getUserCommand();
                command = modHappyParser.parseCommand(userCommandText);
                CommandResult result = command.execute(moduleList, configuration);
                ui.showMessage(result.toString());
            } catch (Exception e) {
                ui.showMessage(e);
            }
        } while (command == null || !ExitCommand.isExit);
    }

    /**
     * Prints the goodbye message and exits.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     * */
    private void exit() {
        ui.showGoodByeMessage();
        System.exit(0);
    }


}
