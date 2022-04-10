package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.Income;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_INCOME;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CATEGORY;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_PAYMENT_METHOD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_AMOUNT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_TIME;
import static seedu.mindmymoney.constants.Flags.FLAG_END_VALUE;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_LIMIT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_NAME;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CASHBACK;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_DESCRIPTION;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;
import static seedu.mindmymoney.constants.Indexes.LIST_INDEX_CORRECTION;
import static seedu.mindmymoney.data.CreditCardList.isEqualName;
import static seedu.mindmymoney.data.CreditCardList.isEqualCashback;
import static seedu.mindmymoney.data.CreditCardList.isEqualCardLimit;
import static seedu.mindmymoney.data.ExpenditureList.isEqualCategory;
import static seedu.mindmymoney.data.ExpenditureList.isEqualPaymentMethod;
import static seedu.mindmymoney.data.ExpenditureList.isEqualDescription;
import static seedu.mindmymoney.data.ExpenditureList.isEqualAmount;
import static seedu.mindmymoney.data.ExpenditureList.isEqualTime;
import static seedu.mindmymoney.data.IncomeList.isEqualIncomeCategory;
import static seedu.mindmymoney.data.IncomeList.isEqualIncomeAmount;
import static seedu.mindmymoney.helper.AddCommandInputTests.testIncomeAmount;
import static seedu.mindmymoney.helper.AddCommandInputTests.testIncomeCategory;
import static seedu.mindmymoney.helper.AddCommandInputTests.testExpenditureParameters;
import static seedu.mindmymoney.helper.GeneralFunctions.capitalise;
import static seedu.mindmymoney.helper.GeneralFunctions.parseInputWithCommandFlag;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;

/**
 * Represents the Update command.
 */
public class UpdateCommand extends Command {
    private final String updateInput;
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    public IncomeList incomeList;

