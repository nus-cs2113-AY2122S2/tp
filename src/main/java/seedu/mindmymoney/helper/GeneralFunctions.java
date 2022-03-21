package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.Indexes;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

/**
 * Container for general functions used throughout the program.
 */
public class GeneralFunctions {
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
     * @param searchTerm The matching term to search the list for.
     * @param fieldToSearch The object in the list to search for.
     * @param itemList The list to search in.
     * @return An ArrayList containing the found items.
     * @throws MindMyMoneyException if fieldToSearch is not in the list, amount is not a number
     and if the list of found items is empty.
     */
    public static ArrayList<Expenditure> findItemsInList(String searchTerm, String fieldToSearch,
                                                         ExpenditureList itemList) throws MindMyMoneyException {
        ArrayList<Expenditure> foundItems = new ArrayList<>();
        try {
            switch (fieldToSearch.toLowerCase()) {
            case "expenditure":
                for (Expenditure item : itemList.expenditureListArray) {
                    if (item.getExpenditure().contains(searchTerm)) {
                        foundItems.add(item);
                    }
                }
                break;
            case "category":
                for (Expenditure item : itemList.expenditureListArray) {
                    if (item.getCategory().contains(searchTerm)) {
                        foundItems.add(item);
                    }
                }
                break;
            case "description":
                for (Expenditure item : itemList.expenditureListArray) {
                    if (item.getDescription().contains(searchTerm)) {
                        foundItems.add(item);
                    }
                }
                break;
            case "amount":
                for (Expenditure item : itemList.expenditureListArray) {
                    if (item.getAmount() == Integer.parseInt(searchTerm)) {
                        foundItems.add(item);
                    }
                }
                break;
            case "time":
                for (Expenditure item : itemList.expenditureListArray) {
                    if (item.getTime().contains(searchTerm)) {
                        foundItems.add(item);
                    }
                }
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
}
