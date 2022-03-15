package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

public class ListCommand extends Command{

    public ListCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }

    @Override
    public void execute() {
        personList.list();
    }
}
