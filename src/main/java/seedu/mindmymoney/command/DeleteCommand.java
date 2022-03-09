package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.Lists;

/**
 * Represents the Delete command
 */
public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Removes the item from the expenditure list
     */
    public void executeCommand() throws MindMyMoneyException {
        try {
            if (Lists.expenditures.isEmpty()) {
                throw new MindMyMoneyException(PrintStrings.LINE + System.lineSeparator() + "Please add something to the list first:)"
                        + System.lineSeparator() + PrintStrings.LINE);
            }
            String[] splitMessage = input.split(" ");
            if (splitMessage.length != 2) {
                throw new MindMyMoneyException(PrintStrings.LINE + System.lineSeparator() + "Please input a number\n" +
                        "For eg. 'delete 2'\n" + PrintStrings.LINE);
            }
            String getNumber = splitMessage[1];
            int positionToDelete = Integer.parseInt(getNumber) - 1;
            if (positionToDelete + 1 <= 0 | positionToDelete + 1 > Lists.expenditures.size()) {
                throw new MindMyMoneyException("Please input a valid index");
            } else {
                System.out.println(PrintStrings.LINE + "I have removed " + Lists.expenditures.get(positionToDelete).getDescription()
                        + " of $" + Lists.expenditures.get(positionToDelete).getAmount() + " from the account" + System.lineSeparator() + PrintStrings.LINE);
                Lists.expenditures.remove(positionToDelete);

            }
        } catch (NumberFormatException e) {
            System.out.println("INDEX must be a number");
        }
    }

}
