package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static seedu.mindmymoney.helper.GeneralFunctions.findItemsInList;

public class Calculations {
    public static void calculateExpenditurePerMonth(String input, ExpenditureList expenditureList)
            throws MindMyMoneyException {
        ArrayList<Expenditure> foundItems = findItemsInList(input, "time", expenditureList);
        int sumOfExpenditure = 0;
        for (Expenditure item : foundItems) {
            sumOfExpenditure += item.getAmount();
        }
        System.out.println("Total expenditure in the month of " + input + " is " + sumOfExpenditure);
    }

}
