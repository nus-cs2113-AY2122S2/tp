package seedu.splitlah.ui;

import java.util.ArrayList;
import java.util.Arrays;

public class TableFormatterRow {

    private ArrayList<String> rowItems;

    public TableFormatterRow(String... rowItems) {
        this.rowItems = new ArrayList<>(Arrays.asList(rowItems));
    }

    public int getRowLength() {
        return rowItems.size();
    }
    /**
     * Returns an item located at a specific index in a TableFormatterRow object. Row items start at index 0.
     *
     * @param index The location of the row item.
     * @return A String object representing the row item.
     */

    public String getItem(int index) {
        return rowItems.get(index);
    }

    /**
     * Returns an item located at a specific index in a TableFormatterRow object with spaces to pad it on both sides.
     * Row items start at index 0.
     *
     * @param index The location of the row item.
     * @param padding The number of padding spaces to append to the row item.
     * @return A String object representing the padded row item.
     */
    private String padRowItem(int index, int padding) {
        StringBuilder paddedItem = new StringBuilder();
        if (index != 0) {
            paddedItem = new StringBuilder(" ");
        }
        paddedItem.append(rowItems.get(index));
        int paddingNecessary = padding - rowItems.get(index).length();
        assert paddingNecessary >= 0;
        paddedItem.append(" ".repeat(paddingNecessary));
        return String.valueOf(paddedItem.append(" "));
    }

    /**
     * Returns an ArrayList object containing each row item padded according to the array object passed into the method.
     *
     * @param padding An array specifying how much each row item should be padded.
     * @return An ArrayList object containing each padded row item of this row.
     */
    public ArrayList<String> padRow(int[] padding) {
        ArrayList<String> paddedRow = new ArrayList<>();
        for (int rowItem = 0; rowItem < padding.length; ++rowItem) {
            paddedRow.add(padRowItem(rowItem, padding[rowItem]));
        }
        return paddedRow;
    }
}
