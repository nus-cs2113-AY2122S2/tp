package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;

/**
 * Executes the delete command and delete a person from the list.
 */
public class DeletePersonCommand extends Command {

    protected int uid;
    protected int group;

    public DeletePersonCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "DeletePersonCMD";
        this.group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        this.uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
    }

    @Override
    public void execute() throws PlanITariumException {
        assert (uid < 1) : Constants.USER_INDEX_NOT_VALID;
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        family.deletePerson(uid,group);
    }

}
