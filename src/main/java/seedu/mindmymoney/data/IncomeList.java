package seedu.mindmymoney.data;

import seedu.mindmymoney.userfinancial.Income;

import java.util.ArrayList;

/**
 * Container for income lists.
 */
public class IncomeList {
    public ArrayList<Income> incomeListArray;

    public IncomeList() {
        this(new ArrayList<>());
    }

    public IncomeList(ArrayList<Income> incomeListArray) {
        this.incomeListArray = incomeListArray;
    }

    /**
     * Adds an Income entry to the list.
     *
     * @param income The Income entry to be added.
     */
    public void add(Income income) {
        incomeListArray.add(income);
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return incomeListArray.isEmpty();
    }

    /**
     * Retrieves the Income entry at the given index.
     *
     * @param index Index of the income entry.
     * @return The Income object.
     */
    public Income get(int index) {
        return incomeListArray.get(index);
    }

    /**
     * Updates the Income entry at the given index.
     *
     * @param index Index of the entry to be updated.
     * @param income The new Income entry.
     */
    public void set(int index, Income income) {
        incomeListArray.set(index, income);
    }

    /**
     * Deletes the Income entry from the list.
     *
     * @param index Index of the Income entry to delete.
     */
    public void delete(int index) {
        incomeListArray.remove(index);
    }
}
