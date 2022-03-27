package seedu.planitarium.commands;

import seedu.planitarium.person.Family;

public class EditRecordCommand extends Command{
    public EditRecordCommand(String userInput, Family family) {
        super(userInput, family);
    }
}
