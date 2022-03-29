package seedu.duke;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static javax.swing.plaf.synth.Region.TABLE_HEADER;

/**
 * Represents the User Interface component of the program.
 * The Ui object handles all interactions with the user such as printing the outputs to the user
 * as well as reading in the user inputs .
 */
public class Ui {
    private Scanner in = new Scanner(System.in);
    private static final int ARRAY_INDEX_OFFSET = 1;
    private static final String LINE = "-------------------------------------------------------------------";
    private static final String ITEM_LIST_HEADER = "=========== Item List ===========";
    private static final String END_OF_LIST_LINE = "======== End of the list ========";
    private static final String TABLE_HEADER = "Type\t\tRoom Id\t\tlevel\t\tStatus\t\t\tHousekeeper Name";

    /**
     * Returns a string containing the user input.
     *
     * @return User input
     */
    public String readUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }


    public void printErrorMessage(HotelLiteManagerException exception) {
        System.out.println(exception.getErrorMessage());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printHousekeeperNoted(Housekeeper housekeeper) {
        System.out.println("========== Noted ! ==========");
        System.out.println(housekeeper);
        System.out.println("=============================");
    }

    /**
     * Prints out an acknowledgement message to inform the user that the item has been added to the item list as well
     * as the number of items within the item list.
     *
     * @param listOfItems The item list containing all the items in the inventory.
     */
    public void printAddItemAcknowledgementMessage(ItemList listOfItems) {
        System.out.println("The item and its pax has been added to the list of items in the inventory.");
        assert (listOfItems.getSize() > 0) : "Assertion Failed! Number of items in the item list is 0 after an item "
                + "was added to it.";
        System.out.printf("There are currently %d items within the inventory.\n", listOfItems.getSize());
    }

    public void printItemAlreadyInTheListErrorMessage(String nameOfItemToAdd) {
        System.out.printf("Error! %s has already been added to the item list.\n", nameOfItemToAdd);
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
        for (int i = 0; i < listOfItems.getSize(); i++) {
            currentItem = listOfItems.getItem(i);
            itemName = currentItem.getName();
            itemName = itemName.toUpperCase();
            itemPax = currentItem.getPax();
            assert (itemPax >= 0) : "Assertion Failed! Pax of an item within the listOfItems is less than 0 !";
            assert (!itemName.isEmpty()) : "Assertion Failed! The name of the item within the listOfItems is empty !";
            currentListIndex = i + ARRAY_INDEX_OFFSET;
            System.out.printf("%d. Item Name: %s Item Pax: %d\n", currentListIndex, itemName, itemPax);
        }
        System.out.println(END_OF_LIST_LINE);
    }

    public void printNoItemsFoundInListAcknowledgementMessage() {
        System.out.println("No Item matching the keyword has been found.");
    }

    public void printHousekeeperList(HousekeeperList housekeeperList) {
        printMessage("======== Housekeeper List ========");
        for (int i = 0; i < housekeeperList.getTotalHousekeeper(); i++) {
            System.out.println((i + 1) + ". " + housekeeperList.getHousekeeper(i));
        }
        printMessage("======== End of the list ========");
    }

    public void printHousekeeperListReset(HousekeeperList housekeeperList) {
        printMessage("Housekeeper's availability has been reset!");
        printHousekeeperList(housekeeperList);
    }

    public void printFoundHousekeeperList(ArrayList<Housekeeper> housekeeperPrintList, int dayInteger) {
        String day = getDayInString(dayInteger);

        printMessage("======== " + day + " List ========");
        if (housekeeperPrintList.isEmpty()) {
            printMessage("TAKE NOTE! NO ONE IS AVAILABLE!!");
        }
        int i = 1;
        for (Housekeeper housekeeper : housekeeperPrintList) {
            System.out.println(i + ". " + housekeeper.getName());
            i += 1;
        }
        printMessage("======== End of the list ========");
    }

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

    public void printOverAgeList(ArrayList<Housekeeper> housekeeperPrintList) {
        printMessage("======== Age Limit Exceed List ========");
        if (housekeeperPrintList.isEmpty()) {
            printMessage("Everyone is within age limit");
        }
        int i = 1;
        for (Housekeeper housekeeper : housekeeperPrintList) {
            System.out.println(i + ". " + housekeeper);
            i += 1;
        }
        printMessage("======== End of the list ========");
    }

    /**
     * Prints an acknowledgement message informing the user that the pax of the item was successfully updated.
     *
     * @param updatedItem The item within the inventory whose pax has been updated by the user.
     */
    public void printUpdateItemPaxAcknowledgementMessage(Item updatedItem) {
        String updatedItemName = updatedItem.getName();
        updatedItemName = updatedItemName.toUpperCase();
        int updatedItemNewPax = updatedItem.getPax();
        assert (!updatedItemName.isEmpty()) : "Assertion Failed! Updated item has an empty item name.";
        assert (updatedItemNewPax >= 0) : "Assertion Failed! Updated item has a pax that is less than 0.";
        System.out.printf("The pax of %s has been updated to %d.\n", updatedItemName, updatedItemNewPax);
    }

    public void printUpdateItemNameAcknowledgementMessage(String oldItemName, String newItemName) {
        System.out.printf("The name of %s has been updated to %s.\n", oldItemName, newItemName);
    }

    public void printAddSatisfactionAcknowledgementMessage(SatisfactionList satisfactionList,
                                                           Satisfaction recentSatisfaction) {
        System.out.println("The Satisfaction instance " + recentSatisfaction.getCustomerName() + ": "
                + recentSatisfaction.getSatisfactionValue() + " has been added to the list of Satisfactions.");
        System.out.printf("There are currently %d recorded customer satisfactions.\n", satisfactionList.getSize());
    }


    public void printDeleteItemAcknowledgementMessage(Item updatedItem, ItemList listOfItems) {
        String itemName = updatedItem.getName();
        itemName = itemName.toUpperCase();
        assert (!itemName.isEmpty()) : "Assertion Failed! Updated item has an empty item name.";
        System.out.printf("%s has been removed from the Item List.\n", itemName);
        System.out.printf("There are currently %d items within the Item List.\n", listOfItems.getSize());
    }

    public void printAddHousekeeperPerformanceAcknowledgementMessage(HousekeeperPerformanceList
                                                                             housekeeperPerformanceList,
                                                                     HousekeeperPerformance housekeeperPerformance) {
        System.out.println("The HousekeeperPerformance instance " + housekeeperPerformance.getName() + ": "
                + housekeeperPerformance.getRating() + " has been added to the list of housekeeper performances.");
        System.out.printf("There are currently %d recorded housekeeper performances.\n",
                housekeeperPerformanceList.getSize());
    }

    public void printTableHeader() {
        System.out.println(LINE);
        System.out.println(TABLE_HEADER);
        System.out.println(LINE);
    }
}

