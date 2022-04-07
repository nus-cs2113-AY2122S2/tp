//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;

import java.util.logging.Level;

/**
 * Executes the delete command and delete an income or an expenditure record to a particular
 * person in the list.
 */
public class DeleteRecordCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String EXPEND = "expenditure";
    protected static final String INCOME = "income";

    protected static final String LOG_DELETEREC_INFO =
            "A record from a person with uid '%d' in group '%d' is going to be deleted";
    protected static final  String LOG_EXECUTE_INFO =
            "An '%s' record with index '%d' from a person with uid '%d' in group '%d' is deleted";

    protected static final String DELETE_INCOME_CMD = "deletein";
    protected static final String DELETE_SPEND_CMD = "deleteout";
    protected String keyword;
    protected Integer index;
    protected Integer uid;
    protected Integer group;

    public DeleteRecordCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = Constants.DELETERECORDCMDTYPE;
        group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        keyword = Parser.parseCommandType(userInput);
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
        assert (uid > 0) : Constants.USER_INDEX_NOT_VALID;
        logger.log(Level.INFO, String.format(LOG_DELETEREC_INFO, group, uid));
    }

    public void execute() throws PlanITariumException {
        assert (keyword != null) : Constants.KEYWORD_NOT_NULL;
        assert (userInput != null) : Constants.INPUT_NOT_NULL;
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        switch (keyword) {
        case DELETE_INCOME_CMD:
            index = Parser.getValidIncomeIndex(Parser.parseRecordIndex(userInput),
                    family.getNumberOfIncomes(group, uid));
            family.deleteIncome(group, uid, index);
            logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, INCOME, index, group, uid));
            break;
        case DELETE_SPEND_CMD:
            index = Parser.getValidExpenditureIndex(Parser.parseRecordIndex(userInput),
                    family.getNumberOfExpenditures(group, uid));
            family.deleteExpend(group, uid, index);
            logger.log(Level.INFO, String.format(LOG_EXECUTE_INFO, EXPEND, index, group, uid));
            break;
        default:
            logger.log(Level.WARNING, Constants.LOG_ERROR_INFO);
            throw new PlanITariumException(DeleteRecordCommand.class.getSimpleName());
        }
    }

    @Override
    public String getType() {
        return type;
    }
}
