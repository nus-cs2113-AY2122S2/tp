package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

public class RemainCommand extends Command {

    public RemainCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }

    @Override
    public void execute() {
        personList.getRemain();
    }
}
