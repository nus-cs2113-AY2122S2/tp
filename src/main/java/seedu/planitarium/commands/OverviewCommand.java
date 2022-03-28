package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.person.Family;

import java.util.logging.Level;

public class OverviewCommand extends Command{
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String LOG_CMD_INFO = "Overview command is going to be executed";
    private static final String LOG_EXECUTE_INFO = "Overview command is executed";
    public OverviewCommand(String userInput, Family family) {
        super(userInput, family);
        logger.log(Level.INFO, LOG_CMD_INFO);
    }

    public void execute() {
        family.overview();
        logger.log(Level.INFO, LOG_EXECUTE_INFO);
    }
}
