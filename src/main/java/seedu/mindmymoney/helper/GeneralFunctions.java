package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ExpenditureCategoryTypes;
import seedu.mindmymoney.constants.ExpenditureFields;
import seedu.mindmymoney.constants.Indexes;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;

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
     * Checks if user's input contains the correct flag formats.
     *
     * @param input        user's input.
     * @param startingFlag the flag before the interested parameter.
     * @param endingFlag   the flag after the interested parameter.
     * @return true if the input contains the correct flag formats, false otherwise.
     */
    public static boolean hasCorrectFlagFormat(String input, String startingFlag, String endingFlag) {
        if (input.contains(startingFlag + " ") && input.contains(" " + endingFlag)) {
            return true;
        }
        return false;
    }

    /**
     * Extracts out the interested parameter from the user's input based on the given flags.
     * For example, if user's input was: "add /i /a 3000 /c salary", this method extracts out "3000" or "salary"
     * based on the flags given.
     *
     * @param input        user's input.
     * @param startingFlag the flag before the interested parameter.
     * @param endingFlag   the flag after the interested parameter.
     * @return the interested parameter.
     * @throws MindMyMoneyException when an invalid command is received, along with the corresponding error message.
     */
    public static String parseInputWithCommandFlag(String input, String startingFlag, String endingFlag)
        throws MindMyMoneyException {
        try {
            if (!hasCorrectFlagFormat(input, startingFlag, endingFlag)) {
                throw new MindMyMoneyException("You are missing a flag or lack the spacing between the flags!\n"
                    + "For eg. \"add /e /pm cash /c Food /d Porridge /a 4.50 /t 30/03/2022\"");
            }

            startingFlag = startingFlag + " ";
            input = input.substring(input.indexOf(startingFlag) + startingFlag.length());
            if (!endingFlag.equals("")) {
                endingFlag = " " + endingFlag;
                input = input.substring(0, input.indexOf(endingFlag));
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new MindMyMoneyException("You are missing one or more of the parameters! Please check your command "
                + "again.\n");
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
                throw new MindMyMoneyException("Search term that have yet to be implemented! "
                    + "Look out for our future updates");
            }
            if (foundItems.size() == 0) {
                throw new MindMyMoneyException("The item \"" + searchTerm + "\" was not found in the list, sorry!");
            } else {
                return foundItems;
            }
        } catch (IllegalArgumentException e) {
            throw new MindMyMoneyException("Input a valid search term!");
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
            if (item.getPaymentMethod().contains(searchTerm)) {
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
     * Searches for matching items in Category field from an arraylist and returns a list of found items.
     *
     * @param categoryType          Category type to search for
     * @param foundItems            List of expense items.
     * @param foundCategoryTypeList List to store items found.
     * @return The list that stores the expense items found.
     */
    public static ArrayList<Expenditure> findMatchingCategoryInArraylist(ExpenditureCategoryTypes categoryType,
                                                                         ArrayList<Expenditure> foundItems,
                                                                         ArrayList<Expenditure> foundCategoryTypeList) {
        for (Expenditure item : foundItems) {
            if (item.getCategory().contains(capitalise(categoryType.toString()))) {
                foundCategoryTypeList.add(item);
            }
        }
        return foundCategoryTypeList;
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
                                                            ExpenditureList itemList) throws MindMyMoneyException {
        try {
            for (Expenditure item : itemList.expenditureListArray) {
                if (item.getAmount() == Float.parseFloat(searchTerm)) {
                    foundItems.add(item);
                }
            }
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("AMOUNT must be a number");
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
        return str.substring(INDEX_OF_FIRST_ITEM, INDEX_OF_SECOND_ITEM).toUpperCase()
            + str.substring(INDEX_OF_SECOND_ITEM);
    }

    /**
     * Round off float to 2dp.
     *
     * @param number float to be rounded off.
     * @return float rounded off to 2dp.
     */
    public static float formatFloat(Float number) {
        return Float.parseFloat(df.format(number));
    }
}
