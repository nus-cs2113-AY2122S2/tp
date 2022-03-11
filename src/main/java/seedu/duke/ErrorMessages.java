package seedu.duke;

public class ErrorMessages {
//    public enum ErrorTypes {
//        INVALID_LIST_INDEX_DELETE,INVALID_LIST_INDEX_DELETE_STRING_DETECTED
//    }

    // can generalise error messages to all commands that requires index
    public static final String ERROR_INDEX_OUT_OF_BOUND = " Hi I need a valid index in the list so I can delete";
    public static final String ERROR_INVALID_INDEX_FORMAT = " Hi I did not receive a proper "
            + "integer index that I can parse";
}
