package seedu.planitarium.commands;

import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.PersonList;

public class AddPersonCommand extends Command {
    protected String name;

    public AddPersonCommand(String userInput, PersonList personList)
                throws MissingDelimiterException, DuplicateDelimiterException {
        super(userInput, personList);
        this.name = Parser.parseName(userInput);
    }

    @Override
    public void execute() throws Exception {
        personList.addPerson(name);
    }

}
