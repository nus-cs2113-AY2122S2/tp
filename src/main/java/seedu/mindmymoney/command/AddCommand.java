package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.helper.Functions;
import seedu.mindmymoney.userfinancial.Expenditure;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM_IN_STRING;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM_IN_STRING;

/**
 * Represents the Add command.
 */
public class AddCommand extends Command {
    private String addInput;
    public ExpenditureList expenditureList;

    public AddCommand(String addInput, ExpenditureList expenditureList) {
        this.addInput = addInput;
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
     * Sets the DESCRIPTION and AMOUNT fields in the users' expenditure and adds it into the list.
     */
    public void executeCommand() throws MindMyMoneyException {
        try {
            String category = null;
            String[] parseAddInput = Functions.parseInput(addInput);
            String description = parseAddInput[INDEX_OF_FIRST_ITEM_IN_STRING];
            assert description != null : "Description should not be null";

            if (parseAddInput[INDEX_OF_SECOND_ITEM_IN_STRING].contains("-c")) {
                parseAddInput = Functions.parseInputWithCommandFlag(parseAddInput[INDEX_OF_SECOND_ITEM_IN_STRING]);
                category = parseAddInput[INDEX_OF_FIRST_ITEM_IN_STRING];
            }

            int amount = Integer.parseInt(parseAddInput[INDEX_OF_SECOND_ITEM_IN_STRING]);
            assert amount >= 0 : "Amount should have a positive value";
            expenditureList.add(new Expenditure(description, category, amount));
            if (category == null) {
                System.out.println("Successfully added " + description + " of $" + amount + " into the account");
            } else {
                System.out.println("Successfully added " + description + " of $" + amount + " from " + category
                        + " into the account");
            }
            System.out.print(System.lineSeparator());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input DESCRIPTION or AMOUNT?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("AMOUNT must be a number");
        }
    }
}
