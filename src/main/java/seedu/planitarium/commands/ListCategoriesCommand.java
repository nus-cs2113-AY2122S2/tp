package seedu.planitarium.commands;

import seedu.planitarium.category.Category;
import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;

public class ListCategoriesCommand extends Command{
    public ListCategoriesCommand(String userInput, Family family) {
        super(userInput, family);
    }

    @Override
    public void execute() {
        Category.listCategories();
    }
}
