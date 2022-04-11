package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ExpenditureCategoryTypes;
import seedu.mindmymoney.constants.IncomeCategoryTypes;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.CreditCard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static seedu.mindmymoney.constants.Indexes.MAX_CASHBACK_AMOUNT;
import static seedu.mindmymoney.constants.Indexes.MAX_CREDIT_CARD_LIMIT;
import static seedu.mindmymoney.constants.Indexes.MAX_EXPENDITURE_AMOUNT;
import static seedu.mindmymoney.constants.Indexes.MAX_STUDENT_INCOME;
import static seedu.mindmymoney.constants.Indexes.MIN_CASHBACK_AMOUNT;
import static seedu.mindmymoney.constants.Indexes.MIN_EXPENDITURE_AMOUNT;
import static seedu.mindmymoney.constants.Indexes.MIN_STUDENT_INCOME;
import static seedu.mindmymoney.constants.PaymentMethod.CASH;
import static seedu.mindmymoney.helper.TimeFunctions.checkAfterCurrentDate;
import static seedu.mindmymoney.helper.TimeFunctions.checkValidDate;

/**
 * Input validation for Add Command.
 */
public class AddCommandInputTests {

    /**
     * Checks if input is cash or a name of credit card.
     *
     * @param input the item to be checked.
     * @return true if item is in the list, false otherwise.
     */
    public static boolean isExpenditureInList(String input, CreditCardList creditCardList) {
        if (input.equalsIgnoreCase(CASH.toString())) {
            return true;
        }
        for (CreditCard str : creditCardList.creditCardListArray) {
            if (str.getNameOfCard().equals(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if input is in Expenditure Category Types.
     *
     * @param input the item to be checked.
     * @return true if item is in the list, false otherwise.
     */
    public static boolean isExpenditureCategoryInList(String input) {
        for (ExpenditureCategoryTypes str : ExpenditureCategoryTypes.values()) {
            if (str.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if input is in Income Category Types.
     *
     * @param input the item to be checked.
     * @return true if item is in the list, false otherwise.
     */
    public static boolean isIncomeCategoryInList(String input) {
        for (IncomeCategoryTypes str : IncomeCategoryTypes.values()) {
            if (str.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if user input of expenditure is null or not a type of expenditure.
     *
     * @param inputPaymentMethod User input of Expenditure.
     * @throws MindMyMoneyException when input is null or not a type of expenditure.
     */
    public static void testPaymentMethod(String inputPaymentMethod, CreditCardList creditCardList)
        throws MindMyMoneyException {
        if (inputPaymentMethod.trim().equals("")) {
            throw new MindMyMoneyException("Expenditure cannot be null!");
        }

        if (!isExpenditureInList(inputPaymentMethod, creditCardList)) {
            throw new MindMyMoneyException("Input Cash or a Credit Card after the /pm field!");
        }
    }

    /**
     * Checks if user input of category is null or not a type of expenditure.
     *
     * @param inputCategory User input of Category.
     * @throws MindMyMoneyException when input is null or not a type of category.
     */
    public static void testExpenditureCategory(String inputCategory) throws MindMyMoneyException {
        if (inputCategory.trim().equals("")) {
            throw new MindMyMoneyException("Category cannot be null!");
        }
        if (!isExpenditureCategoryInList(inputCategory)) {
            throw new MindMyMoneyException("Input Food, Transport, Utilities, Personal, Entertainment or Others after"
                + " the /c field!");
        }
    }

    /**
     * Checks if user input of category is null.
     *
     * @param inputDescription User input of Description.
     * @throws MindMyMoneyException when input is null.
     */
    public static void testDescription(String inputDescription) throws MindMyMoneyException {
        if (inputDescription.trim().equals("")) {
            throw new MindMyMoneyException("Description cannot be null!");
        }
    }

    /**
     * Checks if the expenditure amount is above the credit card limit or balance.
     *
     * @param inputAmountAsFloat The expenditure amount.
     * @param paymentMethod Either as cash or as a credit card.
     * @param creditCardList User's current list of credit cards.
     * @return true if expenditure amount is over the card limit or balance, false otherwise.
     */
    public static boolean isOverLimit(Float inputAmountAsFloat, String paymentMethod, CreditCardList creditCardList) {
        if (paymentMethod.equalsIgnoreCase("cash")) {
            return false;
        }

        CreditCard creditcard = creditCardList.get(paymentMethod);
        float balanceLeft = creditcard.getBalanceLeft();

        if (inputAmountAsFloat > balanceLeft) {
            return true;
        }
        return false;
    }

    /**
     * Checks if user input of amount is a positive number more than 0.
     *
     * @param inputAmount User input of Amount.
     * @param paymentMethod User's payment method.
     * @param creditCardList User's current list of credit cards
     * @throws MindMyMoneyException when input is less than or equal to 0 or null.
     */
    public static void testExpenditureAmount(String inputAmount, String paymentMethod,
                                             CreditCardList creditCardList) throws MindMyMoneyException {
        float inputAmountAsFloat;

        if (inputAmount == null) {
            throw new MindMyMoneyException("Amount cannot be empty!");
        }

        try {
            inputAmountAsFloat = Float.parseFloat(inputAmount);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Amount must be a number");
        }

        if (isOverLimit(inputAmountAsFloat, paymentMethod, creditCardList)) {
            throw new MindMyMoneyException("You have exceeded your credit card limit!");
        }

        if (inputAmountAsFloat <= MIN_EXPENDITURE_AMOUNT) {
            throw new MindMyMoneyException("Amount must be more than 0");
        }
        if (inputAmountAsFloat > MAX_EXPENDITURE_AMOUNT) {
            throw new MindMyMoneyException("Expenditure cannot be more than $1 million!");
        }
        assert inputAmountAsFloat > 0 : "Amount should have a positive value";
    }

    public static void testIncomeAmount(int inputAmount) throws MindMyMoneyException {
        if (inputAmount < MIN_STUDENT_INCOME) {
            throw new MindMyMoneyException("Amount cannot be negative!");
        } else if (inputAmount > MAX_STUDENT_INCOME) {
            throw new MindMyMoneyException("Amount too high!");
        }
    }

    public static void testIncomeCategory(String inputCategory) throws MindMyMoneyException {
        if (inputCategory.trim().equals("")) {
            throw new MindMyMoneyException("Category cannot be null!");
        }

        if (!isIncomeCategoryInList(inputCategory)) {
            throw new MindMyMoneyException("Input Salary, Allowance, Investment or Others!");
        }
    }

    public static void testUpdateIncomeParameters(int inputAmount, String inputCategory) throws MindMyMoneyException {
        testIncomeAmount(inputAmount);
        testIncomeCategory(inputCategory);
    }

    /**
     * Checks if user input of credit card name is valid.
     * Credit Card name that is empty, as "Cash" and that already exist in the list are not accepted.
     *
     * @throws MindMyMoneyException when Credit Card name is cash or has a
     */
    public static void testCreditCardName(String inputCreditCardName, CreditCardList creditCardList)
        throws MindMyMoneyException {
        if (inputCreditCardName.equalsIgnoreCase("")) {
            throw new MindMyMoneyException("Credit card name cannot be empty!");
        }
        assert inputCreditCardName != null : "Credit Card name should not be empty.";

        if (inputCreditCardName.equalsIgnoreCase("cash")) {
            throw new MindMyMoneyException("Credit card name cannot be abbreviated as `Cash`.");
        }
        for (CreditCard creditCard : creditCardList.creditCardListArray) {
            if (creditCard.getNameOfCard().toLowerCase().equalsIgnoreCase(inputCreditCardName)) {
                throw new MindMyMoneyException("You already have this card in the list! "
                    + "Please abbreviate the card as a different name.");
            }
        }

    }

    /**
     * Checks if user input of cashback is a positive number more than 0.
     * Cashback also cannot be more than 100%.
     *
     * @param inputCashback User input of Cash back.
     * @throws MindMyMoneyException when input cashback is less than 0 or null.
     */
    public static void testCashbackAmount(String inputCashback) throws MindMyMoneyException {
        double inputAmountAsDouble;
        if (inputCashback == null) {
            throw new MindMyMoneyException("Cashback cannot be empty!");
        }

        try {
            inputAmountAsDouble = Double.parseDouble(inputCashback);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Cashback must be a number");
        }

        if (inputAmountAsDouble < MIN_CASHBACK_AMOUNT) {
            throw new MindMyMoneyException("Cashback must be more than or equals to 0");
        } else if (inputAmountAsDouble >= MAX_CASHBACK_AMOUNT) {
            throw new MindMyMoneyException("Cashback cannot be 100% or more!");
        }
        assert inputAmountAsDouble >= MIN_CASHBACK_AMOUNT : "Cashback should have a non-negative value";
    }

    /**
     * Checks if user input for credit card limit is valid.
     *
     * @param inputLimit User input of credit card limit.
     * @throws MindMyMoneyException when input credit card limit is less than or equal to 0 or null.
     */
    public static void testCreditCardLimit(String inputLimit) throws MindMyMoneyException {
        float inputAmountAsDouble;
        if (inputLimit == null) {
            throw new MindMyMoneyException("Limit amount cannot be empty!");
        }

        try {
            inputAmountAsDouble = Float.parseFloat(inputLimit);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Limit amount must be a number");
        }

        if (inputAmountAsDouble <= 0) {
            throw new MindMyMoneyException("Limit amount must be more than 0");
        }
        assert inputAmountAsDouble > 0 : "Limit amount should have a positive value";

        if (inputAmountAsDouble > MAX_CREDIT_CARD_LIMIT) {
            throw new MindMyMoneyException("Limit amount must be $40,000 or less.\n"
                + "If you do have a credit card with more than $40,000 limit, "
                + "do inform the MindMyMoney team through GitHub.");
        }

    }

    /**
     * Tests if the input parameters of expenditure from the user are valid.
     *
     * @param paymentMethod The payment method used, either as cash or the credit card.
     * @param inputCategory The category as indicated by the user.
     * @param description The description of the expenditure.
     * @param amountAsString Price of the expenditure.
     * @param inputTime Date of when the expenditure was bought.
     * @throws MindMyMoneyException when the parameters are invalid.
     */
    public static void testExpenditureParameters(String paymentMethod, String inputCategory, String description,
                                                 String amountAsString, String inputTime,
                                                 CreditCardList creditCardList) throws MindMyMoneyException {
        testPaymentMethod(paymentMethod, creditCardList);
        testExpenditureCategory(inputCategory);
        testDescription(description);
        testExpenditureAmount(amountAsString, paymentMethod, creditCardList);
        checkValidDate(inputTime);
        LocalDate date = LocalDate.parse(inputTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        checkAfterCurrentDate(date);
    }

    /**
     * Tests if the input parameters of update expenditure from the user are valid.
     *
     * @param newPaymentMethod The payment method used, either as cash or the credit card.
     * @param inputCategory The category as indicated by the user.
     * @param description The description of the expenditure.
     * @param amountAsString Price of the expenditure.
     * @param inputTime Date of when the expenditure was bought.
     * @throws MindMyMoneyException when the parameters are invalid.
     */
    public static void testUpdateExpenditureParameters(int indexToUpdate, String newPaymentMethod, String inputCategory,
                                                       String description, String amountAsString, String inputTime,
                                                       CreditCardList creditCardList, ExpenditureList expenditureList)
        throws MindMyMoneyException {
        testPaymentMethod(newPaymentMethod, creditCardList);
        testExpenditureCategory(inputCategory);
        testDescription(description);
        checkValidDate(inputTime);
        LocalDate date = LocalDate.parse(inputTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        checkAfterCurrentDate(date);

        //Test updated expenditure amount
        String oldPaymentMethod = getOldPaymentMethod(indexToUpdate, expenditureList);
        if (isSamePaymentMethod(oldPaymentMethod, newPaymentMethod)
            && !newPaymentMethod.equalsIgnoreCase("cash")) {
            testSameCreditCardExpenditure(indexToUpdate, amountAsString, expenditureList, creditCardList,
                newPaymentMethod);
        } else {
            testExpenditureAmount(amountAsString, newPaymentMethod, creditCardList);
        }
    }

    private static String getOldPaymentMethod(int indexToUpdate, ExpenditureList expenditureList) {
        return expenditureList.get(indexToUpdate).getPaymentMethod();
    }

    private static boolean isSamePaymentMethod(String oldPaymentMethod, String newPaymentMethod) {
        return oldPaymentMethod.equalsIgnoreCase(newPaymentMethod);
    }

    private static void testSameCreditCardExpenditure(int indexToUpdate, String inputAmount,
                                                      ExpenditureList expenditureList, CreditCardList creditCardList,
                                                      String paymentMethod)
        throws MindMyMoneyException {

        float inputAmountAsFloat;
        if (inputAmount == null) {
            throw new MindMyMoneyException("Amount cannot be empty!");
        }
        try {
            inputAmountAsFloat = Float.parseFloat(inputAmount);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Amount must be a number");
        }

        CreditCard creditCard = creditCardList.get(paymentMethod);
        float oldExpenditureAmount = expenditureList.get(indexToUpdate).getAmount();
        float newTotalExpenditure = creditCard.getTotalExpenditure() - oldExpenditureAmount
            + inputAmountAsFloat;
        boolean isOverLimit = creditCard.getMonthlyCardLimit() < newTotalExpenditure;

        if (isOverLimit) {
            throw new MindMyMoneyException("You have exceeded your credit card limit!");
        }

        if (inputAmountAsFloat <= MIN_EXPENDITURE_AMOUNT) {
            throw new MindMyMoneyException("Amount must be more than 0");
        }
        assert inputAmountAsFloat > 0 : "Amount should have a positive value";
    }

    /**
     * Tests if the input parameters of income from the user are valid.
     *
     * @param amountAsInt Integer amount of the income.
     * @param inputCategory Source of income.
     * @throws MindMyMoneyException when the parameters are invalid.
     */
    public static void testIncomeParameters(int amountAsInt, String inputCategory) throws MindMyMoneyException {
        testIncomeAmount(amountAsInt);
        testIncomeCategory(inputCategory);
    }

    /**
     * Tests if the input parameters of credit card from the user are valid.
     *
     * @param cardName The name of the credit card.
     * @param cashBack The amount of cashback the card provides.
     * @param cardLimit The spending limit of the credit card.
     * @throws MindMyMoneyException when the parameters are invalid.
     */
    public static void testCreditCardParameters(String cardName, String cashBack, String cardLimit,
                                                CreditCardList creditCardList) throws MindMyMoneyException {
        testCreditCardName(cardName, creditCardList);
        testCashbackAmount(cashBack);
        testCreditCardLimit(cardLimit);
    }

}
