package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

/**
 * Executes remain command and calculate the remaining money according to
 * income records and expenditure records.
 */
public class RemainCommand extends Command {

    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";

    public RemainCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }

    @Override
    public void execute() {
        assert (personList != null) : PERSONLIST_NOT_NULL;
        personList.printRemain();
    }
}
