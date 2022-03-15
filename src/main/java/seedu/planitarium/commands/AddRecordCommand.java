package seedu.planitarium.commands;

import seedu.planitarium.exceptions.UnknownException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

public class AddRecordCommand extends Command{
    protected static final String ADD_INCOME_CMD = "addin";
    protected static final String ADD_SPENT_CMD = "addout";
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
        newPerson = personList.getPerson(uid);

    }

    @Override
    public void execute() throws Exception {
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
