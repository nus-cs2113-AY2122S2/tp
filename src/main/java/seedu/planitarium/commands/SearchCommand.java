package seedu.planitarium.commands;

import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;

public class SearchCommand extends Command{
    public SearchCommand(String userInput, Family family) {
        super(userInput, family);
    }
}
