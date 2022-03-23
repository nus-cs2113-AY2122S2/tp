package constants;

public class TextUIConstants {
    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A decorative prefix added to the beginning of lines printed by RecordBook */
    public static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    public static final String LS = System.lineSeparator();

    public static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    public static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    public static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    public static final String USERCOMMANDREQUEST = LINE_PREFIX + "Enter command: ";
}
