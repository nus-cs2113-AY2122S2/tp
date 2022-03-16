package seedu.planitarium.commands;

import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.PersonList;

/**
 * Executes the add command and add a person to the list.
 */
public class AddPersonCommand extends Command {
    protected static final String NAME_NOT_NULL = "Name should not be null";
    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";
    protected String name;

    public AddPersonCommand(String userInput, PersonList personList)
                throws MissingDelimiterException, DuplicateDelimiterException {
        super(userInput, personList);
        this.name = Parser.parseName(userInput);
    }

    @Override
    public void execute() throws Exception {
        assert (name != null) : NAME_NOT_NULL;
        assert (personList != null) : PERSONLIST_NOT_NULL;
        personList.addPerson(name);
    }

}
