package seedu.meetingjio.common;

/**
 *  Container for user visible messages.
 */
public class Messages {
    public static final String LOGO = " ___      ___ __________ __________ __________ ___ ___    "
            + "___ __________ ____________ ___ ____________ \n"
            + "|   \\    /   |  ________|  ________|___    ___|   |   \\  "
            + "|   |  ________|____    ____|   |   ______   |\n"
            + "|    \\  /    |  |____   |  |____       |  |   |   |    \\ |   "
            + "|  |  _____     |  |    |   |  |      |  |\n"
            + "|  |\\ \\/ /|  |   ____|  |   ____|      |  |   |   |   |\\\\|   "
            + "|  | |__   | _  |  |    |   |  |      |  |\n"
            + "|  | \\__/ |  |  |_______|  |_______    |  |   |   |   | \\    |  "
            + "|____|  || | |  |    |   |  |______|  |\n"
            + "|__|      |__|__________|__________|   |__|   |___|___|  \\___|__________"
            + "||______|    |___|____________|\n";

    public static final String MESSAGE_WELCOME = "Welcome to MeetingJio!";
    public static final String MESSAGE_QUESTION_NAME = "How do I address you?";
    public static final String MESSAGE_HINT = "If you are unfamiliar with MeetingJio, "
            + "you can view the list of commands available using the command 'help'";
    public static final String MESSAGE_DIVIDER = "________________________________________________________"
            + "__________________________________________________";
    public static final String MESSAGE_GOODBYE = "See you again!";
    public static final String MESSAGE_ADD_USER_FORMAT = "To add a user: add_user [Name]\n";
    public static final String MESSAGE_ADD_LESSON_FORMAT = "To add a lesson: add_lesson n/[Name] t/[Titile] "
            + "d/[Day] st/[StartTime] et/[EndTime] m/[Mode]\n";
    public static final String MESSAGE_ADD_MEETING_FORMAT = "To add a meeting: add_meeting t/[Title] "
            + "d/[Day] st/[StartTime] et/[EndTime] m/[Mode]\n";
    public static final String MESSAGE_DELETE_FORMAT = "To delete a lesson: delete n/[Name] i/[Index]\n";
    public static final String MESSAGE_LIST_ALL_FORMAT = "To list all lessons: list all\n";
    public static final String MESSAGE_LIST_USER_FORMAT = "To list a user's lessons: list [Name]\n";
    public static final String MESSAGE_CLEAR_ALL_FORMAT = "To clear all entries: clear all\n";
    public static final String MESSAGE_CLEAR_USER_FORMAT = "To clear all entries: clear [Name]\n";
    public static final String MESSAGE_FREE_GENERIC_FORMAT = "To find free timeslots: free\n";
    public static final String MESSAGE_FREE_SPECIFIC_FORMAT = "To find free timeslots given a minimum duration: free "
            + "[duration]\n";
    public static final String NEW_USER_ADDED_SO_ALL_MEETINGS_DELETED = "New user added so all meetings "
            + "added so far will be deleted";
    public static final String MESSAGE_EXIT_FORMAT = "To exit the application: exit\n";
    public static final String MESSAGE_HELP = "Here is the list of commands available:\n"
            + MESSAGE_DIVIDER + "\n"
            + "1. " + MESSAGE_ADD_USER_FORMAT
            + "2. " + MESSAGE_ADD_LESSON_FORMAT
            + "3. " + MESSAGE_ADD_MEETING_FORMAT
            + "4. " + MESSAGE_DELETE_FORMAT
            + "5. " + MESSAGE_LIST_ALL_FORMAT
            + "6. " + MESSAGE_LIST_USER_FORMAT
            + "7. " + MESSAGE_FREE_GENERIC_FORMAT
            + "8. " + MESSAGE_FREE_SPECIFIC_FORMAT
            + "9. " + MESSAGE_CLEAR_ALL_FORMAT
            + "10. " + MESSAGE_CLEAR_USER_FORMAT
            + "11. " + MESSAGE_EXIT_FORMAT
            + MESSAGE_DIVIDER;

    public static final String SAVE_DATA_MESSAGE = "Data saved to local successfully";
}
