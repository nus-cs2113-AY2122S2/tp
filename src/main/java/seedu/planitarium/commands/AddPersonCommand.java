//@@author hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

/**
 * Executes the add command and add a person to the list.
 */
public class AddPersonCommand extends Command {

    protected String name;
    protected int group;
    protected boolean isSilent;

    public AddPersonCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "AddPersonCMD";
        this.name = Parser.parseName(userInput);
        this.group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        this.isSilent = Constants.FOR_USER;
    }

    @Override
    public void execute() throws PlanITariumException {
        assert (name != null) : Constants.NAME_NOT_NULL;
        assert (family != null) : Constants.PERSONLIST_NOT_NULL;
        family.addPerson(group, name, isSilent);
    }


}
