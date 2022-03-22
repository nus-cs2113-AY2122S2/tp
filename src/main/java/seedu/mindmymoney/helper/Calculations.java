package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static seedu.mindmymoney.constants.ExpenditureFields.time;
import static seedu.mindmymoney.helper.GeneralFunctions.findItemsInList;

/**
 * Container for functions that help do calculations.
 */
public class Calculations {

    /**
     * Calculates the total expenditure in a given month.
     *
     * @param input The month to calculate expenditure for.
     * @param expenditureList The list containing all expenditures to search for.
     * @throws MindMyMoneyException when findItemsInList throws MindMyMoneyException.
     */
    public static void calculateExpenditurePerMonth(String input, ExpenditureList expenditureList)
            throws MindMyMoneyException {
        ArrayList<Expenditure> foundItems = findItemsInList(input, time.toString(), expenditureList);
        int sumOfExpenditure = 0;
        for (Expenditure item : foundItems) {
            sumOfExpenditure += item.getAmount();
        }
        System.out.println("Total expenditure in the month of " + input + " is " + sumOfExpenditure);
    }

}
