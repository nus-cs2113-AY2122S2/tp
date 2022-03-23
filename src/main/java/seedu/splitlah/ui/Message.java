package seedu.splitlah.ui;

import seedu.splitlah.command.ActivityCreateCommand;
import seedu.splitlah.command.ActivityDeleteCommand;
import seedu.splitlah.command.ActivityListCommand;
import seedu.splitlah.command.ActivityViewCommand;
import seedu.splitlah.command.ExitCommand;
import seedu.splitlah.command.GroupCreateCommand;
import seedu.splitlah.command.GroupDeleteCommand;
import seedu.splitlah.command.GroupListCommand;
import seedu.splitlah.command.GroupViewCommand;
import seedu.splitlah.command.SessionCreateCommand;
import seedu.splitlah.command.SessionDeleteCommand;
import seedu.splitlah.parser.commandparser.HelpCommandParser;
import seedu.splitlah.parser.commandparser.SessionListCommandParser;
import seedu.splitlah.parser.commandparser.SessionSummaryCommandParser;

/**
 * Container of hardcoded messages.
 * Naming convention: [TYPE]_[CLASS]_[MESSAGE].
 *
 * @author Saurav
 */
public abstract class Message {

    // SplitLah
    public static final String LOGGER_SPLITLAH_APPLICATION_EXIT =
            "The application is terminated";

    // TextUI
    public static final String ASCII_TEXTUI_LOGO =
            " $$$$$$\\            $$\\ $$\\   $$\\     $$\\           $$\\\n"
            + "$$  $$\\           $$ |\\|  $$ |    $$ |          $$ |\n"
            + "$$ /  \\__| $$$$$$\\  $$ |$$\\ $$$$$$\\   $$ | $$$$$$\\  $$$$$$$\\\n"
            + "\\$$$$$$\\  $$  $$\\ $$ |$$ |\\_$$  _|  $$ | \\____$$\\ $$  __$$\\\n"
            + " \\____$$\\ $$ /  $$ |$$ |$$ |  $$ |    $$ | $$$$$$$ |$$ |  $$ |\n"
            + "$$\\   $$ |$$ |  $$ |$$ |$$ |  $$ |$$\\ $$ |$$  __$$ |$$ |  $$ |\n"
            + "\\$$$$$$  |$$$$$$$  |$$ |$$ |  \\$$$$  |$$ |\\$$$$$$$ |$$ |  $$ |\n"
            + " \\______/ $$  ____/ \\|\\__|   \\____/ \\__| \\_______|\\__|  \\__|\n"
            + "          $$ |\n"
            + "          $$ |\n"
            + "          \\__|";
    public static final String MESSAGE_TEXTUI_HELP_MENU =
            "HELP MENU --\n"
                    + "1.  Create a new session\n"
                    + "        " + SessionCreateCommand.COMMAND_FORMAT + "\n"
                    + "2.  Delete an existing session\n"
                    + "        " + SessionDeleteCommand.COMMAND_FORMAT + "\n"
                    + "3.  List all existing sessions\n"
                    + "        " + SessionListCommandParser.COMMAND_FORMAT + "\n"
                    + "4.  Create a new activity\n"
                    + "        Syntax: " + ActivityCreateCommand.COMMAND_FORMAT_FIRST + "\n"
                    + "                " + ActivityCreateCommand.COMMAND_FORMAT_SECOND + "\n"
                    + "5.  Delete an existing activity\n"
                    + "        " + ActivityDeleteCommand.COMMAND_FORMAT + "\n"
                    + "6.  View an existing activity's details\n"
                    + "        " + ActivityViewCommand.COMMAND_FORMAT + "\n"
                    + "7.  List all existing activities\n"
                    + "        " + ActivityListCommand.COMMAND_FORMAT + "\n"
                    + "8.  Show the summary of an existing session\n"
                    + "        " + SessionSummaryCommandParser.COMMAND_FORMAT + "\n"
                    + "9.  Create a new group\n"
                    + "        " + GroupCreateCommand.COMMAND_FORMAT + "\n"
                    + "10. Delete an existing group\n"
                    + "        " + GroupDeleteCommand.COMMAND_FORMAT + "\n"
                    + "11. View an existing group's details\n"
                    + "        " + GroupViewCommand.COMMAND_FORMAT + "\n"
                    + "12. List all existing groups\n"
                    + "        " + GroupListCommand.COMMAND_FORMAT + "\n"
                    + "13.  Show this help menu\n"
                    + "        " + HelpCommandParser.COMMAND_FORMAT + "\n"
                    + "14. Exit the program\n"
                    + "        " + ExitCommand.COMMAND_FORMAT;
    public static final String PROMPT_TEXTUI_AWAITING_INPUT =
            "> ";

