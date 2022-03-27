package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

public class ListCategoriesCommand extends Command{
    public ListCategoriesCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }
}
