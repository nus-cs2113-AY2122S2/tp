package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ExpenditureFields;
import seedu.mindmymoney.constants.Indexes;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static seedu.mindmymoney.constants.ExpenditureFields.*;

/**
 * Container for general functions used throughout the program.
 */
public class GeneralFunctions {
    private static final String e = expenditure.toString();
    private static final String c = category.toString();
    private static final String d = description.toString();
    private static final String a = amount.toString();
    private static final String t = time.toString();

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
        if (!(input.contains(startingFlag) && input.contains(endingFlag))) {
            throw new MindMyMoneyException("You are missing a flag!");
        }
        startingFlag = startingFlag + " ";

        input = input.substring(input.indexOf(startingFlag) + 3);
        if (!endingFlag.equals(" ")) {
            endingFlag = " " + endingFlag;
            input = input.substring(0, input.indexOf(endingFlag));
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
            ExpenditureFields fieldToSearchAsEnumType = ExpenditureFields.valueOf(fieldToSearch.toLowerCase());
            switch (fieldToSearchAsEnumType) {
            case expenditure:
                foundItems = findMatchingExpenditure(searchTerm, foundItems, itemList);
                break;
            case category:
                foundItems = findMatchingCategory(searchTerm, foundItems, itemList);
                break;
            case description:
                foundItems = findMatchingDescription(searchTerm, foundItems, itemList);
                break;
            case amount:
                foundItems = findMatchingAmount(searchTerm, foundItems, itemList);
                break;
            case time:
                foundItems = findMatchingTime(searchTerm, foundItems, itemList);
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
}
