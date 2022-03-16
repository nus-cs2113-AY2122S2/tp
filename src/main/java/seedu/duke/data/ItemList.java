package seedu.duke.data;

import seedu.duke.exceptions.InvMgrException;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<Item> itemList;

    public ItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public Item removeItem(int index) {
        return this.itemList.remove(index);
    }

    /**
     * Returns the item from the current inventory that contains the search term.
     *
     * @param searchTerm User input of search term.
     * @return Item from current inventory that contains the search term.
     * @throws InvMgrException If current inventory does not contain the search term in item name
     */
    public Item contains(String searchTerm) throws InvMgrException {
        for (Item item : itemList) {
            if (item.contains(searchTerm)) {
                return item;
            }
        }

        throw new InvMgrException("Current inventory does not contain this item.");
    }
}
