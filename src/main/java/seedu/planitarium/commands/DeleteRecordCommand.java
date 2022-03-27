package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;
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
    protected static final String GROUP_NOT_NULL = "Group is not found";
    protected static final String FAMILY_NOT_NULL = "Family should not be null";
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
        assert (uid < 1) : USER_INDEX_NOT_VALID;
    }

    @Override
    public void execute() throws PlanITariumException {
        assert (keyword != null) : KEYWORD_NOT_NULL;
        assert (userInput != null) : INPUT_NOT_NULL;
        assert (family != null) : FAMILY_NOT_NULL;
        switch (keyword) {
        case DELETE_INCOME_CMD:
            try {
                index = Parser.getValidIncomeIndex(Parser.parseRecordIndex(userInput), family.getNumberOfIncomes(uid, group));
                family.deleteIncome(uid, group, index);
            } catch (PlanITariumException e) {
                throw e;
            }
            break;
        case DELETE_SPEND_CMD:
            try {
                index = Parser.getValidExpenditureIndex(Parser.parseRecordIndex(userInput), family.getNumberOfIncomes(uid, group));
                family.deleteExpend(uid, group, index);
            } catch (PlanITariumException e) {
                throw e;
            }
            break;
        default:
            throw new PlanITariumException(DeleteRecordCommand.class.getSimpleName());
        }
    }
}
