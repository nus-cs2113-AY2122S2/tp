package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

/**
 * Executes list command and list all the information stored in the personlist.
 */
public class ListCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

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
        }
    }

    @Override
    public void execute() {
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        if(LIST_WITH_GROUP) {
            family.list(group);
        } else {
            family.overview();
        }
    }
}
