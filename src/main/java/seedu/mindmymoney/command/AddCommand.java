package seedu.mindmymoney.command;

import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.Lists;
import seedu.mindmymoney.helper.Functions;
import seedu.mindmymoney.userfinancial.Expenditure;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM_IN_STRING;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM_IN_STRING;

/**
 * Represents the Add command.
 */
public class AddCommand extends Command {
    private String addInput;
    public Lists itemList;

    public AddCommand(String addInput, Lists listArray) {
        this.addInput = addInput;
        this.itemList = listArray;
    }

    /**
     * Sets the DESCRIPTION and AMOUNT fields in the users' expenditure and adds it into the list.
     */
    public void executeCommand() {
        try {
            System.out.print(PrintStrings.LINE);
            String[] parseAddInput = Functions.parseInput(addInput);
            String description = parseAddInput[INDEX_OF_FIRST_ITEM_IN_STRING];
            int amount = Integer.parseInt(parseAddInput[INDEX_OF_SECOND_ITEM_IN_STRING]);
            itemList.add(new Expenditure(description, amount));
            System.out.println("Successfully added " + description + " of $" + amount + " into the account");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Did you forget to input DESCRIPTION or AMOUNT?");
        } catch (NumberFormatException e) {
            System.out.println("AMOUNT must be a number");
        }
        System.out.print(PrintStrings.LINE);
    }
}