    // Parser
    public static final String ERROR_PARSER_DELIMITER_NOT_FOUND =
            "Please include the following delimiter in your input: ";
    public static final String ERROR_PARSER_MISSING_ARGUMENT =
            "Please include an argument after the following delimiter: ";
    public static final String ERROR_PARSER_MORE_THAN_ONE_PAYER =
            "The activity should only have a single payer. Please rectify and try again.";
    public static final String ERROR_PARSER_NON_INTEGER_ARGUMENT =
            "Please enter a valid integer after the following delimiter: ";
    public static final String ERROR_PARSER_ID_VALUE_NOT_POSITIVE =
            "Please enter a positive value for identifier numbers.";
    public static final String ERROR_PARSER_INVALID_DATE_FORMAT =
            "Please enter the date in the form DD-MM-YYYY or enter 'today' for today's date.";
    public static final String ERROR_PARSER_NON_MONETARY_VALUE_ARGUMENT =
            "Please enter a valid monetary value(s) after the following delimiter: ";
    public static final String ERROR_PARSER_COST_NOT_POSITIVE =
            "Please enter a positive value for monetary value(s).";
    public static final String ERROR_PARSER_COST_NOT_TWO_DP =
            "Please enter a value up to 2 decimal places for monetary value(s).";
    public static final String ERROR_PARSER_COST_MORE_THAN_TWELVE_DIGITS_BEFORE_DP =
            "Please enter a value up to 12 digits in dollars for monetary value(s).";
    public static final String ERROR_PARSER_NON_PERCENTAGE_ARGUMENT =
            "Please enter a valid percentage value after the delimiter: ";
    public static final String ERROR_PARSER_PERCENTAGE_NEGATIVE =
            "Please enter a non-negative percentage value.";
    public static final String ERROR_PARSER_PERCENTAGE_NOT_TWO_DP =
            "Please enter a value up to 2 decimal places for percentage values.";
    public static final String ERROR_PARSER_PERCENTAGE_MORE_THAN_THREE_DIGITS_BEFORE_DP =
            "Please enter a value up to 3 digits before the decimal point for percentage values.";
    public static final String ERROR_PARSER_INVALID_GST_SURCHARGE =
            "Please enter a valid GST surcharge in % in the range [0, 100] after the delimiter: ";
    public static final String ERROR_PARSER_INVALID_SERVICE_CHARGE =
            "Please enter a valid service charge in % in the range [0, 100] after the delimiter: ";
    public static final String ERROR_PARSER_EMPTY_COMMAND =
            "Please enter a command.";
    public static final String ERROR_PARSER_INVALID_COMMAND =
            "Please enter a valid command.";
    public static final String ERROR_PARSER_ADDITIONAL_INVALID_TOKEN =
            "The command contains an argument that does not have a delimiter. Please rectify and try again.";
    public static final String ERROR_PARSER_INVALID_DELIMITERS =
            "The command contains one or more invalid delimiters. Please rectify and try again.";
    public static final String ERROR_PARSER_DUPLICATE_DELIMITERS =
            "The command contains one or more duplicate delimiters. Please rectify and try again.";
    public static final String ASSERT_PARSER_COMMAND_TYPE_NULL =
            "The command type cannot be null.";
    public static final String ASSERT_PARSER_COMMAND_ARGUMENTS_NULL =
            "The command arguments cannot be null.";
    public static final String ASSERT_PARSER_TOKEN_INPUT_NULL =
            "The token input cannot be null.";
    public static final String ASSERT_PARSER_DELIMITER_NULL =
            "The delimiter cannot be null.";
    public static final String ASSERT_PARSER_PARAMETERS_NULL =
            "The input parameters cannot be null.";
    public static final String ASSERT_PARSER_PLACES_NEGATIVE =
            "A number cannot have less than 0 digits before the integer position.";

