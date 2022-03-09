package seedu.duke;

public class Errors {
    public enum ErrorTypes {
        INVALID_LIST_INDEX_DELETE,INVALID_LIST_INDEX_DELETE_STRING_DETECTED
    }

    public static final String INVALID_LIST_INDEX_DELETE = " Hi I need a valid index in the list so I can delete";
    public static final String INVALID_LIST_INDEX_DELETE_STRING_DETECTED = " Hi I did not receive a proper "
            + "integer index that I can parse";
}
