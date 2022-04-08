package seedu.duke;


import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.parsers.ModHappyParser;
import seedu.duke.storage.ModHappyStorageManager;
import seedu.duke.storage.Storage;
import seedu.duke.data.ModuleList;
import seedu.duke.ui.TextUi;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;


public class Main {
    private final String modulePath = StringConstants.MODULE_PATH;
    private final String taskPath = StringConstants.TASK_PATH;
    private final String configurationPath = StringConstants.CONFIGURATION_PATH;


    private ModHappyParser modHappyParser;
    private ModuleList moduleList;
    private Configuration configuration;
    private Storage modHappyStorage;

    /**
     * Main entry-point for the application.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Runs the program until termination.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     **/
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects.
     */
    private void start() {
        try {
            modHappyParser = new ModHappyParser();
            moduleList = new ModuleList();
            loadDataFromFile();
            TextUi.showHelloMessage();
        } catch (Exception e) {
            TextUi.showInitFailedMessage();
        }
    }

    /**
     * Initialises the program data by attempting to read from the data files, if possible.
     * If a data file is not found or contains invalid data, the file will be treated as blank instead.
     */
    private void loadDataFromFile() {
        ModHappyStorageManager.loadTaskList(moduleList,taskPath);
        ModHappyStorageManager.loadModuleList(moduleList, modulePath);
        configuration = ModHappyStorageManager.loadConfiguration(configurationPath);
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
                userCommandText = TextUi.getUserCommand();
                command = modHappyParser.parseCommand(userCommandText);
                CommandResult result = command.execute(moduleList, configuration);
                TextUi.showMessage(result.toString());
            } catch (Exception e) {
                TextUi.showMessage(e);
            }
        } while (command == null || !ExitCommand.isExit);
    }

    /**
     * Prints the goodbye message and exits.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     * */
    private void exit() {
        TextUi.showGoodByeMessage();
        System.exit(0);
    }


}
