package seedu.mindmymoney.data;

import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

/**
 * Container for lists.
 */
public class Lists {
    public ArrayList<Expenditure> itemList;

    public Lists() {
        this(new ArrayList<>());
    }

    public Lists(ArrayList<Expenditure> listArray) {
        this.itemList = listArray;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    /**
     * Represents the size of the task list.
     *
     * @return size of the task list.
     */
    public int size() {
        return itemList.size();
    }

    /**
     * Represents the expenditure item.
     *
     * @param index Index of the expenditure item.
     * @return The expenditure item.
     */
    public Expenditure get(int index) {
        return itemList.get(index);
    }

    /**
     * Deletes the expenditure item from the list.
     *
     * @param index Index of the item to delete.
     */
    public void delete(int index) {
        itemList.remove(index);
    }

    /**
     * Adds an expenditure item to the list.
     * @param item The expenditure item to be added.
     */
    public void add(Expenditure item) {
        itemList.add(item);
    }

    /**
     * Updates the item at a particular index.
     * @param index Index of the item to be updated.
     * @param item  The expenditure item to be updated.
     */
    public void set(int index, Expenditure item) {
        itemList.set(index,item);
    }
}
