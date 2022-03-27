package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static seedu.mindmymoney.constants.ExpenditureFields.TIME;
import static seedu.mindmymoney.helper.GeneralFunctions.capitalise;
import static seedu.mindmymoney.helper.GeneralFunctions.findItemsInList;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;

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
        input = capitalise(input);
        ArrayList<Expenditure> foundItems = findItemsInList(input, TIME.toString(), expenditureList);
        float sumOfExpenditure = 0;
        for (Expenditure item : foundItems) {
            sumOfExpenditure += item.getAmount();
        }
        sumOfExpenditure = formatFloat(sumOfExpenditure);
        if (sumOfExpenditure == 0.0){
            throw new MindMyMoneyException("Month and year not found in the list! Do check your input");
        }
        System.out.println("Total expenditure in the month of " + input + " is " + sumOfExpenditure);
    }

}
