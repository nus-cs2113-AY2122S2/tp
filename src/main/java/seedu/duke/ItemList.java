package seedu.duke;

import java.util.ArrayList;

public class ItemList {
    ArrayList<Item> listOfItems;

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
        int updatedItemPax = item.getPax();
        boolean isItemFound = false;
        Item currentItem;
        String currentItemName;
        ArrayList<Item> listOfItems = getListOfItems();
        for (int i = 0; i < listOfItems.size(); i++) {
            currentItem = listOfItems.get(i);
            currentItemName = currentItem.getName();
            if (currentItemName.equals(nameOfItemToUpdate)) {
                currentItem.setPax(updatedItemPax);
                isItemFound = true;
            }
        }
        if (isItemFound == false) {
            throw new ItemNotFoundException();
        }
    }
}
