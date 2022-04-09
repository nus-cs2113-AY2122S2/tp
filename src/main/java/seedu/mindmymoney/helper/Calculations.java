package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ExpenditureCategoryTypes;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static seedu.mindmymoney.constants.ExpenditureCategoryTypes.FOOD;
import static seedu.mindmymoney.constants.ExpenditureCategoryTypes.UTILITIES;
import static seedu.mindmymoney.constants.ExpenditureCategoryTypes.TRANSPORT;
import static seedu.mindmymoney.constants.ExpenditureCategoryTypes.PERSONAL;
import static seedu.mindmymoney.constants.ExpenditureCategoryTypes.ENTERTAINMENT;
import static seedu.mindmymoney.constants.ExpenditureCategoryTypes.OTHERS;
import static seedu.mindmymoney.constants.ExpenditureFields.TIME;
import static seedu.mindmymoney.helper.GeneralFunctions.findItemsInList;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;
import static seedu.mindmymoney.helper.GeneralFunctions.findMatchingCategoryInArraylist;
import static seedu.mindmymoney.helper.TimeFunctions.isValidInput;

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
     * @throws MindMyMoneyException When findItemsInList throws MindMyMoneyException.
     */
    public static void calculateExpenditurePerMonth(String input, ExpenditureList expenditureList)
        throws MindMyMoneyException {
        if (!isValidInput(input)) {
            throw new MindMyMoneyException("Date has to be in \"dd/mm/yyyy\", \"mm/yyyy\" or \"yyyy\" format!");
        }
        ArrayList<Expenditure> foundItems = findItemsInList(input, TIME.toString(), expenditureList);
        float sumOfExpenditure = 0;
        for (Expenditure item : foundItems) {
            sumOfExpenditure += item.getAmount();
        }
        sumOfExpenditure = formatFloat(sumOfExpenditure);
        if (sumOfExpenditure == 0.0) {
            throw new MindMyMoneyException("Date not found in the list! Do check your input");
        }
        System.out.println("Total expenditure in " + input + " is $" + String.format("%.2f", sumOfExpenditure )+ ".");
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
        System.out.println("FOOD:          " + printOutput(foodPercentage));
        System.out.println("TRANSPORT:     " + printOutput(transportPercentage));
        System.out.println("UTILITIES:     " + printOutput(utilitiesPercentage));
        System.out.println("PERSONAL:      " + printOutput(personalPercentage));
        System.out.println("ENTERTAINMENT: " + printOutput(entertainmentPercentage));
        System.out.println("OTHERS:        " + printOutput(othersPercentage));
        System.out.println(PrintStrings.LINE);
    }

    /**
     * Prints the bar chart.
     *
     * @param percentage Percentage of each category type that is part of the expenses.
     * @return The remaining part of string to be printed.
     */
    public static String printOutput(float percentage) {
        return printBar(percentage) + " [" + percentage + "%]";
    }

    /**
     * Fills the chart for each category type.
     *
     * @param percentage Percentage of each category type that is part of the expenses.
     * @return The 'bar' chart to be output for the category type.
     */
    public static String printBar(float percentage) {
        String output = "";
        for (float i = 0; i < percentage; i += INTERVEL_OF_INCREMENT) {
            output += "$$";
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
    public static float calculatePercentage(ExpenditureCategoryTypes categoryType, ArrayList<Expenditure> foundItems,
                                            float sumOfExpenditure) {
        ArrayList<Expenditure> foundCategoryTypeItems = new ArrayList<>();
        foundCategoryTypeItems = findMatchingCategoryInArraylist(categoryType, foundItems, foundCategoryTypeItems);
        float sumOfCategoryType = 0;
        for (Expenditure item: foundCategoryTypeItems) {
            sumOfCategoryType += item.getAmount();
        }
        sumOfCategoryType = formatFloat(sumOfCategoryType);
        float percentage = (sumOfCategoryType / sumOfExpenditure) * 100;
        percentage = formatFloat(percentage);
        return percentage;
    }
}
