package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_NAME;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CASHBACK;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_LIMIT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_BALANCE;
import static seedu.mindmymoney.constants.Flags.FLAG_END_VALUE;
import static seedu.mindmymoney.constants.Indexes.SPLIT_LIMIT;
import static seedu.mindmymoney.constants.Indexes.LIST_INDEX_CORRECTION;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM_IN_STRING;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM_IN_STRING;
import static seedu.mindmymoney.helper.GeneralFunctions.parseInputWithCommandFlag;

/**
 * Class for managing update commands.
 */
public class UpdateCommand extends Command {
    private final String updateInput;
    public ExpenditureList itemList;
    public CreditCardList creditCardList;

    public UpdateCommand(String updateInput, ExpenditureList expenditureList, CreditCardList creditCardList) {
        this.updateInput = updateInput;
        this.itemList = expenditureList;
        this.creditCardList = creditCardList;
    }

    private boolean hasCreditCardFlag() {
        return updateInput.contains(FLAG_OF_CREDIT_CARD);
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
     * Executes the given update command. Parses its input, and prints
     * feedback to standard output.
     */
    public void updateExpenditure() throws MindMyMoneyException {
        try {
            String description;
            String category = null;
            String[] parseUpdateInput = updateInput.split(" ", SPLIT_LIMIT);

            // get the index to update, amount, description
            String indexString = parseUpdateInput[INDEX_OF_FIRST_ITEM_IN_STRING];
            String expenditureDescription = parseUpdateInput[INDEX_OF_SECOND_ITEM_IN_STRING];
            int divisionIndex = expenditureDescription.lastIndexOf(" ");
            String descriptionAndCategory = expenditureDescription.substring(INDEX_OF_FIRST_ITEM_IN_STRING,
                    divisionIndex).strip();
            if (descriptionAndCategory.contains("-c ")) {
                descriptionAndCategory = descriptionAndCategory.replace("-c ", "");
                int divisionIndexForCategory = descriptionAndCategory.lastIndexOf(" ");
                description = descriptionAndCategory.substring(INDEX_OF_FIRST_ITEM_IN_STRING,
                        divisionIndexForCategory).strip();
                category = descriptionAndCategory.substring(divisionIndexForCategory).strip();
            } else {
                description = expenditureDescription.substring(INDEX_OF_FIRST_ITEM_IN_STRING,
                        divisionIndex).strip();
            }
            String amountString = expenditureDescription.substring(divisionIndex).strip();
            int indexToUpdate = Integer.parseInt(indexString) + LIST_INDEX_CORRECTION;

            //to edit to fit new add command
            Expenditure newExpenditure = new Expenditure("cash", category, description,
                    Integer.parseInt(amountString), "2022-02");
            itemList.set(indexToUpdate, newExpenditure);
            System.out.printf("Successfully set expenditure %d to %s\n" + System.lineSeparator(),
                    indexToUpdate - LIST_INDEX_CORRECTION, newExpenditure);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input INDEX, DESCRIPTION or AMOUNT?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("AMOUNT and INDEX must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Updates a Credit Card entry in user's credit card list.
     * @throws MindMyMoneyException Exception thrown when incorrect input is given
     */
    public void updateCreditCard() throws MindMyMoneyException {
        try {
            String[] parseUpdateInput = updateInput.split(" ");

            //Get index to update
            String indexString = parseUpdateInput[INDEX_OF_SECOND_ITEM_IN_STRING];

            //Parse data from input
            String newCardName = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_NAME,
                    FLAG_OF_CASHBACK);
            String newCashBack = parseInputWithCommandFlag(updateInput, FLAG_OF_CASHBACK,
                    FLAG_OF_CARD_LIMIT);
            String newCardLimit = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_LIMIT,
                    FLAG_OF_CARD_BALANCE);
            String newCardBalance = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_BALANCE,
                    FLAG_END_VALUE);
            int indexToUpdate = Integer.parseInt(indexString) + LIST_INDEX_CORRECTION;

            //to edit to fit new add command
            CreditCard newCreditCard = new CreditCard(newCardName, Double.parseDouble(newCashBack),
                    Float.parseFloat(newCardLimit), Float.parseFloat(newCardBalance));

            creditCardList.set(indexToUpdate, newCreditCard);
            System.out.printf("Successfully set credit card %d\n" + System.lineSeparator(),
                    indexToUpdate - LIST_INDEX_CORRECTION);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input NAME, CASHBACK, CREDIT LIMIT or BALANCE?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("CASHBACK, CREDIT LIMIT and BALANCE must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    public void executeCommand() throws MindMyMoneyException {
        if (hasCreditCardFlag()) {
            updateCreditCard();
        } else {
            updateExpenditure();
        }
    }

}
