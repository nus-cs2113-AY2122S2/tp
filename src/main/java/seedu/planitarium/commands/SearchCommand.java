//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;

import java.util.logging.Level;

public class SearchCommand extends Command {

    protected static final String LOG_CMD_INFO = "Search command is going to be executed";
    protected static final String LOG_EXECUTE_INFO = "Search command is executed with description '%s' "
            + "and category index '%d'";

    protected String description;
    protected Integer category;

    public SearchCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = Constants.SEARCHCMDTYPE;
        this.description = Parser.parseDescription(userInput);
        try {
            this.category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
        } catch (MissingDelimiterException e) {
            category = 0;
        }
        CommandFactory.logger.log(Level.INFO, LOG_CMD_INFO);
    }

    public void execute() {
        family.find(description, category);
        CommandFactory.logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, description, category));
    }

    @Override
    public String getType() {
        return type;
    }
}
