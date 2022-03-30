package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.Income;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Flags.*;
import static seedu.mindmymoney.constants.Indexes.SPLIT_LIMIT;
import static seedu.mindmymoney.constants.Indexes.LIST_INDEX_CORRECTION;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;

import static seedu.mindmymoney.helper.AddCommandInputTests.*;
import static seedu.mindmymoney.helper.GeneralFunctions.*;
import static seedu.mindmymoney.helper.TimeFunctions.convertTime;

/**
 * Represents the Update command.
 */
public class UpdateCommand extends Command {
    private final String updateInput;
    public ExpenditureList itemList;
    public CreditCardList creditCardList;
    public IncomeList incomeList;

    public UpdateCommand(String updateInput, User user) {
        this.updateInput = updateInput;
        this.itemList = user.getExpenditureListArray();
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
     * Indicates whether the update command is to update a credit card by looking for the /cc flag.
     *
     * @return true if the /cc flag is present, false otherwise.
     */
    private boolean hasCreditCardFlag() {
        return updateInput.contains(FLAG_OF_CREDIT_CARD);
    }

    /**
     * Indicates whether the update command is to update an income by looking for the /i flag.
     *
     * @return true if the /i flag is present, false otherwise.
     */
    private boolean hasIncomeFlag() {
        return updateInput.contains(FLAG_OF_INCOME);
    }

    private boolean hasCategoryFlag() {
        return updateInput.contains(FLAG_OF_CATEGORY);
    }

    /**
     * Updates an Expenditure entry in user's expenditure list.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    public void updateExpenditure() throws MindMyMoneyException {
        try {
            String newCategory = null;
            String[] parseUpdateInput = updateInput.split(" ", SPLIT_LIMIT);
            String indexAsString = parseUpdateInput[INDEX_OF_FIRST_ITEM];
            int indexToUpdate = Integer.parseInt(indexAsString) + LIST_INDEX_CORRECTION;
            String newPaymentMethod = null;

            // Parse data from user's input
            if (hasCategoryFlag()) {
                newPaymentMethod = parseInputWithCommandFlag(updateInput, FLAG_OF_PAYMENT_METHOD, FLAG_OF_CATEGORY);

                String inputCategory = parseInputWithCommandFlag(updateInput, FLAG_OF_CATEGORY, FLAG_OF_DESCRIPTION);
                testExpenditureCategory(inputCategory);
                newCategory = capitalise(inputCategory);
            } else {
                newPaymentMethod = parseInputWithCommandFlag(updateInput, FLAG_OF_PAYMENT_METHOD, FLAG_OF_DESCRIPTION);
                // Take previous expenditure's category
                newCategory = itemList.get(indexToUpdate).getCategory();
            }
            testPaymentMethod(newPaymentMethod, creditCardList);
            if (capitalise(newPaymentMethod).equals("Cash")) {
                newPaymentMethod = capitalise(newPaymentMethod);
            }

            String newDescription = parseInputWithCommandFlag(updateInput, FLAG_OF_DESCRIPTION, FLAG_OF_AMOUNT);
            testDescription(newDescription);

            String newAmountAsString = parseInputWithCommandFlag(updateInput, FLAG_OF_AMOUNT, FLAG_OF_TIME);
            testExpenditureAmount(newAmountAsString);
            float newAmountAsFloat = formatFloat(Float.parseFloat(newAmountAsString));

            String inputTime = parseInputWithCommandFlag(updateInput, FLAG_OF_TIME, FLAG_END_VALUE);
            String newTime = convertTime(inputTime);

            //Create new expenditure object to substitute in
            Expenditure newExpenditure = new Expenditure(newPaymentMethod, newCategory, newDescription,
                    newAmountAsFloat, newTime);
            itemList.set(indexToUpdate, newExpenditure);
            System.out.println(PrintStrings.LINE +
                    "Successfully set expenditure " + indexAsString + " to:\n"
            + newExpenditure.toString() + "\n" + PrintStrings.LINE);
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
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    public void updateCreditCard() throws MindMyMoneyException {
        try {
            String[] parseUpdateInput = updateInput.split(" ");

            //Get index to update
            String indexAsString = parseUpdateInput[INDEX_OF_SECOND_ITEM];

            //Parse data from input
            String newCardName = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_NAME,
                    FLAG_OF_CASHBACK);
            String newCashBack = parseInputWithCommandFlag(updateInput, FLAG_OF_CASHBACK,
                    FLAG_OF_CARD_LIMIT);
            String newCardLimit = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_LIMIT,
                    FLAG_OF_CARD_BALANCE);
            String newCardBalance = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_BALANCE,
                    FLAG_END_VALUE);
            int indexToUpdate = Integer.parseInt(indexAsString) + LIST_INDEX_CORRECTION;

            CreditCard newCreditCard = new CreditCard(newCardName, Double.parseDouble(newCashBack),
                    Float.parseFloat(newCardLimit), Float.parseFloat(newCardBalance));

            creditCardList.set(indexToUpdate, newCreditCard);
            System.out.println(PrintStrings.LINE + "Successfully set credit card " + indexAsString + " to :\n"
                    + newCreditCard.toString() + PrintStrings.LINE);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input INDEX, NAME, CASHBACK, CREDIT LIMIT or BALANCE?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("INDEX, CASHBACK, CREDIT LIMIT and BALANCE must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Updates an Income entry in user's income list.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    public void updateIncome() throws MindMyMoneyException {
        try {
            String[] parseUpdateInput = updateInput.split(" ");

            //Get index to update
            String indexAsString = parseUpdateInput[INDEX_OF_SECOND_ITEM];
            int indexToUpdate = Integer.parseInt(indexAsString) + LIST_INDEX_CORRECTION;

            //Parse data from input
            String newAmountAsString = parseInputWithCommandFlag(updateInput, FLAG_OF_AMOUNT,
                    FLAG_OF_CATEGORY);
            int newAmountAsInt = Integer.parseInt(newAmountAsString);
            testIncomeAmount(newAmountAsInt);

            String inputCategory = parseInputWithCommandFlag(updateInput, FLAG_OF_CATEGORY,
                    FLAG_END_VALUE);
            testIncomeCategory(inputCategory);
            String newCategory = capitalise(inputCategory);

            Income newIncome = new Income(newAmountAsInt, newCategory);
            incomeList.set(indexToUpdate, newIncome);

            System.out.printf("Successfully set income %d\n" + System.lineSeparator(),
                    indexToUpdate - LIST_INDEX_CORRECTION);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input AMOUNT or CATEGORY?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("AMOUNT must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Updates either an Expenditure, Credit Card or Income entry based on the user's input.
     *
     * @throws MindMyMoneyException when an invalid command is received, along with its corresponding error message.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        if (hasCreditCardFlag()) {
            updateCreditCard();
        } else if (hasIncomeFlag()) {
            updateIncome();
        } else {
            updateExpenditure();
        }
    }
}
