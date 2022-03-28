package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

public class EditRecordCommand extends Command{
    protected static final String EDIT_INCOME_CMD = "editin";
    protected static final String EDIT_SPENT_CMD = "editout";
    protected static final String DESCRIPTION_NOT_NULL = "Description should not be empty";
    protected static final String INPUT_NOT_NULL = "Input should not be empty";
    protected static final String KEYWORD_NOT_NULL = "Keywords should not be empty";
    protected static final String FAMILY_NOT_NULL = "Family should not be null";
    protected static final String USER_INDEX_NOT_VALID = "User index should be valid";

    protected String keyword;
    protected int uid;
    protected int group;
    protected int index;
    protected int category;
    protected String description;
    protected double amount;
    protected boolean isPermanent;

    public EditRecordCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "EditRecordCMD";
        group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        keyword = Parser.parseKeyword(userInput);
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
        description = Parser.parseDescription(userInput);
        category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
        isPermanent = Parser.parseRecurringStatus(userInput);
        //amount = Parser.getValidMoney(Parser.parseIncome(userInput));
        assert (uid < 1) : USER_INDEX_NOT_VALID;
    }

    @Override
    public void execute() throws PlanITariumException{
        assert (keyword != null) : KEYWORD_NOT_NULL;
        assert (userInput != null) : INPUT_NOT_NULL;
        assert (family != null) : FAMILY_NOT_NULL;
        assert (description != null) : DESCRIPTION_NOT_NULL;
        switch (keyword) {
        case EDIT_INCOME_CMD:
            amount = Parser.getValidMoney(Parser.parseIncome(userInput));
            family.editIncome(uid, group, index, description, amount, category, isPermanent);
            break;
        case EDIT_SPENT_CMD:
            amount = Parser.getValidMoney(Parser.parseExpenditure(userInput));
            family.editExpend(uid, group, index, description, amount, category, isPermanent);
            break;
        default:
            throw new PlanITariumException(AddRecordCommand.class.getSimpleName());
        }

    }
}
