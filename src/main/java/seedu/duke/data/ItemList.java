package seedu.duke.data;

import seedu.duke.exceptions.InvMgrException;

import java.util.List;

public class ItemList {

    private List<Item> itemArrayList;

    public ItemList(List<Item> itemList) {
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

    public List<Item> getItemArrayList() {
        return itemArrayList;
    }

    /**
     * Add a borrow record to a specific item in the itemArrayList as indicated
     * by the itemIndex.
     *
     * @param itemIndex A legal item index on the itemArrayList. Zero-based indexing.
     * @param newRecord A borrow record
     * @return The item that has been added with the new borrow record.
     */
    public Item addBorrowRecord(int itemIndex, BorrowRecord newRecord) throws InvMgrException {
        Item item = this.itemArrayList.get(itemIndex).addBorrowRecord(newRecord);
        return item;
    }

    @Override
    public boolean equals(Object other) {
        ItemList toCompare;
        if (other == this) {
            // return if same object
            return true;
        }
        if (other instanceof ItemList) {
            // cast only if other is instance of EditCommand
            toCompare = (ItemList) other;
            return this.itemArrayList.equals(toCompare.itemArrayList);
        } else {
            // null, or object not EditCommand
            return false;
        }
    }
}
