package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

public class SearchCommand extends Command{
    public SearchCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }
}
