package seedu.sherpass.constant;

public class Message {
    public static final String EMPTY_STRING = "";
    public static final String WELCOME_MESSAGE_ONE = "Welcome to\n";
    public static final String WELCOME_MESSAGE_TWO = "How can I help you today?";
    public static final String WELCOME_MESSAGE_STUDY = "Gotcha! Entering study mode...\n"
            + "Done! To get started, enter one of the three default timers\n"
            + "using 'start <mode_number>':\n"
            + "1) 30 minutes\n"
            + "2) 1 hour\n"
            + "3) 1.5 hours\n\n"
            + "For testing purposes, you may start a 30s timer\nwith mode number 0.\n"
            + "Otherwise, feel free to choose your own timer with\n'start /custom <timer_duration>'.";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";
    public static final String GOODBYE_MESSAGE_STUDY = "Leaving study session...\n"
            + "Welcome back to the main session! How can I help you?";

    private static final String HELP_MESSAGE = "For more information, please enter the 'help' command.";
    public static final String HELP_MESSAGE_QUICK_START_COMMAND = "Command input quick start guide:\n"
            + "1) List: list\n"
            + "2) Mark: mark <task_number>\n"
            + "3) Unmark: unmark <task_number>\n"
            + "4) Add: add <task_description> /by <task_due_date> /do_on <date_to_work_on_task>\n"
            + "5) Edit: edit <task_number> <task_description> /by <task_due_date> /do_on <date_to_work_on_task>\n"
            + "6) Delete: delete <task_number>\n"
            + "7) Clear: clear\n"
            + "8) Study: study\n"
            + "9) Help: help <command_word>\n"
            + "10) Bye: bye";
    public static final String HELP_MESSAGE_SPECIFIC_COMMAND = "\n\nFor more information, enter 'help'";
    public static final String HELP_MESSAGE_STUDY = "Timer command input quick start guide:\n"
            + "1) Start default timer: start <mode_number>\n"
            + "2) Start custom timer: start /custom <timer_duration>\n"
            + "3) Stop timer: stop\n"
            + "4) Pause timer: pause\n"
            + "5) Resume timer: resume\n"
            + "6) Exit study mode: leave";

    public static final String ERROR_INVALID_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE;
    public static final String ERROR_INVALID_STUDY_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE_STUDY;
    public static final String ERROR_INVALID_DELETE_INDEX_MESSAGE = "Oops! It seems that you've given\n"
            + "an invalid index to delete the task.";
    public static final String ERROR_IO_FAILURE_MESSAGE = "Oh no! We've encountered an error \nwhile "
            + "trying to processing the system.\n"
            + "Please reboot and execute the application again.";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_1 = "Oops! It seems that your saved file "
            + "is corrupted.";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_2 = "Would you like to start with a new save "
            + "file? (Y/N):";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_3 = "We're sorry this happened. "
            + "Please refer to the troubleshooting section in the user guide "
            + "or contact the developers for help.";
    public static final String ERROR_INVALID_MARKING_INDEX_MESSAGE = "Bzzt!\nPlease"
            + " key in a valid task number to mark/unmark your task.";
    public static final String ERROR_EMPTY_ADD_MESSAGE = "Oops! The description of an 'add' command cannot be empty.";
    public static final String ERROR_INVALID_DATE_FORMAT_MESSAGE = "Please key in valid date(s) of the format:\n\t"
            + "d/M/yyyy\n(Note: date and month can be in 1 or 2 digits)";
    public static final String ERROR_INVALID_TASK_NUMBER_MESSAGE = "Please key in a valid task number";
    public static final String ERROR_DUPLICATE_ADD_TASK_MESSAGE = "Oops! It seems that you've entered "
            + "a duplicate task.\nPlease re-enter a new task if you wish to add one.";
    public static final String ERROR_INVALID_EDIT_FORMAT_MESSAGE = "Please use the correct order of attributes:\n"
            + "<task_description> /by <task_due_date> /do_on <date_to_work_on_task>\n\n"
            + "You only need to input the attributes you want to edit.\n"
            + "e.g. edit 1 /do_on 2022/02/12\n"
            + "(The task_description and task_due_date is left out here)";
    public static final String ERROR_INVALID_TIMER_INPUT_MESSAGE = "Oops! Your timer input "
            + "does not seem to be correct.\n\n"
            + "Please select one of the three default modes with\n"
            + "\t'start <mode_number>'\n\n"
            + "or choose your own custom timer with\n"
            + "\t'start /custom <timer_duration>'";
    public static final String ERROR_DUPLICATE_TASK_MESSAGE_1 = "Skipping task \"";
    public static final String ERROR_DUPLICATE_TASK_MESSAGE_2 = "\" as it already exists!";
}
