package seedu.planitarium.commands;

import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;

/**
 * Executes remain command and calculate the remaining money according to
 * income records and expenditure records.
 */
public class RemainCommand extends Command {

    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";

    public RemainCommand(String userInput, Family family) {
        super(userInput, family);
        this.type = "RemainCMD";
    }

    @Override
    public void execute() {
        assert (family != null) : PERSONLIST_NOT_NULL;
        // personList.printRemain();
    }
}
