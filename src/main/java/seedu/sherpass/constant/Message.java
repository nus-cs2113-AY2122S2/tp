package seedu.sherpass.constant;

public class Message {
    public static final String WELCOME_MESSAGE_ONE = "Welcome to\n";
    public static final String WELCOME_MESSAGE_TWO = "How can I help you today?";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    private static final String HELP_MESSAGE = "For more information, please enter the 'help' command.";
    public static final String HELP_MESSAGE_SPECIFIC_COMMAND = "\n\nFor more information on "
            + "the command you wish to execute,\nenter 'help <command>' e.g. help todo";
    public static final String HELP_MESSAGE_QUICK_START_COMMAND = "Command input quick start guide:\n"
            + "1) List: list\n"
            + "2) Mark: mark <list_index>\n"
            + "3) Unmark: unmark <list_index>\n"
            + "4) Todo: todo <task_description>\n"
            + "5) Deadline: deadline <task_description> /by <task_date>\n"
            + "6) Event: event <task_description> /at <task_date>\n"
            + "7) Delete: delete <list_index>\n"
            + "8) Find: find <keyword>\n"
            + "9) Clear: clear\n"
            + "9) Bye: bye";

    public static final String ERROR_INVALID_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE;
    public static final String ERROR_EVENT_MISSING_COMMAND_MESSAGE = "Oops! You forgot to "
            + "add a '/at' to your 'event' command.";
    public static final String ERROR_DEADLINE_MISSING_COMMAND_MESSAGE = "Oops! You forgot to"
            + " add a '/by' to your 'deadline' command.";
    public static final String ERROR_TODO_REPEATED_INPUT_MESSAGE = "Oops! It seems that you've "
            + "already added this task.";
    public static final String ERROR_INVALID_DELETE_INDEX_MESSAGE = "Oops! It seems that you've given\n"
            + "an invalid index to delete the task.";
    public static final String ERROR_IO_FAILURE_MESSAGE = "Oh no! We've encountered an error \nwhile "
            + "trying to processing the system.\n"
            + "Please reboot and execute the application again.";
    public static final String ERROR_SYSTEM_FAULT_MESSAGE = "Oops! There seems to be some problem"
            + "with the code.\nPlease contact the developers for help.";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_1 = "Oops! It seems that your saved file "
            + "was corrupted.";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_2 = "Would you like to start with a new save "
            + "file? (Y/N):";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_3 = "We're sorry this happened. "
            + "Please refer to the troubleshooting section in the user guide "
            + "or contact the developers for help.";
    public static final String ERROR_FILE_NOT_FOUND_MESSAGE = "Sorry! There was an error while loading your"
            + "saved file. Please restart and try again later.";
    public static final String ERROR_INVALID_MARKING_INDEX_MESSAGE = "Bzzt!\nPlease"
            + " key in a valid task number to mark/unmark your task."
            + HELP_MESSAGE_SPECIFIC_COMMAND;
    public static final String DATE_FORMAT_WITH_TIME = "dd MMM yyyy, EEE hh:mm a";
    public static final String DATE_FORMAT_WITHOUT_TIME = "dd MMM yyyy, EEE";
}
