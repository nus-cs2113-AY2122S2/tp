package seedu.splitlah.ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a row of items in a TableFormatter object.
 *
 * @author Saurav
 */
public class TableFormatterRow {

    private ArrayList<String> rowItems;

    public TableFormatterRow(String... rowItems) {
        this.rowItems = new ArrayList<>(Arrays.asList(rowItems));
    }

    public int getRowLength() {
        return rowItems.size();
    }
    /**
     * Returns a String object representing a specific row item in a TableFormatterRow object.
     * Row items start at index 0.
     *
     * @param index An integer that represents location of the row item.
     * @return A String object representing the row item.
     */

    public String getItem(int index) {
        return rowItems.get(index);
    }

    /**
     * Returns a String object representing a specific row item in a TableFormatterRow object, padded with whitespace.
     * Row items start at index 0.
     *
     * @param index An integer that represents the location of the row item.
     * @param widthToAlignTo An integer that represents the total width that the item should be padded to align to.
     * @return A String object representing the padded row item.
     */
    private String padRowItem(int index, int widthToAlignTo) {
        StringBuilder paddedItem = new StringBuilder();
        if (index != 0) {
            paddedItem = new StringBuilder(" ");
        }
        paddedItem.append(rowItems.get(index));
        int paddingNecessary = widthToAlignTo - rowItems.get(index).length();
        assert paddingNecessary >= 0;
        paddedItem.append(" ".repeat(paddingNecessary));
        return String.valueOf(paddedItem.append(" "));
    }

    /**
     * Returns an ArrayList object containing each row item padded according to the integer array object
     * passed into the method.
     *
     * @param columnWidths An integer array object specifying the width each row item should be aligned to.
     * @return An ArrayList object containing each padded row item of this row.
     */
    public ArrayList<String> padRow(int[] columnWidths) {
        ArrayList<String> paddedRow = new ArrayList<>();
        for (int rowItemIndex = 0; rowItemIndex < columnWidths.length; ++rowItemIndex) {
            paddedRow.add(padRowItem(rowItemIndex, columnWidths[rowItemIndex]));
        }
        return paddedRow;
    }
}
