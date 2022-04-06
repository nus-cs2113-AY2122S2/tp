//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;

import java.util.logging.Level;

/**
 * Executes the delete command and delete a person from the list.
 */
public class DeletePersonCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String LOG_DELETEPERSONCMD_INFO =
            "A person with uid '%d' is going to be deleted from group '%d'";
    protected static final String LOG_EXECUTE_INFO = "A person with '%d' is deleted from group '%d'";

    protected Integer uid;
    protected Integer group;

    public DeletePersonCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = Constants.DELETEPERSONCMDTYPE;
        this.group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        this.uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
        logger.log(Level.INFO, String.format(LOG_DELETEPERSONCMD_INFO, group, uid));
    }

    public void execute() throws PlanITariumException {
        assert (uid > 0) : Constants.USER_INDEX_NOT_VALID;
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        family.deletePerson(group, uid);
        logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, group, uid));
    }

    @Override
    public String getType() {
        return type;
    }

}
