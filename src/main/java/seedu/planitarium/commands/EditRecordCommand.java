package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

import java.util.logging.Level;

public class EditRecordCommand extends Command {

    protected static final String EXPEND = "expenditure";
    protected static final String INCOME = "income";
    protected static final String LOG_EDITREC_INFO =
            "A record named '%s' from a person with uid '%d' in group '%d' is going to be edited";
    protected static final String LOG_EXECUTE_INFO =
            "A '%s' named '%s' which index is '%d' with $'%.2f' in category '%d' from "
                    + "a person with uid '%d' in group '%d' is edited";

    protected static final String EDIT_INCOME_CMD = "editin";
    protected static final String EDIT_SPENT_CMD = "editout";
    protected static final String DESCRIPTION_NOT_NULL = "Description should not be empty";
    protected static final String INPUT_NOT_NULL = "Input should not be empty";
    protected static final String KEYWORD_NOT_NULL = "Keywords should not be empty";
    protected static final String FAMILY_NOT_NULL = "Family should not be null";
    protected static final String USER_INDEX_NOT_VALID = "User index should be valid";

    protected String keyword;
    protected Integer uid;
    protected Integer group;
    protected Integer index;
    protected Integer category;
    protected String description;
    protected Double amount;
    protected Boolean isPermanent;

    public EditRecordCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "EditRecordCMD";
        group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        keyword = Parser.parseKeyword(userInput);
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
        try {
            isPermanent = Parser.parseRecurringStatus(userInput);
        } catch (PlanITariumException e) {
            isPermanent = null;
        }
        try {
            description = Parser.parseDescription(userInput);
        } catch (PlanITariumException e) {
            description = null;
        }
        assert (uid > 0) : USER_INDEX_NOT_VALID;
        CommandFactory.logger.log(Level.INFO, String.format(LOG_EDITREC_INFO,description, uid,group));
    }

    public void execute() throws PlanITariumException {
        assert (keyword != null) : KEYWORD_NOT_NULL;
        assert (family != null) : FAMILY_NOT_NULL;
        switch (keyword) {
        case EDIT_INCOME_CMD:
            try {
                amount = Parser.getValidMoney(Parser.parseIncome(userInput));
            } catch (PlanITariumException e) {
                amount = null;
            }
            index = Parser.getValidIncomeIndex(Parser.parseRecordIndex(userInput),
                    family.getNumberOfIncomes(group, uid));
            family.editIncome(uid, group, index, description, amount, isPermanent);
            CommandFactory.logger.log(Level.INFO, String.format(
                    LOG_EXECUTE_INFO, INCOME, description, index, amount, category, uid, group));
            break;
        case EDIT_SPENT_CMD:
            index = Parser.getValidExpenditureIndex(Parser.parseRecordIndex(userInput),
                    family.getNumberOfExpenditures(uid, group));
            try {
                amount = Parser.getValidMoney(Parser.parseExpenditure(userInput));
            } catch (PlanITariumException e) {
                amount = null;
            }
            try {
                category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
            } catch (PlanITariumException e) {
                category = null;
            }
            family.editExpend(group, uid, index, description, amount, category, isPermanent);
            CommandFactory.logger.log(Level.INFO, String.format(
                    LOG_EXECUTE_INFO, EXPEND, description, index, amount, category, uid, group));
            break;
        default:
            CommandFactory.logger.log(Level.WARNING, Constants.LOG_ERROR_INFO);
            throw new PlanITariumException(AddRecordCommand.class.getSimpleName());
        }

    }

    @Override
    public String getType() {
        return type;
    }
}
