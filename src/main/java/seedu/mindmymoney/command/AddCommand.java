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
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_THIRD_ITEM;
import static seedu.mindmymoney.helper.AddCommandInputTests.isValidInput;
import static seedu.mindmymoney.helper.AddCommandInputTests.testCashbackAmount;
import static seedu.mindmymoney.helper.AddCommandInputTests.testCreditCardLimit;
import static seedu.mindmymoney.helper.AddCommandInputTests.testCreditCardName;
import static seedu.mindmymoney.helper.AddCommandInputTests.testDescription;
import static seedu.mindmymoney.helper.AddCommandInputTests.testExpenditureAmount;
import static seedu.mindmymoney.helper.AddCommandInputTests.testExpenditureCategory;
import static seedu.mindmymoney.helper.AddCommandInputTests.testIncomeAmount;
import static seedu.mindmymoney.helper.AddCommandInputTests.testIncomeCategory;
import static seedu.mindmymoney.helper.AddCommandInputTests.testPaymentMethod;
import static seedu.mindmymoney.helper.GeneralFunctions.capitalise;
import static seedu.mindmymoney.helper.GeneralFunctions.parseInputWithCommandFlag;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Add command.
 */
public class AddCommand extends Command {
    private String addInput;
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    public IncomeList incomeList;
    private static final int LEAP_YEAR_NUMBER = 4;

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
     * @param amount   amount of new expenditure.
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
     * Tests if the input parameters of expenditure from the user are valid.
     *
     * @param paymentMethod  The payment method used, either as cash or the credit card.
     * @param inputCategory  The category as indicated by the user.
     * @param description    The description of the expenditure.
     * @param amountAsString Price of the expenditure.
     * @param inputTime      Date of when the expenditure was bought.
     * @throws MindMyMoneyException when the parameters are invalid.
     */
    public void testExpenditureParameters(String paymentMethod, String inputCategory, String description,
                                          String amountAsString, String inputTime) throws MindMyMoneyException {
        testPaymentMethod(paymentMethod, creditCardList);
        testExpenditureCategory(inputCategory);
        testDescription(description);
        testExpenditureAmount(amountAsString, paymentMethod, creditCardList);
        checkValidDate(inputTime);
    }

    /**
     * Tests if the input parameters of credit card from the user are valid.
     *
     * @param cardName  The name of the credit card.
     * @param cashBack  The amount of cashback the card provides.
     * @param cardLimit The spending limit of the credit card.
     * @throws MindMyMoneyException when the parameters are invalid.
     */
    public void testCreditCardParameters(String cardName, String cashBack, String cardLimit)
        throws MindMyMoneyException {
        testCreditCardName(cardName);
        testCashbackAmount(cashBack);
        testCreditCardLimit(cardLimit);
    }

