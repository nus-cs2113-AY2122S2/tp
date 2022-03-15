package seedu.planitarium.commands;

import seedu.planitarium.exceptions.UnknownException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

public class DeleteRecordCommand extends Command{
    protected static final String DELETE_INCOME_CMD = "deletein";
    protected static final String DELETE_SPEND_CMD = "deleteout";
    protected String keyword;
    protected int index;
    protected int uid;
    protected Person newPerson;

    public DeleteRecordCommand(String userInput, PersonList personList) throws Exception {
        super(userInput, personList);
        keyword = Parser.parseKeyword(userInput);
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList);
        newPerson = personList.getPerson(uid);
    }

    @Override
    public void execute() throws Exception {
        switch (keyword) {
        case DELETE_INCOME_CMD:
            index = Parser.getValidIncomeIndex(Parser.parseRecordIndex(userInput), newPerson);
            newPerson.deleteIncome(index);
            break;
        case DELETE_SPEND_CMD:
            index = Parser.getValidExpenditureIndex(Parser.parseRecordIndex(userInput), newPerson);
            newPerson.deleteExpend(index);
            break;
        default:
            throw new UnknownException();
        }
    }
}
