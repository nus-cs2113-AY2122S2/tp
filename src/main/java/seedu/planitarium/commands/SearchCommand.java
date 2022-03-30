package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

import java.util.logging.Level;

public class SearchCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String LOG_CMD_INFO = "Search command is going to be executed";
    protected static final String LOG_EXECUTE_INFO = "Search command is executed with description '%s' "
            + "and category index '%d'";

    protected String description;
    protected Integer category;

    public SearchCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "SearchCMD";
        this.description = Parser.parseDescription(userInput);
        this.category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
        logger.log(Level.INFO, LOG_CMD_INFO);
    }

    public void execute() {
        family.find(description, category);
        logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, description, category));
    }

    @Override
    public String getType() {
        return type;
    }
}
