package cpp.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores Magic Constants.
 */
public class Constants {
    public static final String SEPARATOR = "____________________________________________________________";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n";
    public static final String NEGATIVE_INDEX = "The index is a negative number!\n";
    public static final String NON_INTEGER_INDEX = "The index is not an integer!\n";
    public static final String INDEX_OUT_OF_RANGE = "The target index is not found in the list.\n";
    public static final String UNKNOWN_COMMAND = SEPARATOR + "\n Unknown Command.\n" + SEPARATOR;
    public static final String MESSAGE_INVALID_TODO_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "todo [project_index] [todo_description]";
    public static final String MESSAGE_INVALID_MARK_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "mark [project_index] [todo_index]";
    public static final String MESSAGE_INVALID_ADDPROJECT_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "addproject [project_title]";
    public static final String MESSAGE_INVALID_DELETEPROJECT_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "deleteproject [project_title]";
    public static final String INVALID_PROJECT_NAME = "Sorry! There was no project with that name.";

    public static final String MESSAGE_INVALID_ADDDEADLINE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "adddeadline [project_title] [deadline]";
    public static final String MESSAGE_INVALID_ADDLANGUAGE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "addlanguage [project_title] [language_name]";
    public static final String MESSAGE_INVALID_LISTLANGUAGE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "listlanguages [project_title]";
    public static final String MESSAGE_INVALID_ADDMEMBER_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "addmember [project_title] [member_name] /c[contact_number] /e[email]";
    public static final String MESSAGE_INVALID_CHANGEGITHUBLINK_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "changegit [project_title] [website URL]";
    public static final String MESSAGE_INVALID_LINK_FORMAT =
            "Please make sure your link begins with the following:\n"
            + "http:// or https://";

    public static final int PROJECT_NOT_FOUND = -1;
    public static final int TWO_ARGUMENTS = 2;
    public static final int THREE_ARGUMENTS = 3;

    public static final String SUNDAY = "SUNDAY";
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SUNDAY";
    public static final Set<String> DAYS_OF_THE_WEEK = new HashSet<String>(Arrays.asList(
                SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY));
}
