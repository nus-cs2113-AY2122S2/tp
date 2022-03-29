package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;

import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_INCOME;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_THIRD_ITEM;
import static seedu.mindmymoney.constants.Indexes.LIST_INDEX_CORRECTION;
import static seedu.mindmymoney.constants.Indexes.MINIMUM_CREDIT_CARD_COMMAND_LENGTH;
import static seedu.mindmymoney.constants.Indexes.MINIMUM_EXPENDITURE_COMMAND_LENGTH;
import static seedu.mindmymoney.constants.Indexes.MINIMUM_INCOME_COMMAND_LENGTH;

/**
 * Represents the Delete command.
 */
public class DeleteCommand extends Command {
    private String input;
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    public IncomeList incomeList;

    public DeleteCommand(String input, User user) {
        this.input = input;
        this.expenditureList = user.getExpenditureListArray();
        this.creditCardList = user.getCreditCardListArray();
        this.incomeList = user.getIncomeListArray();
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
     * Indicates whether the delete command is to delete a credit card by looking for the /cc flag.
     *
     * @return true if the /cc flag is present, false otherwise.
     */
    private boolean hasCreditCardFlag() {
        return input.contains(FLAG_OF_CREDIT_CARD);
    }

    /**
     * Indicates whether the delete command is to delete an income by looking for the /i flag.
     *
     * @return true if the /i flag is present, false otherwise.
     */
    private boolean hasIncomeFlag() {
        return input.contains(FLAG_OF_INCOME);
    }

    /**
     * Checks if the position to delete is within the bounds of the credit card list.
     *
     * @param positionToDelete position of index to delete.
     * @return true if position is within the bounds, false otherwise.
     */
    public boolean isOutOfCreditCardListBounds(int positionToDelete) {
        return (positionToDelete + 1 <= 0 || positionToDelete + 1 > creditCardList.size());
    }

    /**
     * Removes an expenditure from user's list of expenditure(s).
     *
     * @throws MindMyMoneyException when expenditure list is empty or an invalid command is received.
     */
    public void deleteExpenditure() throws MindMyMoneyException {
        try {
            if (expenditureList.isEmpty()) {
                throw new MindMyMoneyException(System.lineSeparator()
                        + "Please add something to your expenditure list first:)"
                        + System.lineSeparator());
            }

            String[] splitMessage = input.split(" ");
            if (splitMessage.length != MINIMUM_EXPENDITURE_COMMAND_LENGTH) {
                throw new MindMyMoneyException(System.lineSeparator() + "Please input a number\n"
                        + "For eg. 'delete 2'\n");
            }

            String getNumber = splitMessage[INDEX_OF_SECOND_ITEM];
            int positionToDelete = Integer.parseInt(getNumber) + LIST_INDEX_CORRECTION;
            System.out.println("I have removed "
                    + expenditureList.get(positionToDelete).getDescription()
                    + " of $" + expenditureList.get(positionToDelete).getAmount()
                    + " from the account" + System.lineSeparator());
            expenditureList.delete(positionToDelete);
            assert positionToDelete >= 0 : "Index should always be >= 0";

        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("INDEX must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Removes a credit card from user's list of credit card(s).
     *
     * @throws MindMyMoneyException when credit card list is empty or an invalid command is received.
     */
    public void deleteCreditCard() throws MindMyMoneyException {
        try {
            if (creditCardList.isEmpty()) {
                throw new MindMyMoneyException(System.lineSeparator()
                        + "Please add something to your credit card list first:)"
                        + System.lineSeparator());
            }

            String[] splitMessage = input.split(" ");
            if (splitMessage.length != MINIMUM_CREDIT_CARD_COMMAND_LENGTH) {
                throw new MindMyMoneyException(System.lineSeparator() + "Please input a number\n"
                        + "For eg. 'delete /cc 2' to remove the second credit card on your list.\n");
            }

            String getNumber = splitMessage[INDEX_OF_THIRD_ITEM];
            int positionToDelete = Integer.parseInt(getNumber) + LIST_INDEX_CORRECTION;
            assert positionToDelete >= 0 : "Index should always be >= 0";

            System.out.println("I have removed "
                    + creditCardList.get(positionToDelete).getNameOfCard()
                    + " from your list of credit card(s)." + System.lineSeparator());
            creditCardList.delete(positionToDelete);

        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("INDEX must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Removes an income from user's list of income(s).
     *
     * @throws MindMyMoneyException when income list is empty or an invalid command is received.
     */
    public void deleteIncome() throws MindMyMoneyException {
        try {
            if (incomeList.isEmpty()) {
                throw new MindMyMoneyException(System.lineSeparator()
                        + "Please add something to your income list first:)"
                        + System.lineSeparator());
            }

            String[] splitMessage = input.split(" ");
            if (splitMessage.length != MINIMUM_INCOME_COMMAND_LENGTH) {
                throw new MindMyMoneyException(System.lineSeparator() + "Please input a number\n"
                        + "For eg. 'delete /i 2' to remove the second income on your list.\n");
            }

            String getNumber = splitMessage[INDEX_OF_THIRD_ITEM];
            int positionToDelete = Integer.parseInt(getNumber) + LIST_INDEX_CORRECTION;
            assert positionToDelete >= 0 : "Index should always be >= 0";

            System.out.println("I have removed "
                    + incomeList.get(positionToDelete).getCategory()
                    + " from your list of income(s)." + System.lineSeparator());
            incomeList.delete(positionToDelete);

        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Input a number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Input a valid number!");
        }
    }

    /**
     * Removes an expenditure, credit card or income from the user's list based on the input.
     *
     * @throws MindMyMoneyException when an invalid command is received, along with its corresponding error message.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        if (hasCreditCardFlag()) {
            deleteCreditCard();
        } else if (hasIncomeFlag()) {
            deleteIncome();
        } else {
            deleteExpenditure();
        }
    }
}
