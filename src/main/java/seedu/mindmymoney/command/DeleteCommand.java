package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.ExpenditureList;

/**
 * Represents the Delete command.
 */
public class DeleteCommand extends Command {
    private String input;
    public ExpenditureList expenditureList;


    public DeleteCommand(String input, ExpenditureList expenditureList) {
        this.input = input;
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
     * Checks if the position to delete is within the bounds of the array length.
     * @param positionToDelete position of index to delete.
     * @return true if position is within the bounds, false otherwise.
     */
    public boolean checkOutOfBounds(int positionToDelete) {
        return (positionToDelete + 1 <= 0 || positionToDelete + 1 > expenditureList.size());
    }

    /**
     * Removes the item from the expenditure list.
     */
    public void executeCommand() throws MindMyMoneyException, NumberFormatException {
        try {
            if (expenditureList.isEmpty()) {
                throw new MindMyMoneyException(PrintStrings.LINE + System.lineSeparator()
                        + "Please add something to the list first:)"
                        + System.lineSeparator() + PrintStrings.LINE);
            }
            String[] splitMessage = input.split(" ");
            if (splitMessage.length != 2) {
                throw new MindMyMoneyException(PrintStrings.LINE + System.lineSeparator() + "Please input a number\n"
                        + "For eg. 'delete 2'\n" + PrintStrings.LINE);
            }
            String getNumber = splitMessage[1];
            int positionToDelete = Integer.parseInt(getNumber) - 1;
            if (checkOutOfBounds(positionToDelete)) {
                throw new MindMyMoneyException("Please input a valid index");
            } else {
                assert positionToDelete >= 0 : "Index should always be >= 0";
                System.out.print(PrintStrings.LINE + "I have removed "
                        + expenditureList.get(positionToDelete).getDescription()
                        + " of $" + expenditureList.get(positionToDelete).getAmount()
                        + " from the account" + System.lineSeparator() + PrintStrings.LINE);
                expenditureList.delete(positionToDelete);

            }
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("INDEX must be a number");
        }
    }

}
