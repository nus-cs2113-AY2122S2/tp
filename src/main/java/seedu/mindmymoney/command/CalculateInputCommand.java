package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.helper.GeneralFunctions;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENDITURE_PER_MONTH;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;
import static seedu.mindmymoney.helper.Calculations.calculateExpenditure;

/**
 * Represents the Calculate command.
 */
public class CalculateInputCommand extends Command {
    private String calculateInput;
    public ExpenditureList expenditureList;

    public CalculateInputCommand(String calculateInput, User user) {
        this.calculateInput = calculateInput;
        this.expenditureList = user.getExpenditureListArray();
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return true if the program should exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the input for calculate command.
     *
     * @throws MindMyMoneyException when inputs are invalid or flags are missing.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        try {
            String[] parsedCalculateInput = GeneralFunctions.parseInput(calculateInput);
            assert parsedCalculateInput[INDEX_OF_FIRST_ITEM] != null
                    : "First element in parsedCalculateInput is null";
            switch (parsedCalculateInput[INDEX_OF_FIRST_ITEM].toLowerCase()) {
            case FLAG_OF_EXPENDITURE_PER_MONTH:
                calculateExpenditure(parsedCalculateInput[INDEX_OF_SECOND_ITEM], expenditureList);
                break;
            default:
                throw new MindMyMoneyException("Remember to use a proper flag!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Missing input after command!");
        }
    }
}
