package seedu.duke;

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
}
