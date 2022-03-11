package seedu.splitlah.ui;

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
            "HELP MENU"; //TODO: Populate help menu
    public static final String PROMPT_TEXTUI_REQUEST_CONFIRMATION =
            "Please enter Y/y/yes to confirm, or N/n/no otherwise.";
    public static final String ERROR_TEXTUI_USER_DID_NOT_CONFIRM =
            "User did not confirm.";
    public static final String ERROR_TEXTUI_REENTER_INPUT =
            "Invalid input. Please re-enter.";
    public static final String PERSON_TEXTUI_NO_ACTIVITIES =
            "No activities found.";
    public static final String PROMPT_TEXTUI_AWAITING_INPUT =
            ">";

    // Parser
    public static final String ERROR_PARSER_DELIMITER_NOT_FOUND =
            "Please include the following delimiter in your input: ";
    public static final String ERROR_PARSER_MISSING_ARGUMENT =
            "Please include an argument after the following delimiter: ";
    public static final String ERROR_PARSER_NON_INTEGER_ARGUMENT =
            "Please enter a valid integer after the following delimiter: ";
    public static final String ERROR_PARSER_ID_VALUE_NOT_POSITIVE =
            "Please enter a positive value for identifier numbers.";
    public static final String ERROR_PARSER_NON_MONETARY_VALUE_ARGUMENT =
            "Please enter a valid monetary value(s) after the following delimiter: ";
    public static final String ERROR_PARSER_INVALID_GST_SURCHARGE =
            "Please enter a valid GST surcharge in % after the delimiter: ";
    public static final String ERROR_PARSER_INVALID_SERVICE_CHARGE =
            "Please enter a valid service charge in % after the delimiter: ";
    public static final String ERROR_PARSER_EMPTY_COMMAND =
            "Please enter a command.";
    public static final String ERROR_PARSER_INVALID_COMMAND =
            "Please enter a valid command.";
    public static final String ERROR_PARSER_INVALID_DELIMITERS =
            "The command contains one or more invalid delimiters. Please rectify and try again.";

    // Person
    public static final String ERROR_PERSON_NO_ACTIVITIES =
            "This person is not participating in any activities.";
    public static final String ERROR_PERSON_ACTIVITY_NOT_FOUND =
            "Activity not found.";

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
    
    // Profile
    public static final String ERROR_PROFILE_DUPLICATE_SESSION =
            "There is another session with the same name, please try another name.";
    public static final String ERROR_PROFILE_DUPLICATE_NAME =
            "There seems to be duplicates in the session you are trying to create, please try again.";
    public static final String ERROR_PROFILE_SESSION_LIST_EMPTY =
            "There are no sessions currently stored.";
    public static final String ERROR_PROFILE_SESSION_NOT_IN_LIST =
            "The session that you have specified was not found.";

    // Activity
    public static final String ERROR_ACTIVITY_INACCURATE_INVOLVED_LIST =
            "The list of involved persons list is inaccurate "
                    + "as at least one person did not participate in the activity.";

    // Session Summary Command
    public static final String MESSAGE_SESSIONSUMMARY_NO_PAYMENTS_REQUIRED =
            "There are no payments to be made.";
    
    // Activity Create Command
    public static final String ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH =
            "There seems to be a discrepancy between number of people involved and the costs per person";
    public static final String ERROR_ACTIVITYCREATE_HAS_BOTH_COST_AND_COST_LIST =
            "Please only include either a total cost or a list of costs";
    public static final String ERROR_ACTIVITYCREATE_MISSING_COST_AND_COST_LIST =
            "Please include either a cost or a list of costs.";

    //Activity List Command
    public static final String ERROR_ACTIVITYLIST_ACTIVITY_EMPTY =
            "There are currently no activities in this session";
}
