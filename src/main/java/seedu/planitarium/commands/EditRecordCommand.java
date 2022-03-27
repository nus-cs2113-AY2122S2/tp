package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

public class EditRecordCommand extends Command{
    public EditRecordCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }
}
