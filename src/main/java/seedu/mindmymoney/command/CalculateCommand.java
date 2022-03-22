package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.helper.GeneralFunctions;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM_IN_STRING;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENDITURE_PER_MONTH;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM_IN_STRING;
import static seedu.mindmymoney.helper.Calculations.calculateExpenditurePerMonth;

/**
 * Represents the Calculate command.
 */
public class CalculateCommand extends Command {
    private String calculateInput;
    public ExpenditureList expenditureList;

    public CalculateCommand(String calculateInput, ExpenditureList expenditureList) {
        this.calculateInput = calculateInput;
        this.expenditureList = expenditureList;
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
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
    public void executeCommand() throws MindMyMoneyException {
        try {
            String[] parsedCalculateInput = GeneralFunctions.parseInput(calculateInput);
            assert parsedCalculateInput[INDEX_OF_FIRST_ITEM_IN_STRING] != null
                    : "First element in parsedCalculateInput is null";
            switch (parsedCalculateInput[INDEX_OF_FIRST_ITEM_IN_STRING].toLowerCase()) {
            case FLAG_OF_EXPENDITURE_PER_MONTH:
                calculateExpenditurePerMonth(parsedCalculateInput[INDEX_OF_SECOND_ITEM_IN_STRING], expenditureList);
                break;
            default:
                throw new MindMyMoneyException("Remember to use a proper flag!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing input after command!");
        }
    }
}
