package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.CategoryTypes;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static seedu.mindmymoney.constants.CategoryTypes.FOOD;
import static seedu.mindmymoney.constants.CategoryTypes.UTILITIES;
import static seedu.mindmymoney.constants.CategoryTypes.TRANSPORT;
import static seedu.mindmymoney.constants.CategoryTypes.PERSONAL;
import static seedu.mindmymoney.constants.CategoryTypes.ENTERTAINMENT;
import static seedu.mindmymoney.constants.CategoryTypes.OTHERS;
import static seedu.mindmymoney.constants.ExpenditureFields.TIME;
import static seedu.mindmymoney.helper.GeneralFunctions.capitalise;
import static seedu.mindmymoney.helper.GeneralFunctions.findItemsInList;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;
import static seedu.mindmymoney.helper.GeneralFunctions.findMatchingCategoryInArraylist;

/**
 * Container for functions that help do calculations.
 */
public class Calculations {
    public static final double INTERVEL_OF_INCREMENT = 5;

    /**
     * Calculates the total expenditure in a given month.
     *
     * @param input           The month to calculate expenditure for.
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
        if (sumOfExpenditure == 0.0) {
            throw new MindMyMoneyException("Month and year not found in the list! Do check your input");
        }
        System.out.println("Total expenditure in the month of " + input + " is $" + sumOfExpenditure + ".");
        displayExpenditureBreakdown(foundItems, sumOfExpenditure);
    }

    /**
     * Displays the expenditure breakdown for the given month.
     *
     * @param foundItems The list containing all the expenses in the month.
     * @param sumOfExpenditure Total sum of expenses in the month.
     */
    public static void displayExpenditureBreakdown(ArrayList<Expenditure> foundItems, float sumOfExpenditure) {
        float foodPercentage = calculatePercentage(FOOD, foundItems, sumOfExpenditure);
        float transportPercentage = calculatePercentage(TRANSPORT, foundItems, sumOfExpenditure);
        float utilitiesPercentage = calculatePercentage(UTILITIES, foundItems, sumOfExpenditure);
        float personalPercentage = calculatePercentage(PERSONAL, foundItems, sumOfExpenditure);
        float entertainmentPercentage = calculatePercentage(ENTERTAINMENT, foundItems, sumOfExpenditure);
        float othersPercentage = calculatePercentage(OTHERS, foundItems, sumOfExpenditure);

        System.out.println(System.lineSeparator() + "BREAKDOWN OF EXPENSES:");
        System.out.print(PrintStrings.LINE);
        System.out.println("FOOD:          " + printBar(foodPercentage) + " " + foodPercentage + "%");
        System.out.println("TRANSPORT:     " + printBar(transportPercentage) + " " + transportPercentage + "%");
        System.out.println("UTILITIES:     " + printBar(utilitiesPercentage) + " " + utilitiesPercentage + "%");
        System.out.println("PERSONAL:      " + printBar(personalPercentage) + " " + personalPercentage + "%");
        System.out.println("ENTERTAINMENT: " + printBar(entertainmentPercentage) + " " + entertainmentPercentage + "%");
        System.out.println("OTHERS:        " + printBar(othersPercentage) + " " + othersPercentage + "%");
        System.out.println(PrintStrings.LINE);
    }

    /**
     * Displays the chart for each category type.
     *
     * @param percentage Percentage of each category type that is part of the expenses.
     * @return The 'bar' chart to be output for the category type.
     */
    public static String printBar(float percentage) {
        String output = "";
        for (float i = 0; i < percentage; i += INTERVEL_OF_INCREMENT) {
            output += "▇▇";
        }
        return output;
    }

    /**
     * Calculates the percentage of expenses for a particular category type.
     *
     * @param categoryType Category type to calculate for.
     * @param foundItems The list containing all the expenses in the month.
     * @param sumOfExpenditure Total sum of expenses in the month.
     * @return Percentage of expenses for that particular category type.
     */
    public static float calculatePercentage(CategoryTypes categoryType, ArrayList<Expenditure> foundItems,
                                             float sumOfExpenditure) {
        ArrayList<Expenditure> foundCategoryTypeItems = new ArrayList<>();
        foundCategoryTypeItems = findMatchingCategoryInArraylist(categoryType, foundItems, foundCategoryTypeItems);
        float sumOfCategoryType = 0;
        for (Expenditure item: foundCategoryTypeItems) {
            sumOfCategoryType += item.getAmount();
        }
        sumOfCategoryType = formatFloat(sumOfCategoryType);
        float percentage = sumOfCategoryType / sumOfExpenditure * 100;
        percentage = formatFloat(percentage);
        return percentage;
    }
}
