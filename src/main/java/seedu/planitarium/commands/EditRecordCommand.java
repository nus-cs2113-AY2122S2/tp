package seedu.planitarium.commands;

import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;

public class EditRecordCommand extends Command{
    public EditRecordCommand(String userInput, Family family) {
        super(userInput, family);
    }
}
