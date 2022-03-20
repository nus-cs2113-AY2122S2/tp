package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.CategoryTypes;
import seedu.mindmymoney.constants.ExpenditureTypes;

public class AddCommandInputTests {
    public static boolean isExpenditureInList(String input) {
        for (ExpenditureTypes str : ExpenditureTypes.values()) {
            if (str.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCategoryInList(String input) {
        for (CategoryTypes str : CategoryTypes.values()) {
            if (str.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static void testExpenditure(String inputExpenditure) throws MindMyMoneyException {
        if (inputExpenditure == null) {
            throw new MindMyMoneyException("Expenditure cannot be null!");
        }

        if (!isExpenditureInList(inputExpenditure)) {
            throw new MindMyMoneyException("Input Cash or a Credit Card!");
        }
    }

    public static void testCategory(String inputCategory) throws MindMyMoneyException {
        if (inputCategory == null) {
            throw new MindMyMoneyException("Category cannot be null!");
        }

        if (!isCategoryInList(inputCategory)) {
            throw new MindMyMoneyException("Input Food, Transport, Utilities, Personal, Entertainment or Others!");
        }
    }

    public static void testDescription(String inputDescription) throws MindMyMoneyException {
        if (inputDescription == null) {
            throw new MindMyMoneyException("Description cannot be null!");
        }
    }

    public static void testAmount(String inputAmount) throws MindMyMoneyException {
        int inputAmountAsInteger = 0;

        if (inputAmount == null) {
            throw new MindMyMoneyException("Amount cannot be empty!");
        }

        try {
            inputAmountAsInteger = Integer.parseInt(inputAmount);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Amount must be a number");
        }

        if(inputAmountAsInteger <= 0) {
            throw new MindMyMoneyException("Amount must be more than 0");
        }
        assert inputAmountAsInteger >= 0 : "Amount should have a positive value";
    }
}
