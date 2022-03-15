package commands;

import exceptions.MissingDelimiterException;
import seedu.planitarium.parser.Parser;

public class AddPersonCommand extends Command {
    protected String name;

    public AddPersonCommand(String userInput) throws MissingDelimiterException {
        this.name = Parser.parseName(userInput);
    }

    @Override
    public void execute() {
        personList.addPerson(name);
    }

}
