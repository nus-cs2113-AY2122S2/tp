package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.PersonList;

/**
 * Executes the delete command and delete a person from the list.
 */
public class DeletePersonCommand extends Command {
    protected static final String USER_INDEX_NOT_VALID = "User index should be valid";
    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";

    protected int uid;

    public DeletePersonCommand(String userInput, PersonList personList) throws PlanITariumException {
        super(userInput, personList);
        this.type = "DeletePersonCMD";
        this.uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList);
    }

    @Override
    public void execute() throws PlanITariumException {
        assert (uid < 1) : USER_INDEX_NOT_VALID;
        assert (personList != null) : PERSONLIST_NOT_NULL;
        personList.deletePerson(uid);
    }

}
