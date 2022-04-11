package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.ValidationException;

import java.util.HashSet;

/** Class for helper functions in validating the save file. */
public class ValidatorFunctions {

    /**
     * Checks if value is above lowerBound, or if inclusive is true, if it is equal. Throws
     * a ValidationException if this condition is not met.
     * @param value The value to check.
     * @param lowerBound The lower bound to validate against.
     * @param inclusive Whether or not to accept value if it is equal to lowerBound.
     * @param label A label to use in the error message.
     * @throws ValidationException if the value does not satisfy the lower bound.
     */
    public static void validateLowerBound(int value, int lowerBound, boolean inclusive, String label)
            throws ValidationException {
        if (value > lowerBound) {
            return;
        }
        if (value == lowerBound && inclusive) {
            return;
        }
        String comparatorInMessage = (inclusive ? ">=" : ">");
        throw new ValidationException("Values for " + label + " should be " + comparatorInMessage
            + Integer.toString(lowerBound));
    }

    /**
     * Checks if value is between lower and upper, inclusive. Throws a ValidationException
     * if this condition is not met.
     * @param value The value to check.
     * @param lower The lower bound.
     * @param upper The upper bound.
     * @param label A label to use in the error message.
     * @throws ValidationException if the value is not in range.
     */
    public static void validateInRange(double value, double lower, double upper, String label)
            throws ValidationException {
        if (lower <= value && value <= upper) {
            return;
        }
        throw new ValidationException("Values for " + label + " should be between "
            + Double.toString(lower) + " and " + Double.toString(upper));
    }

    /**
     * Checks if category is a valid category for Incomes. Throws a ValidationException
     * if this condition is not met.
     * @param category The category to check.
     * @throws ValidationException if the category is invalid.
     */
    public static void validateIncomeCategory(String category) throws ValidationException {
        if (AddCommandInputTests.isIncomeCategoryInList(category)) {
            return;
        }
        throw new ValidationException(category + " is an invalid income category");
    }

    /**
     * Checks if category is a valid category for Expenditures. Throws a ValidationException
     * if this condition is not met.
     * @param category The category to check.
     * @throws ValidationException if the category is invalid.
     */
    public static void validateExpenditureCategory(String category) throws ValidationException {
        if (AddCommandInputTests.isExpenditureCategoryInList(category)) {
            return;
        }
        throw new ValidationException(category + " is an invalid expenditure category");
    }

    /**
     * Check if time is a valid date for Expenditures. Throws a ValidationException if
     * this condition is not met.
     * @param time The time to check.
     * @throws ValidationException if the date is invalid.
     */
    public static void validateDate(String time) throws ValidationException {
        try {
            TimeFunctions.checkValidDate(time);
        } catch (MindMyMoneyException e) {
            throw new ValidationException(time + " is an invalid date:" + e.getMessage());
        }
    }

    /**
     * Checks if all payment methods in an ExpenditureList is either Cash, or can be found
     * in a CreditCardList. Throws a ValidationException if this condition is not met.
     * @param expenditures The ExpenditureList to validate.
     * @param creditCards The CreditCardList to validate against.
     * @throws ValidationException if a payment method is invalid.
     */
    public static void validatePaymentMethods(ExpenditureList expenditures,
                                                            CreditCardList creditCards) throws ValidationException {
        HashSet<String> creditCardNames = new HashSet<>();
        for (CreditCard creditCard : creditCards.creditCardListArray) {
            creditCardNames.add(creditCard.getNameOfCard());
        }
        for (Expenditure expenditure : expenditures.expenditureListArray) {
            if (expenditure.getPaymentMethod().equals("Cash")) {
                continue;
            }
            if (!creditCardNames.contains(expenditure.getPaymentMethod())) {
                throw new ValidationException(expenditure.getPaymentMethod()
                    + " does not appear as a credit card");
            }
        }
    }

    /**
     * Checks if no two credit cards in a CreditCardList have the same name. Throws a ValidationException if this
     * condition is not met.
     * @param creditCards The CreditCardList to validate
     * @throws ValidationException if a name repeats.
     */
    public static void validateCreditCardNames(CreditCardList creditCards) throws ValidationException {
        HashSet<String> creditCardNames = new HashSet<>();
        for (CreditCard creditCard : creditCards.creditCardListArray) {
            String name = creditCard.getNameOfCard();
            if (creditCardNames.contains(name)) {
                throw new ValidationException(name + " appears twice in credit card list");
            }
            creditCardNames.add(name);
        }
    }
}
