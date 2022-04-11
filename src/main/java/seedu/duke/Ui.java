package seedu.duke;

import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.housekeeperperformancelists.HousekeeperPerformance;
import seedu.duke.housekeeperperformancelists.HousekeeperPerformanceList;
import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;
import seedu.duke.eventlists.Event;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.satisfactionlists.Satisfaction;
import seedu.duke.satisfactionlists.SatisfactionList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the User Interface component of the program.
 * The Ui object handles all interactions with the user such as printing the outputs to the user
 * as well as reading in the user inputs .
 */
public class Ui {
    private Scanner in = new Scanner(System.in);
    private static final int ARRAY_INDEX_OFFSET = 1;
    private static final String LINE = "=======================================================================";
    private static final String ITEM_LIST_HEADER = "=============== Item List =================";
    private static final String END_OF_LIST_LINE = "============ End of the list ==============";
    private static final String MESSAGE_HEADER = "================ Noted! ===================";
    private static final String END_OF_MESSAGE_LINE = "===========================================";
    private static final String TABLE_HEADER = String.format("%-15s%-15s%-15s%-15s%-15s", "Type",
            "Room Id", "Level", "Status", "Housekeeper");

    /**
     * Returns a string containing the user input.
     *
     * @return User input
     */
    public String readUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }

    public void printHelp(String message) {
        System.out.println(MESSAGE_HEADER);
        System.out.println(message);
        System.out.println(END_OF_MESSAGE_LINE);
    }

    public void printEventAdded(Event event) {
        System.out.println(MESSAGE_HEADER);
        System.out.println("I have added the following event in your list:");
        System.out.println("\t" + event.toString());
        System.out.println(END_OF_MESSAGE_LINE);
    }

    public void printAllEvents(ArrayList<Event> events) {
        System.out.println(MESSAGE_HEADER);
        System.out.println("Here are all the events in your list:");
        int j = 0;
        for (Event event : events) {
            j = j + 1;
            System.out.println("\t" + j + ". " + event.toString());
        }
        System.out.println(END_OF_MESSAGE_LINE);
    }

    public void printGreeting() {
        int row = 5;
        int i;
        int j;
        int space = row - 1;
        for (j = 1; j <= row; j++) {
            for (i = 1; i <= space; i++) {
                System.out.print(" ");
            }
            space--;
            for (i = 1; i <= 2 * j - 1; i++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        space = 1;
        for (j = 1; j <= row - 1; j++) {
            for (i = 1; i <= space; i++) {
                System.out.print(" ");
            }
            space++;
            for (i = 1; i <= 2 * (row - j) - 1; i++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        System.out.println("Hi, I am Hotel Lite, and "
                + "I will do the managing for you.");
        System.out.println("What shall we start with today?");
        System.out.println("(You may type 'help' to get a full "
                + "list of commands.)");
    }


    public void printEventDeleted(Event event) {
        System.out.println(MESSAGE_HEADER);
        System.out.println("I have deleted the following event from your list:");
        System.out.println("\t" + event.toString());
        System.out.println(END_OF_MESSAGE_LINE);
    }

    public void printAssignedHousekeeper(String roomID, String name) {
        System.out.println(MESSAGE_HEADER);
        System.out.println("I have assigned " + name
                + " to room number " + roomID + ".");
        System.out.println(END_OF_MESSAGE_LINE);
    }

    /**
     * Prints out the error message associated with the exception that is passed into the function.
     *
     * @param exception The exception whose error message the program wants to print out.
     */
    public void printErrorMessage(HotelLiteManagerException exception) {
        System.out.println(exception.getErrorMessage());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Uses toString method from the housekeeper class to print the name, age and available of the housekeeper to be
     * added into the list.
     *
     * @param housekeeper Housekeeper to be added into list.
     */
    public void printHousekeeperNoted(Housekeeper housekeeper) {
        System.out.println(MESSAGE_HEADER);
        System.out.println(housekeeper);
        System.out.println(END_OF_MESSAGE_LINE);
    }

    /**
     * Prints out an acknowledgement message to inform the user that the item has been added to the item list as well
     * as the number of items within the item list.
     *
     * @param listOfItems The item list containing all the items in the inventory.
     */
    public void printAddItemAcknowledgementMessage(ItemList listOfItems) {
        System.out.println(MESSAGE_HEADER);
        System.out.println("The item and its pax has been added to the item list.");
        assert (listOfItems.getSize() > 0) : "Assertion Failed! Number of items in the item list is 0 after an item "
                + "was added to it.";
        System.out.printf("There are currently %d items within the item list.\n", listOfItems.getSize());
        System.out.println(END_OF_MESSAGE_LINE);
    }

    /**
     * Prints out the item name ,pax as well as index for each item that is found within the item list.
     *
     * @param listOfItems The item list containing all the items in the inventory.
     */
    public void printItemList(ItemList listOfItems) {
        String itemName;
        int itemPax;
        Item currentItem;
        int currentListIndex;
        System.out.println(ITEM_LIST_HEADER);
        if (listOfItems.getSize() == 0) {
            System.out.println("No item found within the item list.");
        } else {
            for (int i = 0; i < listOfItems.getSize(); i++) {
                currentItem = listOfItems.getItem(i);
                itemName = currentItem.getName();
                itemName = itemName.toUpperCase();
                itemPax = currentItem.getPax();
                assert (itemPax >= 0) : "Assertion Failed! Pax of an item within the listOfItems is less than 0 !";
                assert (!itemName.isEmpty()) : "Assertion Failed! The name of the item within the listOfItems is empty "
                        + "!";
                currentListIndex = i + ARRAY_INDEX_OFFSET;
                System.out.printf("%d. Item Name: %s Item Pax: %d\n", currentListIndex, itemName, itemPax);
            }
        }
        System.out.println(END_OF_LIST_LINE);
    }

    /**
     * Prints out an acknowledgement message saying that there were no items within the item list that matches the
     * user specified criteria.
     */
    public void printNoItemsFoundInListAcknowledgementMessage() {
        System.out.println(MESSAGE_HEADER);
        System.out.println("No Item matching the criteria has been found.");
        System.out.println(END_OF_MESSAGE_LINE);
    }

    /**
     * This method prints the name, age and availability of each housekeeper in the list.
     *
     * @param housekeeperList Contains the list of housekeeper's profiles.
     */
    public void printHousekeeperList(HousekeeperList housekeeperList) {
        printMessage("=============== Housekeeper List ================");
        for (int i = 0; i < housekeeperList.getTotalHousekeeper(); i++) {
            System.out.println((i + 1) + ". " + housekeeperList.getHousekeeper(i));
        }
        printMessage("=============== End of the list =================");
    }


    /**
     * This method notifies user that every housekeeper availability has been rested and prints the list of housekeeper
     * with their name, age and availability. The availability column seen should now be 'N/A'.
     *
     * @param housekeeperList Contains the list of housekeeper's profiles.
     */
    public void printHousekeeperListReset(HousekeeperList housekeeperList) {
        printMessage("Housekeeper's availability has been reset!");
        printHousekeeperList(housekeeperList);
    }

    /**
     * Given the day interested from the user, this method will print the list of housekeeper available on the
     * day of interest.
     *
     * @param housekeeperPrintList Contains the list of housekeeper's profiles.
     * @param dayInteger           The Day user is interested to know which housekeepers are available.
     */
    public void printFoundHousekeeperList(ArrayList<Housekeeper> housekeeperPrintList, int dayInteger) {
        String day = getDayInString(dayInteger);

        printMessage("=========== " + day + " List ===========");
        if (housekeeperPrintList.isEmpty()) {
            printMessage("TAKE NOTE! NO ONE IS AVAILABLE!!");
        }
        int i = 1;
        for (Housekeeper housekeeper : housekeeperPrintList) {
            System.out.println(i + ". " + housekeeper.getName());
            i += 1;
        }
        printMessage("========= End of the list =========");
    }

    /**
     * Converts the day from integer to their string equivalent. For example, 1 represents Monday.
     *
     * @param dayInteger Day given in integer.
     * @return The actual name of the day in string.
     */
    private String getDayInString(int dayInteger) {
        String day;
        switch (dayInteger) {
        case 1:
            day = "Monday";
            break;
        case 2:
            day = "Tuesday";
            break;
        case 3:
            day = "Wednesday";
            break;
        case 4:
            day = "Thursday";
            break;
        case 5:
            day = "Friday";
            break;
        case 6:
            day = "Saturday";
            break;
        case 7:
            day = "Sunday";
            break;
        default:
            day = "None of Day";
        }
        return day;
    }

    /**
     * Prints the list of housekeepers who exceed age limit after user given the command to indicate a new year
     * has started. It will also notifies user if none of the housekeeper's age has exceeded the age limit.
     *
     * @param housekeeperPrintList Consist the list of housekeeper whose age has exceed the age limit which is 61 and
     *                             above.
     */
    public void printOverAgeList(ArrayList<Housekeeper> housekeeperPrintList) {
        printMessage("======== Age Limit Exceed List ========");
        if (housekeeperPrintList.isEmpty()) {
            printMessage("No one exceed age limit");
        }
        int i = 1;
        for (Housekeeper housekeeper : housekeeperPrintList) {
            System.out.println(i + ". " + housekeeper);
            i += 1;
        }
        printMessage("=========== End of the list ===========");
    }

    /**
     * Prints an acknowledgement message informing the user that the pax of the item was successfully updated.
     *
     * @param updatedItem The item within the item list whose pax has been updated by the user.
     */
    public void printUpdateItemPaxAcknowledgementMessage(Item updatedItem) {
        String updatedItemName = updatedItem.getName();
        updatedItemName = updatedItemName.toUpperCase();
        int updatedItemNewPax = updatedItem.getPax();
        assert (!updatedItemName.isEmpty()) : "Assertion Failed! Updated item has an empty item name.";
        assert (updatedItemNewPax >= 0) : "Assertion Failed! Updated item has a pax that is less than 0.";
        System.out.println(MESSAGE_HEADER);
        System.out.printf("The pax of %s has been updated to %d.\n", updatedItemName, updatedItemNewPax);
        System.out.println(END_OF_MESSAGE_LINE);
    }

    /**
     * Prints an acknowledgement message informing the user that the item name of the item to update was successfully
     * updated.
     *
     * @param oldItemName The current item name of the item to update within the item list.
     * @param newItemName The new item name of the item to update within the item list.
     */
    public void printUpdateItemNameAcknowledgementMessage(String oldItemName, String newItemName) {
        System.out.println(MESSAGE_HEADER);
        System.out.printf("The name of %s has been updated to %s.\n", oldItemName, newItemName);
        System.out.println(END_OF_MESSAGE_LINE);
    }

    public void printAddSatisfactionAcknowledgementMessage(SatisfactionList satisfactionList,
                                                           Satisfaction recentSatisfaction) {
        System.out.println(MESSAGE_HEADER);
        System.out.println("The Satisfaction instance " + recentSatisfaction.getCustomerName().toUpperCase() + ": "
                + recentSatisfaction.getSatisfactionValue() + " has been added to the list of Satisfactions.");
        System.out.printf("There are currently %d recorded customer satisfactions.\n", satisfactionList.getSize());
        System.out.println(END_OF_MESSAGE_LINE);
    }

    /**
     * Prints an acknowledgement message informing the user that the item to delete has been deleted from the item list.
     *
     * @param itemToDelete The item object which contains the item name of the item to be deleted from the item list.
     * @param listOfItems  The item list which containing all the items.
     */
    public void printDeleteItemAcknowledgementMessage(Item itemToDelete, ItemList listOfItems) {
        String itemName = itemToDelete.getName();
        itemName = itemName.toUpperCase();
        assert (!itemName.isEmpty()) : "Assertion Failed! Updated item has an empty item name.";
        System.out.println(MESSAGE_HEADER);
        System.out.printf("%s has been removed from the Item List.\n", itemName);
        System.out.printf("There are currently %d items within the Item List.\n", listOfItems.getSize());
        System.out.println(END_OF_MESSAGE_LINE);
    }

    public void printAddHousekeeperPerformanceAcknowledgementMessage(HousekeeperPerformanceList
                                                                             housekeeperPerformanceList,
                                                                     HousekeeperPerformance housekeeperPerformance) {
        System.out.println(MESSAGE_HEADER);
        System.out.println("The HousekeeperPerformance instance " + housekeeperPerformance.getName().toUpperCase()
                + ": " + housekeeperPerformance.getRating() + " has been added to the list "
                + "of housekeeper performances.");
        System.out.printf("There are currently %d recorded housekeeper performances.\n",
                housekeeperPerformanceList.getSize());
        System.out.println(END_OF_MESSAGE_LINE);
    }

    public void printTableHeader() {
        System.out.println(LINE);
        System.out.println(TABLE_HEADER);
        System.out.println(LINE);
    }

    public void printNotedLine() {
        System.out.println("================ Noted! ===================");
    }

    public void printBottomLine() {
        System.out.println("===========================================");
    }

    /**
     * This method print the confirmation that the housekeeper has been delete as well as the total pax of
     * housekeeper currently working in the hotel.
     *
     * @param housekeeperList Contains the list of housekeeper's profiles.
     * @param name            Name of housekeeper to be deleted.
     */
    public void printNotifiedDeletionOfHousekeeper(HousekeeperList housekeeperList, String name) {
        printNotedLine();
        printMessage("Deleted " + name + " from the list of profile");
        printMessage("Take note! Total pax of housekeeper:  " + housekeeperList.getTotalHousekeeper());
        printBottomLine();
    }
}

