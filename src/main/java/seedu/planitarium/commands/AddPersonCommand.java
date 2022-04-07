//@@author hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;

import java.util.logging.Level;

/**
 * Executes the add command and add a person to the list.
 */
public class AddPersonCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String LOG_ADDPERSONCMD_INFO = "'%s' is going to be added to group '%d'";
    protected static final String LOG_EXECUTE_INFO = "'%s' is added to group '%d'";

    protected String name;
    protected Integer group;
    protected Boolean isSilent;

    public AddPersonCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = Constants.ADDPERSONCMDTYPE;
        this.name = Parser.parseName(userInput);
        this.group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        this.isSilent = Constants.FOR_USER;
        logger.log(Level.INFO, String.format(LOG_ADDPERSONCMD_INFO, name, group));
    }

    public void execute() throws PlanITariumException {
        assert (name != null) : Constants.NAME_NOT_NULL;
        family.addPerson(group, name, isSilent);
        logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, name, group));
    }

    @Override
    public String getType() {
        return type;
    }


}
