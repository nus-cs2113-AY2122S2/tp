package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Income;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Flags.FLAG_END_VALUE;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_AMOUNT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_LIMIT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_NAME;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CASHBACK;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CATEGORY;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_DESCRIPTION;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_INCOME;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_PAYMENT_METHOD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_TIME;
import static seedu.mindmymoney.helper.AddCommandInputTests.testExpenditureParameters;
import static seedu.mindmymoney.helper.AddCommandInputTests.testIncomeParameters;
import static seedu.mindmymoney.helper.AddCommandInputTests.testCreditCardParameters;
import static seedu.mindmymoney.helper.GeneralFunctions.capitalise;
import static seedu.mindmymoney.helper.GeneralFunctions.parseInputWithCommandFlag;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;

/**
 * Represents the Add command.
 */
public class AddCommand extends Command {
    private String addInput;
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    public IncomeList incomeList;

    public AddCommand(String addInput, User user) {
        this.addInput = addInput;
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
     * Indicates whether the add command is to add an expenditure by checking for the expenditure flag.
     *
     * @return true if the /e flag is present, false otherwise.
     */
    private boolean hasExpenditureFlag() {
        return addInput.contains(FLAG_OF_EXPENSES);
    }

    /**
     * Indicates whether the add command is to add a credit card by looking for the credit card flag.
     *
     * @return true if the credit card flag is present, false otherwise.
     */
    private boolean hasCreditCardFlag() {
        return addInput.contains(FLAG_OF_CREDIT_CARD);
    }

    /**
     * Updates the total expenditure field in the credit card specified in the expenditure item and returns
     * the balance left.
     *
     * @param cardName Name of credit card to be updated.
     * @param amount amount of new expenditure.
     * @return The credit card balance left.
     * @throws MindMyMoneyException when the card is not found in user's credit card list.
     */
    private float updateCreditCardTotalExpenditure(String cardName, float amount) throws MindMyMoneyException {
        CreditCard creditCard = creditCardList.get(cardName);
        if (creditCard == null) {
            throw new MindMyMoneyException("Invalid Card Name!");
        }
        creditCard.addExpenditure(amount);
        return creditCard.getBalanceLeft();
    }

    /**
     * Indicates whether the add command is to add an income by looking for the /i flag.
     *
     * @return true if the /i flag is present, false otherwise.
     */
    private boolean hasIncomeFlag() {
        return addInput.contains(FLAG_OF_INCOME);
    }

    /**
     * Inserts an Expenditure object into user's list of expenditure(s).
     *
     * @throws MindMyMoneyException when inputs are invalid or flags are missing.
     */
    public void addExpenditure() throws MindMyMoneyException {
        String paymentMethod = parseInputWithCommandFlag(addInput, FLAG_OF_PAYMENT_METHOD, FLAG_OF_CATEGORY).trim();
        String inputCategory = parseInputWithCommandFlag(addInput, FLAG_OF_CATEGORY, FLAG_OF_DESCRIPTION).trim();
        String description = parseInputWithCommandFlag(addInput, FLAG_OF_DESCRIPTION, FLAG_OF_AMOUNT).trim();
        String amountAsString = parseInputWithCommandFlag(addInput, FLAG_OF_AMOUNT, FLAG_OF_TIME).trim();
        String inputTime = parseInputWithCommandFlag(addInput, FLAG_OF_TIME, FLAG_END_VALUE).trim();
        testExpenditureParameters(paymentMethod, inputCategory, description, amountAsString, inputTime, creditCardList);

        if (capitalise(paymentMethod).equals("Cash")) {
            paymentMethod = capitalise(paymentMethod);
        }
        String category = capitalise(inputCategory);
        float amountAsFloat = Float.parseFloat(amountAsString);
        float amountFloat = formatFloat(amountAsFloat);
        expenditureList.add(new Expenditure(paymentMethod, category, description, amountFloat, inputTime));

        System.out.println("Successfully added: \n\n"
            + "Description: " + description + "\n"
            + "Amount: $" + String.format("%.2f", amountFloat) + "\n"
            + "Category: " + category + "\n"
            + "Payment method: " + paymentMethod + "\n"
            + "Date: " + inputTime + "\n\n"
            + "into the account\n");

        if (!paymentMethod.equals("Cash")) {
            float balanceLeft = updateCreditCardTotalExpenditure(paymentMethod, amountFloat);
            System.out.printf(paymentMethod + " has a balance of $%.2f left%n", balanceLeft);
        }
        System.out.print(System.lineSeparator());
    }

    /**
     * Inserts a CreditCard object into user's list of credit card(s).
     *
     * @throws MindMyMoneyException Exception thrown when input is invalid
     */
    public void addCreditCard() throws MindMyMoneyException {
        final String cardName = parseInputWithCommandFlag(addInput, FLAG_OF_CARD_NAME,
            FLAG_OF_CASHBACK).trim();
        final String cashBack = parseInputWithCommandFlag(addInput, FLAG_OF_CASHBACK,
            FLAG_OF_CARD_LIMIT).trim();
        final String cardLimit = parseInputWithCommandFlag(addInput, FLAG_OF_CARD_LIMIT,
            FLAG_END_VALUE).trim();
        testCreditCardParameters(cardName, cashBack, cardLimit, creditCardList);
        Float cashBackAsFloat = formatFloat(Float.parseFloat(cashBack));
        Float cardLimitAsFloat = formatFloat(Float.parseFloat(cardLimit));
        creditCardList.add(new CreditCard(cardName, cashBackAsFloat, cardLimitAsFloat));

        System.out.println("Successfully added: \n\n"
            + "Credit card: " + cardName + "\n"
            + "Cash back: " + String.format("%.2f", cashBackAsFloat) + "%\n"
            + "Card limit: $" + String.format("%.2f", cardLimitAsFloat) + "\n\n"
            + "into the account");
        System.out.print(System.lineSeparator());
    }

    /**
     * Inserts an Income object into user's list of income(s).
     *
     * @throws MindMyMoneyException when the input amount is not a number.
     */
    public void addIncome() throws MindMyMoneyException {
        String amountAsString = parseInputWithCommandFlag(addInput, FLAG_OF_AMOUNT, FLAG_OF_CATEGORY).trim();
        if (amountAsString.length() > 6) {
            throw new MindMyMoneyException("Amount too high!");
        }

        try {
            int amountAsInt = Integer.parseInt(amountAsString);
            String inputCategory = parseInputWithCommandFlag(addInput, FLAG_OF_CATEGORY, FLAG_END_VALUE).trim();
            testIncomeParameters(amountAsInt, inputCategory);
            String category = capitalise(inputCategory);

            incomeList.add(new Income(amountAsInt, category));

            System.out.print("Successfully added: \n\n"
                + "Amount: $" + amountAsInt + "\n"
                + "Category: " + category + "\n\n"
                + "into the account");
            System.out.println(System.lineSeparator());
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Income must be a whole number!");
        }
    }

    /**
     * Inserts either an Expenditure, CreditCard or Income object into the user's list based on the input.
     *
     * @throws MindMyMoneyException when an invalid command is received, along with its corresponding error message.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        if (hasExpenditureFlag()) {
            addExpenditure();
        } else if (hasCreditCardFlag()) {
            addCreditCard();
        } else if (hasIncomeFlag()) {
            addIncome();
        } else {
            throw new MindMyMoneyException("You are missing a flag in your command\n"
                + "Type \"help /e\" to view the list of supported expenditure commands\n"
                + "Type \"help /cc\" to view the list of supported Credit Card commands\n"
                + "Type \"help /i\" to view the list of supported income commands\n");
        }
    }
}
