package seedu.duke;

import java.util.Scanner;

/**
 * Represents the User Interface component of the program.
 * The Ui object handles all interactions with the user such as printing the outputs to the user
 * as well as reading in the user inputs .
 */
public class Ui {
    private Scanner in;
    private final int ARRAY_INDEX_OFFSET = 1;

    public Ui() {
        in = new Scanner(System.in);
    }

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

    public void printHousekeeperNoted(Housekeeper housekeeper) {
        System.out.println("Noted!");
        System.out.println(housekeeper);

    }

    public void printAddItemAcknowledgementMessage(ItemList listOfItems) {
        System.out.println("The item and its pax has been added to the list of items in the inventory.");
        System.out.printf("There are currently %d items within the inventory.\n", listOfItems.getSize());
    }
    public void printItemList(ItemList listOfItems) {
        String itemName;
        int itemPax;
        Item currentItem;
        int currentListIndex;
        for (int i = 0; i < listOfItems.getSize(); i++) {
            currentItem = listOfItems.getItem(i);
            itemName = currentItem.getName();
            itemPax = currentItem.getPax();
            currentListIndex = i + ARRAY_INDEX_OFFSET;
            System.out.printf("%d. Item Name: %s Item Pax: %d\n", currentListIndex, itemName, itemPax);
        }
    }
}
