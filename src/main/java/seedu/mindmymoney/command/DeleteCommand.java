package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.Lists;

/**
 * Represents the Delete command.
 */
public class DeleteCommand extends Command {
    private String input;
    public Lists itemList;


    public DeleteCommand(String input, Lists listArray) {
        this.input = input;
        this.itemList = listArray;
    }

    /**
     * Removes the item from the expenditure list.
     */
    public void executeCommand() throws MindMyMoneyException, NumberFormatException {
        try {
            if (itemList.isEmpty()) {
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
            if (positionToDelete + 1 <= 0 | positionToDelete + 1 > itemList.size()) {
                throw new MindMyMoneyException("Please input a valid index");
            } else {
                System.out.println(PrintStrings.LINE + "I have removed "
                        + itemList.get(positionToDelete).getDescription()
                        + " of $" + itemList.get(positionToDelete).getAmount()
                        + " from the account" + System.lineSeparator() + PrintStrings.LINE);
                itemList.delete(positionToDelete);

            }
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("INDEX must be a number");
        }
    }

}