    public UpdateCommand(String updateInput, User user) {
        this.updateInput = updateInput;
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
     * Indicates whether the help command is for expenses by looking for the /e flag.
     *
     * @return true if the /e flag is present, false otherwise.
     */
    private boolean hasExpensesFlag() {
        return updateInput.contains(FLAG_OF_EXPENSES);
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

    /**
     * Updates an Expenditure entry in user's expenditure list.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    public void updateExpenditure() throws MindMyMoneyException {
        try {
            String[] parseUpdateInput = updateInput.split(" ");
            String indexAsString = parseUpdateInput[INDEX_OF_SECOND_ITEM];
            final int indexToUpdate = Integer.parseInt(indexAsString) + LIST_INDEX_CORRECTION;

            String newPaymentMethod = parseInputWithCommandFlag(updateInput, FLAG_OF_PAYMENT_METHOD, FLAG_OF_CATEGORY);
            String inputCategory = parseInputWithCommandFlag(updateInput, FLAG_OF_CATEGORY, FLAG_OF_DESCRIPTION);
            String newDescription = parseInputWithCommandFlag(updateInput, FLAG_OF_DESCRIPTION, FLAG_OF_AMOUNT);
            String newAmountAsString = parseInputWithCommandFlag(updateInput, FLAG_OF_AMOUNT, FLAG_OF_TIME);
            String inputTime = parseInputWithCommandFlag(updateInput, FLAG_OF_TIME, FLAG_END_VALUE);

            testExpenditureParameters(newPaymentMethod, inputCategory, newDescription, newAmountAsString,
                    inputTime, creditCardList);

            final String newCategory = capitalise(inputCategory);
            float newAmountAsFloat = formatFloat(Float.parseFloat(newAmountAsString));
            if (capitalise(newPaymentMethod).equals("Cash")) {
                newPaymentMethod = capitalise(newPaymentMethod);
            }
            if (isSimilarExpenditure(indexToUpdate, newPaymentMethod, newCategory, newDescription, newAmountAsFloat,
                    inputTime)) {
                throw new MindMyMoneyException("Expense fields to be updated is similar to the expense in the list.\n"
                        + "Please make sure the field descriptions you want to change are different.");
            }
            // Create new expenditure object to substitute in
            Expenditure newExpenditure = new Expenditure(newPaymentMethod, newCategory, newDescription,
                    newAmountAsFloat, inputTime);
            expenditureList.set(indexToUpdate, newExpenditure);
            System.out.println("Successfully set expenditure " + indexAsString + " to:\n"
                    + "$" + newExpenditure.getAmount() + " was spent on " + newExpenditure.getDescription()
                    + "(" + newExpenditure.getCategory() + ") " + "using " + newExpenditure.getPaymentMethod()
                    + " [" + newExpenditure.getTime() + "]");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input INDEX, DESCRIPTION or AMOUNT?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("AMOUNT and INDEX must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Checks if the fields in the update command is similar to the fields in the expenditure in the list.
     *
     * @param index index of expenditure to update.
     * @param newPaymentMethod new payment method field to be updated.
     * @param newCategory new category field to be updated.
     * @param newDescription new description field to be updated.
     * @param newAmountAsFloat new amount field to be updated.
     * @param newTime new time field to be updated.
     * @return true if fields are similar, false otherwise.
     */
    public boolean isSimilarExpenditure(int index, String newPaymentMethod, String newCategory, String newDescription,
                             float newAmountAsFloat, String newTime) {
        if (isEqualPaymentMethod(expenditureList, index, newPaymentMethod)
                && isEqualCategory(expenditureList, index, newCategory)
                && isEqualDescription(expenditureList, index, newDescription)
                && isEqualAmount(expenditureList, index, newAmountAsFloat)
                && isEqualTime(expenditureList, index, newTime)) {
            return true;
        }
        return false;
    }

    /**
     * Updates a Credit Card entry in user's credit card list.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    public void updateCreditCard() throws MindMyMoneyException {
        try {
            String[] parseUpdateInput = updateInput.split(" ");

            // Get index to update
            String indexAsString = parseUpdateInput[INDEX_OF_SECOND_ITEM];

            // Parse data from input
            String newCardName = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_NAME,
                    FLAG_OF_CASHBACK);
            String newCashBack = parseInputWithCommandFlag(updateInput, FLAG_OF_CASHBACK,
                    FLAG_OF_CARD_LIMIT);
            String newCardLimit = parseInputWithCommandFlag(updateInput, FLAG_OF_CARD_LIMIT,
                    FLAG_END_VALUE);
            int indexToUpdate = Integer.parseInt(indexAsString) + LIST_INDEX_CORRECTION;

            double newCashBackAsDouble = Double.parseDouble(newCashBack);
            float newCardLimitAsFloat = Float.parseFloat(newCardLimit);

            CreditCard oldCreditCard = creditCardList.get(indexToUpdate);
            if (oldCreditCard.getTotalExpenditure() > newCardLimitAsFloat) {
                throw new MindMyMoneyException("Current spending has already exceeded the new limit!");
            }
            if (isSimilarCreditCard(indexToUpdate, newCardName, newCashBackAsDouble, newCardLimitAsFloat)) {
                throw new MindMyMoneyException("Credit Card fields to be updated is similar to the credit card in "
                        + "the list.\n" + "Please make sure the field descriptions you want to change are different.");
            }
            CreditCard newCreditCard = new CreditCard(newCardName, newCashBackAsDouble,
                    newCardLimitAsFloat);

            creditCardList.set(indexToUpdate, newCreditCard);
            System.out.println("Successfully set credit card " + indexAsString + " to:\n"
                    + newCreditCard);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input INDEX, NAME, CASHBACK or CREDIT LIMIT?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("INDEX, CASHBACK and CREDIT LIMIT must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Checks if the fields in the update command is similar to the fields in the credit card in the list.
     *
     * @param index index of credit card to update.
     * @param newCardName new card name field to be updated.
     * @param newCashback new cash back field to be updated.
     * @param newCardLimit new card limit field to be updated.
     * @return true if fields are similar, false otherwise.
     */
    public boolean isSimilarCreditCard(int index, String newCardName, double newCashback, float newCardLimit) {
        if (isEqualName(creditCardList, index, newCardName)
                && isEqualCashback(creditCardList, index, newCashback)
                && isEqualCardLimit(creditCardList, index, newCardLimit)) {
            return true;
        }
        return false;
    }

    /**
     * Updates an Income entry in user's income list.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    public void updateIncome() throws MindMyMoneyException {
        try {
            String[] parseUpdateInput = updateInput.split(" ");

            String indexAsString = parseUpdateInput[INDEX_OF_SECOND_ITEM];
            int indexToUpdate = Integer.parseInt(indexAsString) + LIST_INDEX_CORRECTION;

            String newAmountAsString = parseInputWithCommandFlag(updateInput, FLAG_OF_AMOUNT,
                    FLAG_OF_CATEGORY);
            int newAmountAsInt = Integer.parseInt(newAmountAsString);
            testIncomeAmount(newAmountAsInt);

            String inputCategory = parseInputWithCommandFlag(updateInput, FLAG_OF_CATEGORY,
                    FLAG_END_VALUE);
            testIncomeCategory(inputCategory);
            String newCategory = capitalise(inputCategory);
            if (isSimilarIncome(indexToUpdate, newAmountAsInt, newCategory)) {
                throw new MindMyMoneyException("Income fields to be updated is similar to the income in the list.\n"
                        + "Please make sure the field descriptions you want to change are different.");
            }
            Income newIncome = new Income(newAmountAsInt, newCategory);
            incomeList.set(indexToUpdate, newIncome);

            System.out.print("Successfully set income " + indexAsString + " to:\n"
                    + "Amount: $" + newAmountAsString + "\n"
                    + "Category: " + newCategory + "\n"
                    + System.lineSeparator());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Did you forget to input AMOUNT or CATEGORY?");
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("AMOUNT must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new MindMyMoneyException("Please input a valid index");
        }
    }

    /**
     * Checks if the fields in the update command is similar to the fields in the income in the list.
     *
     * @param index index of income to update.
     * @param newAmount new amount to be updated.
     * @param newCategory new category to be updated.
     * @return true if fields are similar, false otherwise.
     */
    public boolean isSimilarIncome(int index, int newAmount, String newCategory) {
        if (isEqualIncomeCategory(incomeList, index, newCategory)
                && isEqualIncomeAmount(incomeList, index, newAmount)) {
            return true;
        }
        return false;
    }

    /**
     * Updates either an Expenditure, Credit Card or Income entry based on the user's input.
     *
     * @throws MindMyMoneyException when an invalid command is received, along with its corresponding error message.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        if (hasExpensesFlag()) {
            updateExpenditure();
        } else if (hasCreditCardFlag()) {
            updateCreditCard();
        } else if (hasIncomeFlag()) {
            updateIncome();
        } else {
            throw new MindMyMoneyException("You are missing a flag in your command\n"
                    + "Type \"help /e\" to view the list of supported expenditure commands\n"
                    + "Type \"help /cc\" to view the list of supported Credit Card commands\n"
                    + "Type \"help /i\" to view the list of supported income commands\n");
        }
    }
}
