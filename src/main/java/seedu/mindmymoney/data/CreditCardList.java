package seedu.mindmymoney.data;

import seedu.mindmymoney.userfinancial.CreditCard;

import java.util.ArrayList;

/**
 * Container for Credit Cards
 */
public class CreditCardList {
    public ArrayList<CreditCard> creditCardListArray;

    public CreditCardList() {
        this(new ArrayList<>());
    }

    public CreditCardList(ArrayList<CreditCard> listArray) {
        this.creditCardListArray = listArray;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return creditCardListArray.isEmpty();
    }

    /**
     * Represents the size of the CreditCard list.
     *
     * @return size of the task list.
     */
    public int size() {
        return creditCardListArray.size();
    }

    /**
     * Represents the CreditCard item.
     *
     * @param index Index of the CreditCard item.
     * @return The CreditCard item.
     */
    public CreditCard get(int index) {
        return creditCardListArray.get(index);
    }

    /**
     * Deletes the CreditCard item from the list.
     *
     * @param index Index of the item to delete.
     */
    public void delete(int index) {
        creditCardListArray.remove(index);
    }

    /**
     * Adds an CreditCard item to the list.
     *
     * @param item The CreditCard item to be added.
     */
    public void add(CreditCard item) {
        creditCardListArray.add(item);
    }

    /**
     * Updates the item at a particular index.
     *
     * @param index Index of the item to be updated.
     * @param item  The expenditure item to be updated.
     */
    public void set(int index, CreditCard item) {
        creditCardListArray.set(index,item);
    }
}
