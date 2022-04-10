package cpp.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores Magic Constants.
 */
public class Constants {
    public static final String FILEPATH = "./src/data/projectList.txt";
    public static final String DIRECTORYPATH = "./src/data";
    public static final String SEPARATOR = "____________________________________________________________";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n";
    public static final String NO_COMMAND_ENTERED = "No command is entered.";
    public static final String NEGATIVE_INDEX = "Unsuccessful. The index is zero or a negative number!";
    public static final String NON_INTEGER_INDEX = "Unsuccessful. The index is not an integer!";
    public static final String INDEX_PARSING_ERROR = "Unsuccessful. Cannot parse the index.\n "
            + "Please check if the index is too large or it is not a number.";
    public static final String INDEX_OUT_OF_RANGE = "Unsuccessful. The target index is not found in the list.";
    public static final String NO_LANGUAGES_ADDED = "No languages have been added to this project.\n";
    public static final String EMPTY_LANGUAGES = "Unsuccessful. The language name cannot be empty.\n";
    public static final String UNKNOWN_COMMAND = "Unknown Command.";
    public static final String INVALID_INDEX = "The entered index is invalid.\n";
    public static final String MESSAGE_NO_DEADLINE = "No deadline specified";
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String MESSAGE_INVALID_TODO_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "todo [project_index] [todo_description]";
    public static final String MESSAGE_INVALID_MARK_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "mark [project_index] [todo_index]";
    public static final String MESSAGE_INVALID_ADDPROJECT_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "addproject [project_name]";
    public static final String MESSAGE_INVALID_DELETEPROJECT_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "deleteproject [project_name]";
    public static final String INVALID_PROJECT_NAME = "Sorry! There was no project with that name.";
    public static final String MESSAGE_INVALID_PROJDEADLINE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "projdeadline [project_index] [deadline (yyyy-mm-dd)]";
    public static final String MESSAGE_INVALID_TODODEADLINE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "tododeadline [project_index] [todo_index] [deadline (yyyy-mm-dd)]";
    public static final String MESSAGE_INVALID_ADDLANGUAGE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "addlanguage [project_name] [language_name]";
    public static final String MESSAGE_INVALID_LISTLANGUAGE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "listlanguages [project_name]";
    public static final String MESSAGE_INVALID_CHANGEGITHUBLINK_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "changegit [project_index] [website URL]";
    public static final String MESSAGE_INVALID_LINK_FORMAT =
            "Please make sure your link begins with the following:\n"
            + "http:// or https://";
    public static final String MESSAGE_INVALID_OPENGIT_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT
            + "The correct format should be:\n"
            + "opengit [project_index]";

    public static final int PROJECT_NOT_FOUND = -1;
    public static final int TWO_ARGUMENTS = 2;
    public static final int THREE_ARGUMENTS = 3;
    public static final int FOUR_ARGUMENTS = 4;

    public static final String SUNDAY = "SUNDAY";
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final Set<String> DAYS_OF_THE_WEEK = new HashSet<String>(Arrays.asList(
                SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY));
}
