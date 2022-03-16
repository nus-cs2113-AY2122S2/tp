package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

/**
 * Executes list command and list all the information stored in the personlist.
 */
public class ListCommand extends Command{

    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";

    public ListCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }

    @Override
    public void execute() {
        assert (personList != null) : PERSONLIST_NOT_NULL;
        personList.list();
    }
}
