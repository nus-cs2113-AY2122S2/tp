package seedu.mindmymoney.data;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.userfinancial.Income;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.mindmymoney.helper.SerializerFunctions.SERIALIZATION_INCOME_END_MARKER;
import static seedu.mindmymoney.helper.SerializerFunctions.SERIALIZATION_INCOME_START_MARKER;
import static seedu.mindmymoney.helper.SerializerFunctions.addListToStringBuilder;
import static seedu.mindmymoney.helper.SerializerFunctions.convertInputToList;

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

    /**
     * Converts this IncomeList into a machine-readable format.
     * @return The serialized IncomeList
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        addListToStringBuilder(SERIALIZATION_INCOME_START_MARKER,
                SERIALIZATION_INCOME_END_MARKER,
                incomeListArray,
                sb);
        return sb.toString();
    }

    /**
     * Reads a serialized IncomeList from the scanner.
     * @param scanner A scanner
     * @returns An IncomeList
     * @throws MindMyMoneyException if the format is invalid.
     */
    public static IncomeList deserializeFrom(Scanner scanner) throws MindMyMoneyException {
        IncomeList incomeList = new IncomeList();
        incomeList.incomeListArray = convertInputToList(
                SERIALIZATION_INCOME_START_MARKER,
                SERIALIZATION_INCOME_END_MARKER,
                scanner, Income::deserialize);
        return incomeList;
    }
}
