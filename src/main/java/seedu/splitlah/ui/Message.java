package seedu.splitlah.ui;

import seedu.splitlah.command.ActivityCreateCommand;
import seedu.splitlah.command.ActivityDeleteCommand;
import seedu.splitlah.command.ActivityListCommand;
import seedu.splitlah.command.ActivityViewCommand;
import seedu.splitlah.command.ExitCommand;
import seedu.splitlah.command.HelpCommand;
import seedu.splitlah.command.SessionCreateCommand;
import seedu.splitlah.command.SessionDeleteCommand;
import seedu.splitlah.command.SessionListCommand;
import seedu.splitlah.command.SessionSummaryCommand;

/**
 * Container of hardcoded messages.
 * Naming convention: [TYPE]_[CLASS]_[MESSAGE].
 *
 * @author Saurav
 */
public abstract class Message {
    
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
                    + "        " + SessionListCommand.COMMAND_FORMAT + "\n"
                    + "4.  Create a new activity\n"
                    + "        Syntax: " + ActivityCreateCommand.COMMAND_FORMAT_FIRST + "\n"
                    + "                " + ActivityCreateCommand.COMMAND_FORMAT_SECOND + "\n"
                    + "5.  Delete an existing activity\n"
                    + "        " + ActivityDeleteCommand.COMMAND_FORMAT + "\n"
                    + "6.  List all existing activities\n"
                    + "        " + ActivityListCommand.COMMAND_FORMAT + "\n"
                    + "7.  View an existing activity's details\n"
                    + "        " + ActivityViewCommand.COMMAND_FORMAT + "\n"
                    + "8.  Show the summary of an existing session\n"
                    + "        " + SessionSummaryCommand.COMMAND_FORMAT + "\n"
                    + "9.  Show this help menu\n"
                    + "        " + HelpCommand.COMMAND_FORMAT + "\n"
                    + "10. Exit the program\n"
                    + "        " + ExitCommand.COMMAND_FORMAT;
    public static final String PROMPT_TEXTUI_REQUEST_CONFIRMATION =
            "Please enter Y/y/yes to confirm, or N/n/no otherwise.";
    public static final String ERROR_TEXTUI_REENTER_INPUT =
            "Invalid input. Please re-enter.";
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
    public static final String ERROR_PARSER_INVALID_GST_SURCHARGE =
            "Please enter a valid GST surcharge in % after the delimiter: ";
    public static final String ERROR_PARSER_INVALID_SERVICE_CHARGE =
            "Please enter a valid service charge in % after the delimiter: ";
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
    public static final String ASSERT_PARSER_COMMAND_ARGUMENTS_EMPTY =
            "The command arguments cannot be null.";

    // Person
    public static final String ERROR_PERSON_NO_ACTIVITIES =
            "This person is not participating in any activities.";
    public static final String ERROR_PERSON_ACTIVITY_NOT_FOUND =
            "This person is not participating in the activity with Id: ";

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
    public static final String LOGGER_PROFILE_SESSION_ADDED =
            "A session was added into the list of sessions with Id: ";
    public static final String LOGGER_PROFILE_SESSION_REMOVED =
            "A session was remove from the list of session with Id: ";

    // Activity
    public static final String ERROR_ACTIVITY_INACCURATE_INVOLVED_LIST =
            "The list of involved persons list is inaccurate "
                    + "as at least one person did not participate in the activity.";

    // Session Create Command
    public static final String ASSERT_SESSIONCREATE_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED =
            "Name duplicates exist but not detected.";
    public static final String ASSERT_SESSIONCREATE_PERSON_NAMES_ARRAY_EMPTY =
            "The array of person names is empty but was not handled in prepare function.";

    // Session Summary Command
    public static final String MESSAGE_SESSIONSUMMARY_NO_PAYMENTS_REQUIRED =
            "There are no payments to be made.";
    public static final String ASSERT_SESSIONSUMMARY_INVALID_PERSONCOSTPAIR_LIST =
            "The generated personCostPairList is invalid with a non-zero total sum of debt.";
    public static final String ASSERT_SESSIONSUMMARY_PAYER_EXPECTS_FROM_RECEIVER =
            "Payer has a greater total cost than receiver.";
    
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

    // Activity List Command
    public static final String ERROR_ACTIVITYLIST_ACTIVITY_EMPTY =
            "There are currently no activities in this session.";

}
