package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.userfinancial.MindMyMoneySerializable;

import java.util.ArrayList;
import java.util.Scanner;

public class SerializerFunctions {

    public static final String SERIALIZATION_EXPENDITURE_START_MARKER = "# BEGIN EXPENDITURES";
    public static final String SERIALIZATION_CREDIT_CARD_START_MARKER = "# BEGIN CREDIT CARDS";
    public static final String SERIALIZATION_INCOME_START_MARKER = "# BEGIN INCOME SOURCES";
    public static final String SERIALIZATION_EXPENDITURE_END_MARKER = "# END EXPENDITURES";
    public static final String SERIALIZATION_CREDIT_CARD_END_MARKER = "# END CREDIT CARDS";
    public static final String SERIALIZATION_INCOME_END_MARKER = "# END INCOME SOURCES";

    public interface DeserializerFunction<T> {
        T apply(String s) throws MindMyMoneyException;
    }

    /**
     * Adds an ArrayList of objects that implement MMMSerializable line-by-line into a StringBuilder.
     * The list will have a start marker and an end marker placed before and after the list, respectively,
     * to help with deserializing this list.
     * @param startMarker A String marking the start of the list.
     * @param endMarker A String marking the end of the list.
     * @param list The list to add.
     * @param stringBuilder A StringBuilder to add to.
     * @param <T> A MMMSerializable type to add. <\T>
     */
    public static <T extends MindMyMoneySerializable>
        void addListToStringBuilder(String startMarker, String endMarker,
                                    ArrayList<T> list, StringBuilder stringBuilder) {
        stringBuilder.append(startMarker).append("\n");
        for (T serializable : list) {
            stringBuilder.append(serializable.serialize()).append("\n");
        }
        stringBuilder.append(endMarker).append("\n");
    }

    /**
     * Reads a list of MMMSerializables from a Scanner. The list should start with startMarker, and end
     * with endMarker. Each line in between will be passed to a deserializer function that converts
     * the line into an MMMSerializable.
     * @param startMarker A String marking the start of the list.
     * @param endMarker A String marking the end of the list.
     * @param scanner The Scanner to read from.
     * @param deserializer A function that accepts a string and deserializes it.
     * @param <T> A MMMSerializable type to convert to. <\T>
     * @return An ArrayList<T> of list elements. </T>
     */
    public static <T extends MindMyMoneySerializable>
        ArrayList<T> convertInputToList(String startMarker, String endMarker,
                                    Scanner scanner,
                                    DeserializerFunction<T> deserializer) throws MindMyMoneyException {
        ArrayList<T> list = new ArrayList<T>();
        if (!scanner.hasNextLine()) {
            return list;
        }
        String nextLine = scanner.nextLine();
        if (!nextLine.equals(startMarker)) {
            throw new MindMyMoneyException("Expected " + startMarker + ", got " + nextLine);
        }
        while (true) {
            if (!scanner.hasNextLine()) {
                throw new MindMyMoneyException("Expected " + endMarker + " , got EOF");
            }
            nextLine = scanner.nextLine();
            if (nextLine.equals(endMarker)) {
                break;
            }
            list.add(deserializer.apply(nextLine));
        }
        return list;
    }
}
