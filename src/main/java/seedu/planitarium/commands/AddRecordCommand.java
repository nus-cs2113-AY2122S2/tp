//@@author hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;

import java.util.logging.Level;

/**
 * Executes the add command and add an income or an expenditure record to a particular
 * person in the list.
 */
public class AddRecordCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String ADD_INCOME_CMD = "addin";
    protected static final String ADD_SPENT_CMD = "addout";

    protected static final String EXPEND = "expenditure";
    protected static final String INCOME = "income";
    protected static final String LOG_ADDRECORD_INFO = "A record of '%s' is going to "
            + "be added to someone with uid '%d' in group '%d'";
    protected static final String LOG_EXECUTE_INFO = "An '%s' record of '%s' with $'%.2f' in category '%d' "
            + "is going to be added to someone with uid '%d' in group '%d'";

    protected String keyword;
    protected String description;
    protected Double amount;
    protected Boolean isPermanent;
    protected Boolean isSilent;
    protected Integer group;
    protected Integer uid;
    protected Integer category = -1;

    public AddRecordCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = Constants.ADDRECORDCMDTYPE;
        keyword = Parser.parseCommandType(userInput);
        description = Parser.parseDescription(userInput);
        isPermanent = Parser.parseRecurringStatus(userInput);
        group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
        this.isSilent = Constants.FOR_USER;
        assert (uid >= 1) : Constants.USER_INDEX_NOT_VALID;
        logger.log(Level.INFO, String.format(LOG_ADDRECORD_INFO, description, group, uid));
    }

    public void execute() throws PlanITariumException {
        assert (keyword != null) : Constants.KEYWORD_NOT_NULL;
        assert (userInput != null) : Constants.INPUT_NOT_NULL;
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        assert (description != null) : Constants.DESCRIPTION_NOT_NULL;
        switch (keyword) {
        case ADD_INCOME_CMD:
            amount = Parser.getValidMoney(Parser.parseIncome(userInput));
            family.addIncome(group, uid, description, amount, isPermanent, isSilent);
            logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, INCOME, description, amount, category,
                    group, uid));
            break;
        case ADD_SPENT_CMD:
            amount = Parser.getValidMoney(Parser.parseExpenditure(userInput));
            category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
            family.addExpend(group, uid, description, amount, category, isPermanent, isSilent);
            logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, EXPEND, description, amount, category,
                    group, uid));
            break;
        default:
            logger.log(Level.WARNING, Constants.LOG_ERROR_INFO);
            throw new PlanITariumException(AddRecordCommand.class.getSimpleName());
        }

    }

    @Override
    public String getType() {
        return type;
    }

}
