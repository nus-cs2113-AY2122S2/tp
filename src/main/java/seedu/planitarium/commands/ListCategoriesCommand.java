package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;
import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;

public class ListCategoriesCommand extends Command{
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    public ListCategoriesCommand(String userInput, Family family) {
        super(userInput, family);
    }

    @Override
    public void execute() {
        Category.listCategories();
    }
}
