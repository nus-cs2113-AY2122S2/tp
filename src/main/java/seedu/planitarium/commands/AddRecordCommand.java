package seedu.planitarium.commands;

import seedu.planitarium.exceptions.UnknownException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

/**
 * Executes the add command and add an income or an expenditure record to a particular
 * person in the list.
 */
public class AddRecordCommand extends Command {
    protected static final String ADD_INCOME_CMD = "addin";
    protected static final String ADD_SPENT_CMD = "addout";
    protected static final String DESCRIPTION_NOT_NULL = "Description should not be empty";
    protected static final String INPUT_NOT_NULL = "Input should not be empty";
    protected static final String KEYWORD_NOT_NULL = "Keywords should not be empty";
    protected static final String PERSON_NOT_NULL = "The person is not found";
    protected static final String PERSONLIST_NOT_NULL = "Personlist should not be null";
    protected static final String USER_INDEX_NOT_VALID = "User index should be valid";
    protected String keyword;
    protected String description;
    protected Double amount;
    protected int uid;
    protected Person newPerson;

    public AddRecordCommand(String userInput, PersonList personList) throws Exception {
        super(userInput, personList);
        keyword = Parser.parseKeyword(userInput);
        description = Parser.parseDescription(userInput);
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
        assert (description != null) : DESCRIPTION_NOT_NULL;
        switch (keyword) {
        case ADD_INCOME_CMD:
            amount = Parser.getValidMoney(Parser.parseIncome(userInput));
            newPerson.addIncome(description, amount);
            break;
        case ADD_SPENT_CMD:
            amount = Parser.getValidMoney(Parser.parseExpenditure(userInput));
            newPerson.addExpend(description, amount);
            break;
        default:
            throw new UnknownException();
        }

    }

}