    /**
     * Inserts an Expenditure object into user's list of expenditure(s).
     *
     * @throws MindMyMoneyException when inputs are invalid or flags are missing.
     */
    public void addExpenditure() throws MindMyMoneyException {
        String paymentMethod = parseInputWithCommandFlag(addInput, FLAG_OF_PAYMENT_METHOD, FLAG_OF_CATEGORY);
        String inputCategory = parseInputWithCommandFlag(addInput, FLAG_OF_CATEGORY, FLAG_OF_DESCRIPTION);
        String description = parseInputWithCommandFlag(addInput, FLAG_OF_DESCRIPTION, FLAG_OF_AMOUNT);
        String amountAsString = parseInputWithCommandFlag(addInput, FLAG_OF_AMOUNT, FLAG_OF_TIME);
        String inputTime = parseInputWithCommandFlag(addInput, FLAG_OF_TIME, FLAG_END_VALUE);

        testExpenditureParameters(paymentMethod, inputCategory, description, amountAsString, inputTime);

        if (capitalise(paymentMethod).equals("Cash")) {
            paymentMethod = capitalise(paymentMethod);
        }
        String category = capitalise(inputCategory);
        float amountAsFloat = Float.parseFloat(amountAsString);
        float amountFloat = formatFloat(amountAsFloat);
        LocalDate date = LocalDate.parse(inputTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        checkAfterCurrentDate(date);
        String time = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        expenditureList.add(new Expenditure(paymentMethod, category, description, amountFloat, time));

        System.out.println("Successfully added: \n\n"
            + "Description: " + description + "\n"
            + "Amount: $" + String.format("%.2f", amountFloat) + "\n"
            + "Category: " + category + "\n"
            + "Payment method: " + paymentMethod + "\n"
            + "Date: " + time + "\n\n"
            + "into the account\n");

        if (!paymentMethod.equals("Cash")) {
            float balanceLeft = updateCreditCardTotalExpenditure(paymentMethod, amountFloat);
            System.out.printf(paymentMethod + " has a balance of $%.2f left%n", balanceLeft);
        }
        System.out.print(System.lineSeparator());
    }

    /**
     * Checks if parsed date is after the current date.
     *
     * @param date date that is parsed in.
     * @throws MindMyMoneyException throws an exception when the date parsed is after current date.
     */
    public static void checkAfterCurrentDate(LocalDate date) throws MindMyMoneyException {
        LocalDate currentDate = LocalDate.now();
        if (date.isAfter(currentDate)) {
            throw new MindMyMoneyException("Please enter a valid date that is before today or today's date itself.");
        }
    }

    /**
     * Checks is parsed date is a valid date in the calendar.
     *
     * @param inputTime date that is parsed in.
     * @throws MindMyMoneyException throws an exception when the date parsed is in not in the calendar.
     */
    public static void checkValidDate(String inputTime) throws MindMyMoneyException {
        String[] date = inputTime.split("/");
        String day = date[INDEX_OF_FIRST_ITEM];
        int dayInInt = Integer.parseInt(day);
        String month = date[INDEX_OF_SECOND_ITEM];
        String year = date[INDEX_OF_THIRD_ITEM];
        int yearInInt = Integer.parseInt(year);
        if (!isValidInput(inputTime)) {
            throw new MindMyMoneyException("Date has to be valid and in this format \"dd/mm/yyyy\"");
        } else if (!(yearInInt % LEAP_YEAR_NUMBER == 0) && month.equals("02") && (dayInInt > 28)) {
            throw new MindMyMoneyException(day + "/" + month + " is not a valid dd/mm in a non leap year!");
        } else if ((yearInInt % LEAP_YEAR_NUMBER == 0) && month.equals("02") && (dayInInt > 29)) {
            throw new MindMyMoneyException(day + "/" + month + " is not a valid dd/mm in a leap year!");
        } else if ((month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11"))
            && dayInInt > 30) {
            throw new MindMyMoneyException(day + "/" + month + " is not a valid dd/mm in this month!");
        }
    }

    /**
     * Inserts a CreditCard object into user's list of credit card(s).
     *
     * @throws MindMyMoneyException Exception thrown when input is invalid
     */
    public void addCreditCard() throws MindMyMoneyException {
        final String cardName = parseInputWithCommandFlag(addInput, FLAG_OF_CARD_NAME,
            FLAG_OF_CASHBACK);
        final String cashBack = parseInputWithCommandFlag(addInput, FLAG_OF_CASHBACK,
            FLAG_OF_CARD_LIMIT);
        final String cardLimit = parseInputWithCommandFlag(addInput, FLAG_OF_CARD_LIMIT,
            FLAG_END_VALUE);
        testCreditCardParameters(cardName, cashBack, cardLimit);

        creditCardList.add(new CreditCard(cardName, Double.parseDouble(cashBack), Float.parseFloat(cardLimit)));

        System.out.println("Successfully added: \n\n"
            + "Credit card: " + cardName + "\n"
            + "Cash back: " + cashBack + "%\n"
            + "Card limit: $" + cardLimit + "\n\n"
            + "into the account");
        System.out.print(System.lineSeparator());
    }

    /**
     * Inserts an Income object into user's list of income(s).
     *
     * @throws MindMyMoneyException when the input amount is not a number.
     */
    public void addIncome() throws MindMyMoneyException {
        String amountAsString = parseInputWithCommandFlag(addInput, FLAG_OF_AMOUNT, FLAG_OF_CATEGORY);

        try {
            int amountAsInt = Integer.parseInt(amountAsString);
            testIncomeAmount(amountAsInt);

            String inputCategory = parseInputWithCommandFlag(addInput, FLAG_OF_CATEGORY, FLAG_END_VALUE);
            testIncomeCategory(inputCategory);
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
