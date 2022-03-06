package seedu.duke.ui;

/**
 * Contains hardcoded strings such as error codes.
 *
 * @author Saurav
 */
public abstract class Message {
    public static final String ERRORCODE_EXAMPLE = "";
    public static final String LOGO = " $$$$$$\\            $$\\ $$\\   $$\\     $$\\           $$\\\n"
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
    public static final String HELP_MENU =
            "HELP MENU"; //TODO: Populate help menu
    
    public static final String ERROR_SESSION_INDEX_OUT_OF_RANGE_PERSON_LIST =
            "Please enter a number from 1 to ";
    public static final String ERROR_SESSION_EMPTY_PERSON_LIST =
            "The list of participants in the session is currently empty.";
    public static final String ERROR_SESSION_PERSON_NOT_IN_LIST =
            "The person that you have specified was not found in this session.";
    
    public static final String ERROR_PARSER_DELIMITER_NOT_FOUND =
            "Please include the following delimiter in your input: ";
    public static final String ERROR_PARSER_MISSING_ARGUMENT =
            "Please include an argument after the following delimiter: ";
    public static final String ERROR_PARSER_NON_INTEGER_ARGUMENT =
            "Please enter a valid integer after the following delimiter: ";
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
}
