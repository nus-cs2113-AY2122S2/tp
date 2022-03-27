package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ExpenditureFields;
import seedu.mindmymoney.constants.Indexes;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM_IN_STRING;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM_IN_STRING;

/**
 * Container for general functions used throughout the program.
 */
public class GeneralFunctions {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Separates the user input into the command and description for easy reference.
     *
     * @return String array of user input.
     */
    public static String[] parseInput(String inputCommand) {
        String[] inputAsArray = inputCommand.split(" ", Indexes.SPLIT_LIMIT);
        return inputAsArray;
    }

    /**
     * Separates the user input into the command and description for easy reference.
     *
     * @return String array of user input.
     */
    public static String parseInputWithCommandFlag(String input, String startingFlag, String endingFlag)
        throws MindMyMoneyException {
        if (!(input.contains(startingFlag + " ") && input.contains(" " + endingFlag))) {
            throw new MindMyMoneyException("You are missing a flag or lack the spacing between the flags!");
        }
        startingFlag = startingFlag + " ";
        try {
            input = input.substring(input.indexOf(startingFlag) + 3);
            if (!endingFlag.equals("")) {
                endingFlag = " " + endingFlag;
                input = input.substring(0, input.indexOf(endingFlag));
            }
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Flag not found!");
        }

        return input;
    }

    /**
     * Finds an item in a given list provided the search term and the field to search in.
     *
     * @param searchTerm    The matching term to search the list for.
     * @param fieldToSearch The object in the list to search for.
     * @param itemList      The list to search in.
     * @return An ArrayList containing the found items.
     * @throws MindMyMoneyException if fieldToSearch is not in the list, amount is not a number
     *                              and if the list of found items is empty.
     */
    public static ArrayList<Expenditure> findItemsInList(String searchTerm, String fieldToSearch,
                                                         ExpenditureList itemList) throws MindMyMoneyException {
        ArrayList<Expenditure> foundItems = new ArrayList<>();
        try {
            ExpenditureFields fieldToSearchAsEnumType = ExpenditureFields.valueOf(fieldToSearch);
            switch (fieldToSearchAsEnumType) {
            case EXPENDITURE:
                findMatchingExpenditure(searchTerm, foundItems, itemList);
                break;
            case CATEGORY:
                findMatchingCategory(searchTerm, foundItems, itemList);
                break;
            case DESCRIPTION:
                findMatchingDescription(searchTerm, foundItems, itemList);
                break;
            case AMOUNT:
                findMatchingAmount(searchTerm, foundItems, itemList);
                break;
            case TIME:
                findMatchingTime(searchTerm, foundItems, itemList);
                break;
            default:
                throw new MindMyMoneyException("Input a valid search field!");
            }
            if (itemList.size() == 0) {
                throw new MindMyMoneyException("The task \"" + searchTerm + "\" was not found in the list, sorry!");
            } else {
                return foundItems;
            }
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("AMOUNT must be a number");
        }
    }

    /**
     * Searches for matching items in Expenditure field of itemList and returns a list of found items.
     *
     * @param searchTerm String to search for.
     * @param foundItems List to store items found.
     * @param itemList   List to search in.
     * @return foundItems, updated with new items found.
     */
    public static ArrayList<Expenditure> findMatchingExpenditure(String searchTerm, ArrayList<Expenditure> foundItems,
                                                                 ExpenditureList itemList) {
        for (Expenditure item : itemList.expenditureListArray) {
            if (item.getExpenditure().contains(searchTerm)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    /**
     * Searches for matching items in Category field of itemList and returns a list of found items.
     *
     * @param searchTerm String to search for.
     * @param foundItems List to store items found.
     * @param itemList   List to search in.
     * @return foundItems, updated with new items found.
     */
    public static ArrayList<Expenditure> findMatchingCategory(String searchTerm, ArrayList<Expenditure> foundItems,
                                                              ExpenditureList itemList) {
        for (Expenditure item : itemList.expenditureListArray) {
            if (item.getCategory().contains(searchTerm)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    /**
     * Searches for matching items in Description field of itemList and returns a list of found items.
     *
     * @param searchTerm String to search for.
     * @param foundItems List to store items found.
     * @param itemList   List to search in.
     * @return foundItems, updated with new items found.
     */
    public static ArrayList<Expenditure> findMatchingDescription(String searchTerm, ArrayList<Expenditure> foundItems,
                                                                 ExpenditureList itemList) {
        for (Expenditure item : itemList.expenditureListArray) {
            if (item.getDescription().contains(searchTerm)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    /**
     * Searches for matching items in Amount field of itemList and returns a list of found items.
     *
     * @param searchTerm String to search for.
     * @param foundItems List to store items found.
     * @param itemList   List to search in.
     * @return foundItems, updated with new items found.
     */
    public static ArrayList<Expenditure> findMatchingAmount(String searchTerm, ArrayList<Expenditure> foundItems,
                                                            ExpenditureList itemList) {
        for (Expenditure item : itemList.expenditureListArray) {
            if (item.getAmount() == Float.parseFloat(searchTerm)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    /**
     * Searches for matching items in Time field of itemList and returns a list of found items.
     *
     * @param searchTerm String to search for.
     * @param foundItems List to store items found.
     * @param itemList   List to search in.
     * @return foundItems, updated with new items found.
     */
    public static ArrayList<Expenditure> findMatchingTime(String searchTerm, ArrayList<Expenditure> foundItems,
                                                          ExpenditureList itemList) {
        for (Expenditure item : itemList.expenditureListArray) {
            if (item.getTime().contains(searchTerm)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    /**
     * Sets the string to lower case and then capitalise the first character in string.
     *
     * @param str String to be capitalised.
     * @return Capitalised string.
     */
    public static String capitalise(String str) {
        str = str.toLowerCase();
        return str.substring(INDEX_OF_FIRST_ITEM_IN_STRING, INDEX_OF_SECOND_ITEM_IN_STRING).toUpperCase()
            + str.substring(INDEX_OF_SECOND_ITEM_IN_STRING);
    }

    /**
     * Round off float to 2dp.
     * @param number float to be rounded off.
     * @return float rounded off to 2dp.
     */
    public static float formatFloat(Float number) {
        return Float.parseFloat(df.format(number));
    }
}
