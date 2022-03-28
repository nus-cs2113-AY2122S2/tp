package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.CategoryTypes;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.userfinancial.CreditCard;

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
     * Checks if input is in Category Types.
     *
     * @param input the item to be checked.
     * @return true if item is in the list, false otherwise.
     */
    public static boolean isCategoryInList(String input) {
        for (CategoryTypes str : CategoryTypes.values()) {
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
            throw new MindMyMoneyException("Input Cash or a Credit Card after the /e field!");
        }
    }

    /**
     * Checks if user input of category is null or not a type of expenditure.
     *
     * @param inputCategory User input of Category.
     * @throws MindMyMoneyException when input is null or not a type of category.
     */
    public static void testCategory(String inputCategory) throws MindMyMoneyException {
        if (inputCategory.equals("")) {
            throw new MindMyMoneyException("Category cannot be null!");
        }

        if (!isCategoryInList(inputCategory)) {
            throw new MindMyMoneyException("Input Food, Transport, Utilities, Personal, Entertainment or Others after" +
                    " the /c field!");
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
    public static void testAmount(String inputAmount) throws MindMyMoneyException {
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
}
