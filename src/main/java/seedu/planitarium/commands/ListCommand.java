//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;

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

    protected int group;

    public ListCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = Constants.LISTCMDTYPE;
        group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        logger.log(Level.INFO, String.format(LOG_LIST_INFO, group));
    }

    public void execute() {
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        family.list(group);
        logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, group));
    }

    @Override
    public String getType() {
        return type;
    }
}
