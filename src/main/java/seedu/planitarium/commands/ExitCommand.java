//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;
import seedu.planitarium.global.UI;
import seedu.planitarium.family.Family;
import seedu.planitarium.storage.Storage;

import java.util.logging.Level;

/**
 * Executes exit command and exit the program.
 */
public class ExitCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String LOG_EXIT_INFO = "Program is going to exit";
    protected static final String LOG_EXECUTE_INFO = "Program exits with status 0";

    public ExitCommand(String userInput, Family family) {
        super(userInput, family);
        this.type = Constants.EXITCMDTYPE;
        logger.log(Level.INFO, LOG_EXIT_INFO);
    }

    @Override
    public void execute() {
        Storage.saveData(family);
        UI.exit();
        System.out.println(UI.HORI_LINE);
        logger.log(Level.INFO, LOG_EXECUTE_INFO);
        System.exit(0);
    }

    @Override
    public String getType() {
        return type;
    }
}
