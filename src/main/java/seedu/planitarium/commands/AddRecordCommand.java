package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

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
    protected static final String FAMILY_NOT_NULL = "Family should not be null";
    protected static final String USER_INDEX_NOT_VALID = "User index should be valid";
    protected String keyword;
    protected String description;
    protected Double amount;
    protected boolean isPermanent;
    protected int group;
    protected int uid;
    protected int category;

    public AddRecordCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "AddRecordCMD";
        keyword = Parser.parseKeyword(userInput);
        description = Parser.parseDescription(userInput);
        isPermanent = Parser.parseRecurringStatus(userInput);
        group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
        category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
        assert (uid < 1) : USER_INDEX_NOT_VALID;

    }

    @Override
    public void execute() throws Exception {
        assert (keyword != null) : KEYWORD_NOT_NULL;
        assert (userInput != null) : INPUT_NOT_NULL;
        assert (family != null) : FAMILY_NOT_NULL;
        assert (description != null) : DESCRIPTION_NOT_NULL;
        switch (keyword) {
        case ADD_INCOME_CMD:
            amount = Parser.getValidMoney(Parser.parseIncome(userInput));
            family.addIncome(uid, group, description, amount, isPermanent, category);
            break;
        case ADD_SPENT_CMD:
            amount = Parser.getValidMoney(Parser.parseExpenditure(userInput));
            family.addExpend(uid, group, description, amount, isPermanent, category);
            break;
        default:
            throw new PlanITariumException(AddRecordCommand.class.getSimpleName());
        }

    }

}
