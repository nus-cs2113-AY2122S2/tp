//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;
import seedu.planitarium.family.Family;
import seedu.planitarium.global.Constants;


import java.util.logging.Level;

public class ListCategoriesCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String LOG_LISTCAT_INFO = "List categories command is going to be executed";
    protected static final String LOG_EXECUTE_INFO = "List categories command is executed";

    public ListCategoriesCommand(String userInput, Family family) {
        super(userInput, family);
        this.type = Constants.LISTCATCMDTYPE;
        logger.log(Level.INFO, LOG_LISTCAT_INFO);
    }

    public void execute() {
        Category.listCategories();
        logger.log(Level.INFO, LOG_EXECUTE_INFO);
    }

    @Override
    public String getType() {
        return type;
    }
}
