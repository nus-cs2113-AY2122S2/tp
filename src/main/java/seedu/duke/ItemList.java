package seedu.duke;

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

    public boolean addItemToList(Item item) {
        boolean isItemAlreadyInTheList = checkForItemDuplicates(item.getName());
        if (isItemAlreadyInTheList == true) {
            return isItemAlreadyInTheList;
        }
        listOfItems.add(item);
        return isItemAlreadyInTheList;
    }

    public int getSize() {
        return listOfItems.size();
    }

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
     * Returns a specific satisfaction within the satisfaction list.
     *
     * @param index The index of the satisfaction within the list that would be returned.
     * @return The satisfaction at the given index in the satisfaction list.
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
    public void updateItemPaxInList(Item item) throws ItemNotFoundException {
        String nameOfItemToUpdate = item.getName();
        int paxOfItemToUpdate = item.getPax();
        assert (paxOfItemToUpdate >= 0) : "Assertion Failed! Item to update has a pax that is lesser than 0";
        assert (!nameOfItemToUpdate.isEmpty()) : "Assertion Failed! Item to update has an empty name.";
        boolean isItemFound = false;
        Item currentItem;
        String currentItemName;
        ArrayList<Item> listOfItems = getListOfItems();
        for (int itemIndex = 0; itemIndex < listOfItems.size(); itemIndex++) {
            currentItem = listOfItems.get(itemIndex);
            currentItemName = currentItem.getName();
            if (currentItemName.equals(nameOfItemToUpdate)) {
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
