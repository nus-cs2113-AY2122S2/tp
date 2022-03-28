package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

/**
 * Executes the delete command and delete an income or an expenditure record to a particular
 * person in the list.
 */
public class DeleteRecordCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String DELETE_INCOME_CMD = "deletein";
    protected static final String DELETE_SPEND_CMD = "deleteout";
    protected String keyword;
    protected int index;
    protected int uid;
    protected int group;

    public DeleteRecordCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.type = "DeleteRecordCMD";
        group = Parser.getValidGroupIndex(Parser.parseGroupIndex(userInput));
        keyword = Parser.parseKeyword(userInput);
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), family.getNumberOfMembers(group));
        assert (uid < 1) : Constants.USER_INDEX_NOT_VALID;
    }

    @Override
    public void execute() throws PlanITariumException {
        assert (keyword != null) : Constants.KEYWORD_NOT_NULL;
        assert (userInput != null) : Constants.INPUT_NOT_NULL;
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        switch (keyword) {
        case DELETE_INCOME_CMD:
            index = Parser.getValidIncomeIndex(Parser.parseRecordIndex(userInput),
                    family.getNumberOfIncomes(uid, group));
            family.deleteIncome(uid, group, index);
            break;
        case DELETE_SPEND_CMD:
            index = Parser.getValidExpenditureIndex(Parser.parseRecordIndex(userInput),
                    family.getNumberOfExpenditures(uid, group));
            family.deleteExpend(uid, group, index);
            break;
        default:
            throw new PlanITariumException(DeleteRecordCommand.class.getSimpleName());
        }
    }
}
