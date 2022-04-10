package seedu.duke.itemlists;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.ItemNotFoundException;
import seedu.duke.exceptions.NewItemPaxSameAsCurrentPaxException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a list of the items within the inventory. An ItemList object contains the methods to add items to the
 * item list as well as update the paxs of the current items within the item List.
 */
public class ItemList {
    ArrayList<Item> listOfItems;
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    public ItemList() {
        ArrayList<Item> listOfItems = new ArrayList<>();
        setListOfItems(listOfItems);
    }

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(ArrayList<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    /**
     * Adds a new item to the item list. If there exists an item within the item list that has the same name as the item
     * the user wants to add, the item would not be added into the item list.
     *
     * @param item The item that the user wants to add into the item list.
     */
    public void addItemToList(Item item) {
        listOfItems.add(item);
    }

    public int getSize() {
        return listOfItems.size();
    }

    /**
     * Checks if there is another item within the item list which has the same name as the name passed
     * into the function.
     *
     * @param nameOfItemToAdd Name of the item that the user wants to add into the item list.
     * @return true if there is no items within the item list which has the same name as that passed into the function
     */
    public boolean checkForItemDuplicates(String nameOfItemToAdd) {
        boolean isItemAlreadyInTheList = false;
        int numberOfItemsInItemList = listOfItems.size();
        if (numberOfItemsInItemList == 0) {
            return isItemAlreadyInTheList;
        }
        String itemName;
        Item item;
        for (int itemIndex = 0; itemIndex < numberOfItemsInItemList; itemIndex++) {
            item = listOfItems.get(itemIndex);
            itemName = item.getName();
            if (itemName.equals(nameOfItemToAdd)) {
                isItemAlreadyInTheList = true;
                break;
            }
        }
        return isItemAlreadyInTheList;
    }

    /**
     * Returns a specific item within the item list based on the index passed in.
     *
     * @param index The index of the item within the list that would be returned.
     * @return The item specified by the index passed into the function.
     */
    public Item getItem(int index) {
        ArrayList<Item> listOfItems = getListOfItems();
        Item item = listOfItems.get(index);
        return item;
    }

    /**
     * Updates the pax of a specific item within the item list.
     *
     * @param item The item within the inventory that the user wants to update. The item object contains the item name
     *             and the new pax.
     * @throws ItemNotFoundException if the item name within the item object does not exist in the item list.
     */
    public void updateItemPaxInList(Item item) throws HotelLiteManagerException {
        String nameOfItemToUpdate = item.getName();
        int paxOfItemToUpdate = item.getPax();
        assert (paxOfItemToUpdate >= 0) : "Assertion Failed! Item to update has a pax that is lesser than 0";
        assert (!nameOfItemToUpdate.isEmpty()) : "Assertion Failed! Item to update has an empty name.";
        boolean isItemFound = false;
        Item currentItem;
        String currentItemName;
        int currentItemPax;
        ArrayList<Item> listOfItems = getListOfItems();
        for (int itemIndex = 0; itemIndex < listOfItems.size(); itemIndex++) {
            currentItem = listOfItems.get(itemIndex);
            currentItemName = currentItem.getName();
            currentItemPax = currentItem.getPax();
            if (currentItemName.equals(nameOfItemToUpdate) && currentItemPax == paxOfItemToUpdate) {
                throw new NewItemPaxSameAsCurrentPaxException();
            } else if (currentItemName.equals(nameOfItemToUpdate)) {
                currentItem.setPax(paxOfItemToUpdate);
                isItemFound = true;
            }
        }
        if (isItemFound == false) {
            itemLogger.log(Level.WARNING, "The item whose pax is to be updated cannot be found within the Item "
                    + "List. Exception thrown.");
            throw new ItemNotFoundException();
        }
    }

    /**
     * Deletes the item within the item list which has the item name specified by the user.
     *
     * @param item The item within the inventory that the user wants to delete. The item object contains the item name
     *             of the item to delete.
     * @throws ItemNotFoundException if the item that the user wants to delete does not exist within the item list.
     */

    public void deleteItemInList(Item item) throws ItemNotFoundException {
        String nameOfItemToDelete = item.getName();
        assert (!nameOfItemToDelete.isEmpty()) : "Assertion Failed! Name of the item to delete is empty.";
        boolean isItemFound = false;
        Item currentItem;
        String currentItemName;
        ArrayList<Item> listOfItems = getListOfItems();
        for (int itemIndex = 0; itemIndex < listOfItems.size(); itemIndex++) {
            currentItem = listOfItems.get(itemIndex);
            currentItemName = currentItem.getName();
            if (currentItemName.equals(nameOfItemToDelete)) {
                listOfItems.remove(itemIndex);
                isItemFound = true;
            }
        }
        if (isItemFound == false) {
            itemLogger.log(Level.WARNING, "The item to delete cannot be found within the Item List. Exception "
                    + "thrown.");
            throw new ItemNotFoundException();
        }
    }

    /**
     * Updates the pax of a specific item within the item list.
     *
     * @param currentItemName The current name of the item within the item list that the user wants to update.
     * @param newItemName     The new name of the item within the item list that the user wants to update.
     * @throws ItemNotFoundException if the item that the user wants to update does not exist in the item list.
     */
    public void updateItemNameInList(String currentItemName, String newItemName) throws ItemNotFoundException {
        boolean isItemFound = false;
        ArrayList<Item> listOfItems = getListOfItems();
        Item item;
        String itemName;
        for (int itemIndex = 0; itemIndex < listOfItems.size(); itemIndex++) {
            item = listOfItems.get(itemIndex);
            itemName = item.getName();
            if (itemName.equals(currentItemName)) {
                item.setName(newItemName);
                isItemFound = true;
                break;
            }
        }
        if (isItemFound == false) {
            itemLogger.log(Level.WARNING, "The item whose name we want to update cannot be found within the "
                    + "Item List. Exception thrown.");
            throw new ItemNotFoundException();
        }
    }

    /**
     * Returns all the items in the item list whose name contains the keyword given by the user.
     *
     * @param keyword The keyword used to search the item list.
     * @return An item list containing all the items whose names contain the keyword.
     */
    public ItemList findItemsInList(String keyword) {
        ItemList listOfMatchingItems = new ItemList();
        ArrayList<Item> listOfItems = getListOfItems();
        Item item;
        String itemName;
        for (int itemIndex = 0; itemIndex < listOfItems.size(); itemIndex++) {
            item = listOfItems.get(itemIndex);
            itemName = item.getName();
            if (itemName.contains(keyword)) {
                listOfMatchingItems.addItemToList(item);
            }
        }
        return listOfMatchingItems;
    }

    /**
     * Returns all the items in the item list whose pax is zero.
     *
     * @return An item list containing all the items whose pax is zero.
     */
    public ItemList findItemsWithZeroPaxInList() {
        ItemList listOfMatchingItems = new ItemList();
        Item item;
        int itemPax;
        for (int itemIndex = 0; itemIndex < listOfItems.size(); itemIndex++) {
            item = listOfItems.get(itemIndex);
            itemPax = item.getPax();
            if (itemPax == 0) {
                listOfMatchingItems.addItemToList(item);
            }
        }
        return listOfMatchingItems;
    }
}
