package seedu.meetingjio.common;

public class ErrorMessages {

    public static final String ERROR_INDEX_OUT_OF_BOUND = "Hi I need a valid index in the list so I can delete";
    public static final String ERROR_INVALID_INDEX_FORMAT = "Hi I did not receive a proper "
            + "integer that I can parse";
    public static final String ERROR_MISSING_PARAMETERS = "Hi 1 or more parameters are missing\n"
            + "The required parameters are:\n"
            + "n/[Name]\n"
            + "l/[Lesson]\n"
            + "d/[Day]\n"
            + "st/[StartTime]\n"
            + "et/[EndTime]\n"
            + "m/[Mode]";
    public static final String ERROR_MISSING_VALUES = "Hi 1 or more parameters have missing values\n"
            + "The parameters that require values are:\n"
            + "n/[Name]\n"
            + "l/[Lesson]\n"
            + "d/[Day]\n"
            + "st/[StartTime]\n"
            + "et/[EndTime]\n"
            + "m/[Mode]";
    public static final String ERROR_INVALID_TIME = "Hi please input a valid time where "
            + "StartTime must be before EndTime\n"
            + "Accepted inputs are: 0000 - 2359";
    public static final String ERROR_INVALID_DAY = "Hi please input a valid day\n"
            + "Accepted inputs are:\n"
            + "monday\n"
            + "tuesday\n"
            + "wednesday\n"
            + "thursday\n"
            + "friday\n"
            + "saturday\n"
            + "sunday";
    public static final String ERROR_INVALID_MODE = "Hi please input a valid mode\n"
            + "Accepted inputs are: online or physical";
    public static final String ERROR_EMPTY_LIST = "There are no lessons in your timetable yet!";
    public static final String ERROR_DUPLICATE_EVENT = "This event already exists. "
            + "This event will not be not added to the timetable.";
    public static final String ERROR_OVERLAPPING_EVENT = "This event coincides with another event. "
            + "This event will not be added to the timetable.";
    public static final String ERROR_INIT_FAILED = "Failed to initialise MeetingJio application. Exiting...";
    public static final String ERROR_INVALID_COMMAND = "Hi please input a valid command.";
}
