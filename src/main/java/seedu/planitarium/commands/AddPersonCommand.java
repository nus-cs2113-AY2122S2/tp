package seedu.planitarium.commands;

import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.EmptyStringException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;

/**
 * Executes the add command and add a person to the list.
 */
public class AddPersonCommand extends Command {
    protected static final String NAME_NOT_NULL = "Name should not be null";
    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";
    protected String name;
    protected int group;

    public AddPersonCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "AddPersonCMD";
        this.name = Parser.parseName(userInput);
        this.group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
    }

    @Override
    public void execute() throws Exception {
        assert (name != null) : NAME_NOT_NULL;
        assert (family != null) : PERSONLIST_NOT_NULL;
        family.addPerson(name, group);
    }


}
