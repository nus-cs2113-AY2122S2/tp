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

    public void addItemToList(Item item) {
        listOfItems.add(item);
    }

    public int getSize() {
        return listOfItems.size();
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
    public void updateItemPaxInList(Item item) throws ItemNotFoundException {
        String nameOfItemToUpdate = item.getName();
        int paxOfItemToUpdate = item.getPax();
        assert (paxOfItemToUpdate >= 0) : "Assertion Failed! Item to update has a pax that is lesser than 0";
        assert (!nameOfItemToUpdate.isEmpty()) : "Assertion Failed! Item to update has an empty name.";
        boolean isItemFound = false;
        Item currentItem;
        String currentItemName;
        ArrayList<Item> listOfItems = getListOfItems();
        for (int i = 0; i < listOfItems.size(); i++) {
            currentItem = listOfItems.get(i);
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
}
