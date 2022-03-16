package seedu.mindmymoney.data;

import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

/**
 * Container for expenditure lists.
 */
public class ExpenditureList {
    public ArrayList<Expenditure> expenditureListArray;

    public ExpenditureList() {
        this(new ArrayList<>());
    }

    public ExpenditureList(ArrayList<Expenditure> listArray) {
        this.expenditureListArray = listArray;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return expenditureListArray.isEmpty();
    }

    /**
     * Represents the size of the task list.
     *
     * @return size of the task list.
     */
    public int size() {
        return expenditureListArray.size();
    }

    /**
     * Represents the expenditure item.
     *
     * @param index Index of the expenditure item.
     * @return The expenditure item.
     */
    public Expenditure get(int index) {
        return expenditureListArray.get(index);
    }

    /**
     * Deletes the expenditure item from the list.
     *
     * @param index Index of the item to delete.
     */
    public void delete(int index) {
        expenditureListArray.remove(index);
    }

    /**
     * Adds an expenditure item to the list.
     * @param item The expenditure item to be added.
     */
    public void add(Expenditure item) {
        expenditureListArray.add(item);
    }

    /**
     * Updates the item at a particular index.
     * @param index Index of the item to be updated.
     * @param item  The expenditure item to be updated.
     */
    public void set(int index, Expenditure item) {
        expenditureListArray.set(index,item);
    }
}
