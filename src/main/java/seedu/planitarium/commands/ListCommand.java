package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

import java.util.logging.Level;

/**
 * Executes list command and list all the information stored in the personlist.
 */
public class ListCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String LOG_LIST_INFO = "List with group '%d' is going to be executed";
    protected static final String LOG_EXECUTE_INFO = "List with group '%d' is executed";

    protected boolean LIST_WITH_GROUP = true;
    protected int group;

    public ListCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "ListCMD";
        try {
            group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        } catch (PlanITariumException e) {
            group = -1; //list without group
            LIST_WITH_GROUP = false;
        } finally {
            logger.log(Level.INFO, String.format(LOG_LIST_INFO, group));
        }
    }

    @Override
    public void execute() {
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        if(LIST_WITH_GROUP) {
            family.list(group);
            logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, group));
        } else {
            family.overview();
            logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, group));
        }
    }
}
