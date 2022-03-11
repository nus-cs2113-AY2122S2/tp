package seedu.mindmymoney.command;

import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.Lists;
import seedu.mindmymoney.userfinancial.Expenditure;

import static seedu.mindmymoney.constants.Indexes.*;

/**
 * Class for managing update commands.
 */
public class UpdateCommand extends Command {
    private final String updateInput;

    public UpdateCommand(String updateInput) {
        this.updateInput = updateInput;
    }

    /**
     * Executes the given update command. Parses its input, and prints
     * feedback to standard output.
     */
    @Override
    public void executeCommand() {
        try {
            System.out.print(PrintStrings.LINE);
            String[] parseUpdateInput = updateInput.split(" ", SPLIT_LIMIT);

            // get the index to update, amount, description
            String indexString = parseUpdateInput[INDEX_OF_FIRST_ITEM_IN_STRING];
            String expenditureDescription = parseUpdateInput[INDEX_OF_SECOND_ITEM_IN_STRING];
            int divisionIndex = expenditureDescription.lastIndexOf(" ");
            String description = expenditureDescription.substring(INDEX_OF_FIRST_ITEM_IN_STRING,
                    divisionIndex).strip();
            String amountString = expenditureDescription.substring(divisionIndex).strip();

            int indexToUpdate = Integer.parseInt(indexString) + LIST_INDEX_CORRECTION;
            Expenditure newExpenditure = new Expenditure(description, Integer.parseInt(amountString));
            Lists.expenditures.set(indexToUpdate, newExpenditure);

            System.out.printf("Successfully set expenditure %d to %s\n",
                    indexToUpdate - LIST_INDEX_CORRECTION, newExpenditure);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Did you forget to input INDEX, DESCRIPTION or AMOUNT?");
        } catch (NumberFormatException e) {
            System.out.println("AMOUNT and INDEX must be a number");
        } finally {
            System.out.print(PrintStrings.LINE);
        }
    }
}
