package seedu.planitarium.commands;

import seedu.planitarium.exceptions.UnknownException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.PersonList;

public class DeletePersonCommand extends Command {

    protected int uid;

    public DeletePersonCommand(String userInput, PersonList personList) throws Exception {
        super(userInput, personList);
        this.uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList);
    }

    @Override
    public void execute() throws UnknownException {
        personList.removePerson(uid);
    }

}
