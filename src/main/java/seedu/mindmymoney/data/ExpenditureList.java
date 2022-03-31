package seedu.mindmymoney.data;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.mindmymoney.helper.SerializerFunctions.SERIALIZATION_EXPENDITURE_END_MARKER;
import static seedu.mindmymoney.helper.SerializerFunctions.SERIALIZATION_EXPENDITURE_START_MARKER;
import static seedu.mindmymoney.helper.SerializerFunctions.addListToStringBuilder;
import static seedu.mindmymoney.helper.SerializerFunctions.convertInputToList;

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
     * Retrieves the Expenditure entry at the given index.
     *
     * @param index Index of the Expenditure entry.
     * @return The Expenditure object.
     */
    public Expenditure get(int index) {
        return expenditureListArray.get(index);
    }

    /**
     * Deletes the Expenditure entry from the list.
     *
     * @param index Index of the Expenditure entry to delete.
     */
    public void delete(int index) {
        expenditureListArray.remove(index);
    }

    /**
     * Adds an Expenditure entry to the list.
     *
     * @param item The Expenditure entry to be added.
     */
    public void add(Expenditure item) {
        expenditureListArray.add(item);
    }

    /**
     * Updates the Expenditure entry at the given index.
     *
     * @param index Index of the entry to be updated.
     * @param item The new Expenditure entry.
     */
    public void set(int index, Expenditure item) {
        expenditureListArray.set(index,item);
    }

    /**
     * Converts this ExpenditureList into a machine-readable format.
     * @return The serialized ExpenditureList
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        addListToStringBuilder(SERIALIZATION_EXPENDITURE_START_MARKER,
                SERIALIZATION_EXPENDITURE_END_MARKER,
                expenditureListArray,
                sb);
        return sb.toString();
    }

    /**
     * Reads a serialized ExpenditureList from the scanner.
     * @param scanner A scanner
     * @returns An ExpenditureList.
     * @throws MindMyMoneyException if the format is invalid.
     */
    public static ExpenditureList deserializeFrom(Scanner scanner) throws MindMyMoneyException {
        ExpenditureList savedExpenditureList = new ExpenditureList();
        savedExpenditureList.expenditureListArray = convertInputToList(
                SERIALIZATION_EXPENDITURE_START_MARKER,
                SERIALIZATION_EXPENDITURE_END_MARKER,
                scanner, Expenditure::deserialize);
        return savedExpenditureList;
    }
}
