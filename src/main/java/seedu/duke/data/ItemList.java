package seedu.duke.data;

import java.util.ArrayList;

public class ItemList {

    private ArrayList<Item> itemArrayList;

    public ItemList(ArrayList<Item> itemList) {
        this.itemArrayList = itemList;
    }

    public void addItem(Item item) {
        this.itemArrayList.add(item);
    }

    public Item removeItem(int index) {
        return this.itemArrayList.remove(index);
    }

    public Item getItem(int index) {
        return this.itemArrayList.get(index);
    }

    public int getSize() {
        return this.itemArrayList.size();
    }

    public Item set(int index, Item item) {
        return itemArrayList.set(index, item);
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    /**
     * Add a borrow record to a specific item in the itemArrayList as indicated
     * by the itemIndex.
     *
     * @param itemIndex
     * @param newRecord
     */
    public void addBorrowRecord(int itemIndex, BorrowRecord newRecord) {
        this.itemArrayList.get(itemIndex).addBorrowRecord(newRecord);
    }
}