    // ActivityCost
    public static final String LOGGER_ACTIVITYCOST_CONSTRUCT_WITH_DEFAULT_PARAMS =
            "Constructing ActivityCost with default parameters";
    public static final String LOGGER_ACTIVITYCOST_CONSTRUCT_WITH_ACTIVITYID =
            "Constructing ActivityCost with activityId";
    public static final String LOGGER_ACTIVITYCOST_CONSTRUCT_WITH_ALL_PARAMS =
            "Constructing ActivityCost with activityId, costPaid and costOwed";

    // Person
    public static final String ERROR_PERSON_NO_ACTIVITIES =
            "This person is not participating in any activities.";
    public static final String ERROR_PERSON_ACTIVITY_NOT_FOUND =
            "This person is not participating in the activity with Id: ";

    // PersonList
    public static final String ASSERT_PERSONLIST_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED =
            "Name duplicates exist but not detected.";

    // Name
    public static final String ERROR_NAME_INVALID_NAME =
            "Invalid name. Names must contain only alphabetical characters.";

    // Session
    public static final String ERROR_SESSION_INDEX_OUT_OF_RANGE_PERSON_LIST =
            "Please enter a number from 1 to ";
    public static final String ERROR_SESSION_EMPTY_ACTIVITY_LIST =
            "The list of activities in the session is currently empty.";
    public static final String ERROR_SESSION_ACTIVITY_ID_NOT_IN_LIST =
            "The activity that you have specified was not found in this session.";
    public static final String ERROR_SESSION_EMPTY_PERSON_LIST =
            "The list of participants in the session is currently empty.";
    public static final String ERROR_SESSION_PERSON_NOT_IN_LIST =
            "The person that you have specified was not found in this session.";
    public static final String ASSERT_SESSION_PERSON_LIST_EMPTY =
            "Session object cannot be constructed with a null or empty person list.";

    // Profile
    public static final String ERROR_PROFILE_DUPLICATE_SESSION =
            "There is another session with the same name. Please rename and try again.";
    public static final String ERROR_PROFILE_DUPLICATE_NAME =
            "There are duplicate names in the person list for the session you are trying to create. "
                    + "Please rectify and try again.";
    public static final String ERROR_PROFILE_SESSION_LIST_EMPTY =
            "There are currently no sessions stored.";
    public static final String ERROR_PROFILE_SESSION_NOT_IN_LIST =
            "The session that you have specified was not found.";
    public static final String ERROR_PROFILE_GROUP_LIST_EMPTY =
            "There are currently no groups stored.";
    public static final String ERROR_PROFILE_GROUP_NOT_IN_LIST =
            "The group that you have specified was not found.";

    // Storage
    public static final String ERROR_STORAGE_FILE_NOT_FOUND =
            "The data file was not found or has been corrupted. No saves were loaded into SplitLah.";
    public static final String ERROR_STORAGE_PATH_LOCATION_CREATION_FAILED =
            "A new data file could not be created. No data would be saved while using SplitLah.";
    public static final String ERROR_STORAGE_DATA_NOT_SAVED =
            "An error occurred while saving. No data was saved.";
    public static final String ERROR_STORAGE_CLASS_EXCEPTION_ISSUE =
            "It seems that something went wrong internally. No saves were loaded into SplitLah.";
    public static final String LOGGER_STORAGE_CLASS_NOT_FOUND =
            "An internal error of ClassNotFoundException has occurred.";
    public static final String LOGGER_STORAGE_FILE_ERROR =
            "There was an error in retrieving data from save file.";

    // Activity
    public static final String ERROR_ACTIVITY_INACCURATE_INVOLVED_LIST =
            "The list of involved persons list is inaccurate "
                    + "as at least one person did not participate in the activity.";

