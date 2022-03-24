package cpp;

/**
 * Stores Magic Constants.
 */
public class Constants {
    public static final String SEPARATOR = "____________________________________________________________";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n";
    public static final String NEGATIVE_INDEX = "The index is a negative number! \n";
    public static final String NON_INTEGER_INDEX = "The index is not an integer! \n";
    public static final String INDEX_OUT_OF_RANGE = "The target index is not found in the list. \n";
    public static final String UNKNOWN_COMMAND = SEPARATOR + "\n Unknown Command. \n" + SEPARATOR;
    public static final String MESSAGE_INVALID_TODO_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be: \n"
            + "todo [project_index] [todo_description]";
    public static final String MESSAGE_INVALID_MARK_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be: \n"
            + "mark [project_index] [todo_index]";
    public static final String MESSAGE_INVALID_ADDPROJECT_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be: \n"
            + "addproject [project_title]";
    public static final String MESSAGE_INVALID_DELETEPROJECT_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be: \n"
            + "deleteproject [project_title]";



    public static final int PROJECT_NOT_FOUND = -1;
    public static final int TWO_ARGUMENTS = 2;
    public static final int THREE_ARGUMENTS = 3;
}
