package seedu.planitarium.commands;

import seedu.planitarium.exceptions.UnknownException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

/**
 * Executes the delete command and delete an income or an expenditure record to a particular
 * person in the list.
 */
public class DeleteRecordCommand extends Command {
    protected static final String DELETE_INCOME_CMD = "deletein";
    protected static final String DELETE_SPEND_CMD = "deleteout";
    protected static final String INPUT_NOT_NULL = "Input should not be empty";
    protected static final String KEYWORD_NOT_NULL = "Keywords should not be empty";
    protected static final String USER_INDEX_NOT_VALID = "User index should be valid";
    protected static final String PERSON_NOT_NULL = "The person is not found";
    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";
    protected String keyword;
    protected int index;
    protected int uid;
    protected Person newPerson;

    public DeleteRecordCommand(String userInput, PersonList personList) throws Exception {
        super(userInput, personList);
        keyword = Parser.parseKeyword(userInput);
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList);
        assert (uid < 1) : USER_INDEX_NOT_VALID;
        newPerson = personList.getPerson(uid);
    }

    @Override
    public void execute() throws Exception {
        assert (keyword != null) : KEYWORD_NOT_NULL;
        assert (userInput != null) : INPUT_NOT_NULL;
        assert (newPerson != null) : PERSON_NOT_NULL;
        assert (personList != null) : PERSONLIST_NOT_NULL;
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