    // Invalid Command
    public static final String ASSERT_INVALIDCOMMAND_MANAGER_DOES_NOT_EXIST =
            "Manager does not exist.";

    // Session Create Command
    public static final String ERROR_SESSIONCREATE_MISSING_PERSONLIST_AND_GROUP_DELIMITERS =
            "The person list or group identifier delimiters are missing.";
    public static final String ASSERT_SESSIONCREATE_SESSION_NAME_NULL =
            "Session name is empty but was not handled in prepare function.";
    public static final String ASSERT_SESSIONCREATE_SESSION_DATE_NULL =
            "Session data is empty but was not handled in prepare function.";
    public static final String ASSERT_SESSIONCREATE_MISSING_PERSONLIST_AND_GROUP_DELIMITERS =
            "The person list or group identifier delimiters are missing but was not handled in prepare function.";
    public static final String LOGGER_SESSIONCREATE_SESSION_ADDED =
            "A session was added into the list of sessions with Id: ";

    // Session Delete Command
    public static final String LOGGER_SESSIONDELETE_SESSION_REMOVED =
            "A session was removed from the list of session with Id: ";

    // Session Summary Command
    public static final String MESSAGE_SESSIONSUMMARY_NO_PAYMENTS_REQUIRED =
            "There are no payments to be made.";
    public static final String ASSERT_SESSIONSUMMARY_INVALID_PERSONCOSTPAIR_LIST =
            "The generated personCostPairList is invalid with a non-zero total sum of debt.";
    public static final String ASSERT_SESSIONSUMMARY_PAYER_EXPECTS_FROM_RECEIVER =
            "Payer has a greater total cost than receiver.";
    public static final String LOGGER_SESSIONSUMMARY_SESSION_ID_NOT_FOUND =
            "A session summary was not produced as a Session object with the following Id was not found :";
    public static final String LOGGER_SESSIONSUMMARY_SESSION_SUMMARY_PRINTED =
            "A session summary has been successful produced for the Session object with the following Id: ";

    // Activity Create Command
    public static final String ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH =
            "There is a mismatch between persons involved and the costs for each person. Please rectify and try again.";
    public static final String ERROR_ACTIVITYCREATE_HAS_BOTH_COST_AND_COST_LIST =
            "Please only include either a total cost or a list of costs.";
    public static final String ERROR_ACTIVITYCREATE_MISSING_COST_AND_COST_LIST =
            "Please include either a cost or a list of costs.";
    public static final String ERROR_ACTIVITYCREATE_DUPLICATE_NAME =
            "There are duplicate names in the persons involved for the activity you are trying to create. "
                    + "Please rectify and try again.";
    public static final String ASSERT_ACTIVITYCREATE_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED =
            "Name duplicates exists but not detected.";
    public static final String ASSERT_ACTIVITYCREATE_SESSION_ID_LESS_THAN_ONE =
            "Session ID is less than or equals to zero.";
    public static final String ASSERT_ACTIVITYCREATE_ACTIVITY_NAME_MISSING =
            "Activity name is missing.";
    public static final String ASSERT_ACTIVITYCREATE_TOTAL_COST_LESS_THAN_ONE =
            "Total cost is less than or equals to zero.";
    public static final String ASSERT_ACTIVITYCREATE_PAYER_NAME_MISSING =
            "Payer's name is missing.";
    public static final String ASSERT_ACTIVITYCREATE_INVOLVED_LIST_ARRAY_NULL =
            "Involved list array does not contain the list of names of the persons involved in the activity.";
    public static final String ASSERT_ACTIVITYCREATE_COST_LIST_ARRAY_NULL =
            "Cost list array does not contain the respective costs of the persons involved in the activity.";
    public static final String LOGGER_ACTIVITYCREATE_ACTIVITY_ADDED =
            "An Activity object was succesfully added into the list of activities with Id: ";
    public static final String LOGGER_ACTIVITYCREATE_FAILED_ADDING_ACTIVITY =
            "An Activity object failed to be added into the list of activities due to the following error: ";
    public static final String LOGGER_ACTIVITYCREATE_DUPLICATE_NAMES_IN_INVOLVED_LIST =
            "An Activity object failed to be added into the list of activities because there are duplicate names in"
                    + "the involved list.";

