package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ExpenditureCategoryTypes;
import seedu.mindmymoney.constants.IncomeCategoryTypes;
import seedu.mindmymoney.constants.ValidationRegexTypes;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.userfinancial.CreditCard;

import static seedu.mindmymoney.constants.Indexes.MAX_STUDENT_INCOME;
import static seedu.mindmymoney.constants.Indexes.MIN_STUDENT_INCOME;
import static seedu.mindmymoney.constants.PaymentMethod.CASH;

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
        if (inputPaymentMethod.equals("")) {
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
        if (inputCategory.equals("")) {
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
        if (inputDescription.equals("")) {
            throw new MindMyMoneyException("Description cannot be null!");
        }
    }

    /**
     * Checks if user input of amount is a positive number more than 0.
     *
     * @param inputAmount User input of Amount.
     * @throws MindMyMoneyException when input is less than or equal to 0 or null.
     */
    public static void testExpenditureAmount(String inputAmount) throws MindMyMoneyException {
        float inputAmountAsInteger;

        if (inputAmount == null) {
            throw new MindMyMoneyException("Amount cannot be empty!");
        }

        try {
            inputAmountAsInteger = Float.parseFloat(inputAmount);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Amount must be a number");
        }

        if (inputAmountAsInteger <= 0) {
            throw new MindMyMoneyException("Amount must be more than 0");
        }
        assert inputAmountAsInteger > 0 : "Amount should have a positive value";
    }

    public static void testIncomeAmount(int inputAmount) throws MindMyMoneyException {
        if (inputAmount < MIN_STUDENT_INCOME) {
            throw new MindMyMoneyException("Amount cannot be negative!");
        } else if (inputAmount > MAX_STUDENT_INCOME) {
            throw new MindMyMoneyException("Amount too high!");
        }
    }

    public static void testIncomeCategory(String inputCategory) throws MindMyMoneyException {
        if (inputCategory.equals("")) {
            throw new MindMyMoneyException("Category cannot be null!");
        }

        if (!isIncomeCategoryInList(inputCategory)) {
            throw new MindMyMoneyException("Input Salary, Allowance, Investment or Others!");
        }
    }

    /**
     * Checks if date input format is valid.
     *
     * @param input The string of the date input.
     * @return true if format is valid, false otherwise.
     */
    public static boolean isValidInput(String input) {
        if (input.matches(ValidationRegexTypes.VALIDATION_REGEX_D)) {
            return true;
        }
        return false;

    }
}
