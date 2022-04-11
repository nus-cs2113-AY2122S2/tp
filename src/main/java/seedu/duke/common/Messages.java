package seedu.duke.common;

public class Messages {
    public static final String MISSING_ITEM_NUMBER_MESSAGE = "Sorry, you haven't entered an item number. "
            + "Please enter an item number!";
    public static final String INVALID_ITEM_NUMBER_MESSAGE = "Sorry, you haven't entered a valid item number. "
            + "Please enter a valid item number!";
    public static final String RETURNED_MESSAGE = "I've marked this item as returned.";
    public static final String EMPTY_ITEM_LIST_MESSAGE = "Your inventory is currently empty. Please add an item first!";
    public static final String ITEM_NUMBER_OUT_OF_RANGE_MESSAGE = "Sorry, the item number is out of range. "
            + " Please enter a different item number!";
    public static final String REPORTED_LOST_MESSAGE = "I've marked this item as lost and "
            + "updated the inventory accordingly!";
    public static final String REPORTED_LOST_AND_DELETED_MESSAGE = "I've marked this item as lost and "
            + "deleted it from the inventory!";
    public static final String LOST_ERROR_MESSAGE = "The quantity lost exceeds the item quantity!"
            + " Please enter a different item quantity.";
    public static final String RETURN_ERROR_MESSAGE = "There are no outstanding loans on this item. "
            + "Please select a different item to return!";
    public static final String INVALID_START_END_DATE = "Error: Incorrect start and end date order. "
            + "Please ensure that end date >= start date.";
    public static final String INVALID_INSUFFICIENT_QUANTITY = "Sorry. There is insufficient quantity. "
            + "Please reduce your quantity borrowed or try another date.";
    public static final String INVALID_BORROW_LOST_ITEM = "Sorry. The item is lost and "
            + "not available for borrowing.";
    public static final String INVALID_BORROW_DURATION = "Error. You can only borrow for a maximum of 7 days.";

    /** General messages. **/
    public static final String WELCOME_MESSAGE = "Welcome to Inventory Manager! What can I do for you today?";
    public static final String HELP_MESSAGE = "To find out more about my functions and how to call them,"
            + " key in 'help'.";
    public static final String DIVIDER = "===================================================";
    public static final String COMMAND_MENU_MESSAGE = "Here are my functionalities and how to call them: \n";
    public static final String ERROR_MESSAGE = "Sorry, there was an error!\n";
    public static final String INVALID_COMMAND = "I did not understand your command.\n" + HELP_MESSAGE;
    public static final String INVALID_INDEX = "Error. The index you entered was invalid!\n";
    public static final String INVALID_SYNTAX = "The syntax you entered for the command is invalid!";
    public static final String INVALID_QUANTITY = "The quantity you entered was invalid!\n";
    public static final String INVALID_DATE_FORMAT = "Error: Incorrect date format. Please enter "
            + "dates in YYYY-MM-DD format.";

    /** AddCommand messages. **/
    public static final String DUPLICATE_ITEM_MESSAGE = "There is already a similar item in the list!\n"
            + "Use edit command to edit the item's quantity/description instead.\n"
            + "Or change the name of the item to be more specific.";

    /** EditCommandParser messages. **/
    public static final String INVALID_RELATIVE_MESSAGE = "The relative multiplier you entered was invalid! "
            + "Only '+' and '-' is allowed.\n";
    public static final String INVALID_RELATIVE_WITHOUT_QUANTITY = "The relative multiplier must be used in "
            + "conjunction with a quantity!";

    /** EditCommand messages. **/
    public static final String NEGATIVE_QUANTITY_MESSAGE = "Sorry, the resulting new quantity for the specified item "
            + "would be negative! "
            + "Your command has been terminated; no changes were made.";
    public static final String OVERFLOW_QUANTITY_MESSAGE = "Sorry, the resulting new quantity for the specified item "
            + "would overflow! (too large) "
            + "Your command has been terminated; no changes were made.";

    /** Storage messages. **/
    public static final String WRITER_IO_ERROR = "An IO error occured while writing the data file. "
            + "Your data may or may not have been saved.";
    public static final String CREATE_FILE_IOERROR = "An IO error occured while creating the data file. "
            + "Any changes made while running will not be saved!";
    public static final String INVALID_PATH = "The given path has an invalid format.";
    public static final String JSON_PARSING_ERROR = "A JSON parsing error occured while reading the data file. "
            + "Check the format of the data file."
            + "If you would like to retain the data, exit the program immediately.";
    public static final String READ_FILE_IOERROR = "An IO error occured while reading the data file. "
            + "If you would like to retain the data, exit the program immediately.";


}