    // Activity Edit Command
    public static final String LOGGER_ACTIVITYEDIT_SESSION_ID_NOT_FOUND =
            "Session was not found with unique identifier of:";
    public static final String ASSERT_ACTIVITYEDIT_COMMAND_ARGS_NULL =
            "The command arguments cannot be null.";
   public static final String ASSERT_ACTIVITYEDIT_MANAGER_DOES_NOT_EXIST =
            "Manager does not exist.";
    public static final String ASSERT_ACTIVITYEDIT_SESSIONID_MISSING =
            "Session Id missing.";
    public static final String ASSERT_ACTIVITYEDIT_ACTIVITYID_MISSING =
            "Activity Id missing.";

    // Activity Delete Command
    public static final String ASSERT_ACTIVITYDELETE_SESSION_IS_NULL =
            "Session is still not initialized.";
    public static final String ASSERT_ACTIVITYDELETE_SESSION_ID_NOT_INITIALIZED =
            "Session Id not initialized.";
    public static final String ASSERT_ACTIVITYDELETE_ACTIVITY_ID_NOT_INITIALIZED =
            "Activity Id not initialized.";
    public static final String LOGGER_ACTIVITYDELETE_ACTIVITY_REMOVED =
            "An Activity object was successfully deleted from the list of activities with Id: ";

    // Activity List Command
    public static final String ERROR_ACTIVITYLIST_ACTIVITY_EMPTY =
            "There are currently no activities in this session.";
    public static final String ASSERT_ACTIVITYLIST_SESSION_ID_LESS_THAN_ONE =
            "Session unique identifier is less than one";
    public static final String LOGGER_ACTIVITYLIST_SESSION_ID_NOT_FOUND =
            "Session was not found with unique identifier of:";

    // Group
    public static final String ERROR_GROUP_EMPTY_PERSON_LIST =
            "There are currently no person in this group.";
    public static final String ERROR_GROUP_PERSON_NOT_IN_LIST =
            "This person is not in the group.";

    // Group Create Command
    public static final String ERROR_GROUPCREATE_DUPLICATE_GROUP_NAME =
            "There is another group with the same name.";
    public static final String ERROR_GROUPCREATE_DUPLICATE_NAMES =
            "There are duplicate names in the person list for the group you are trying to create.";
    public static final String ERROR_GROUPCREATE_DUPLICATE_GROUP_ID =
             "There is another group with the same Id.";
    public static final String ASSERT_GROUPCREATE_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED =
             "Name duplicates exists but not detected.";

    // Group View Command
    public static final String ASSERT_GROUPVIEW_GROUP_ID_NOT_INITIALIZED =
            "Group Id is not initialized.";
    public static final String ASSERT_GROUPVIEW_INCORRECT_GROUP =
            "The group returned is incorrect.";
    public static final String ASSERT_GROUPVIEW_GROUP_ID_LESS_THAN_ONE =
            "Group unique identifier is less than one";
    public static final String LOGGER_GROUPVIEW_GROUP_VIEWED =
            "A Group object was successfully viewed from the list of groups with Id: ";
    public static final String LOGGER_GROUPVIEW_GROUP_NOT_VIEWED =
            "A Group object was unable to be viewed from the list of groups with Id: ";

    // Group List Command
    public static final String ASSERT_GROUPLIST_GROUP_SIZE_NOT_ZERO =
            "The list of groups to be printed is not empty.";
    public static final String ASSERT_GROUPLIST_GROUP_SIZE_LESS_THAN_ONE =
            "The size of the list of groups to be printed is less than one.";
    public static final String LOGGER_GROUPLIST_GROUPS_NOT_LISTED =
            "There are no groups to be listed.";
    public static final String LOGGER_GROUPLIST_GROUPS_LISTED =
            "All the groups have been listed.";
}
