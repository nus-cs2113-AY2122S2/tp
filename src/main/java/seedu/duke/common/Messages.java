package seedu.duke.common;

public class Messages {
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
    public static final String INVALID_RELATIVE_MESSAGE = "The relative multiplier you entered was invalid! "
            + "Only '+' and '-' is allowed.\n";
    public static final String INVALID_RELATIVE_WITHOUT_QUANTITY = "The relative multiplier must be used in "
            + "conjunction with a quantity!";
    public static final String INVALID_DATE_FORMAT = "Error: Incorrect date format. Please enter "
            + "dates in YYYY-MM-DD format.";
    public static final String NEGATIVE_QUANTITY_MESSAGE = "Sorry, the resulting new quantity for the specified item "
            + "would be negative! "
            + "Your command has been terminated; no changes were made.";
    public static final String MISSING_ITEM_NUMBER_MESSAGE = "Sorry, you haven't entered an item number. "
            + "Please enter an item number!";
    public static final String INVALID_ITEM_NUMBER_MESSAGE = "Sorry, you haven't entered a valid item number. "
            + "Please enter a valid item number!";
    public static final String RETURNED_MESSAGE = "I've marked this item as returned.";
    public static final String EMPTY_ITEM_LIST_MESSAGE = "Your inventory is currently empty. Please add an item first!";
    public static final String ITEM_NUMBER_OUT_OF_RANGE_MESSAGE = "Sorry, the item number is out of range. "
            + "Please enter a different item number!";
    public static final String RETURN_ERROR_MESSAGE = "There are no outstanding loans on this item. "
            + "Please select a different item to return!";
    public static final String REPORTED_LOST_MESSAGE = "I've marked this item as lost.";
    public static final String INVALID_START_END_DATE = "Error: Incorrect start and end date order. "
            + "Please ensure that end date >= start date.";
    public static final String INVALID_INSUFFICIENT_QUANTITY = "Sorry. There is insufficient quantity. "
            + "Please reduce your quantity borrowed or try another date.";
    public static final String INVALID_BORROW_LOST_ITEM = "Sorry. The item is lost and "
            + "not available for borrowing.";
    public static final String INVALID_BORROW_DURATION = "Error. You can only borrow for a maximum of 7 days.";
}
