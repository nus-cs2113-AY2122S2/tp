package seedu.sherpass.constant;

public class Message {
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String TAB_INDENT = "\t";
    public static final String NEWLINE = "\n";
    public static final String WELCOME_MESSAGE_ONE = "Welcome to\n";
    public static final String WELCOME_MESSAGE_TWO = "Here is your schedule for today:";
    public static final String WELCOME_MESSAGE_STUDY = "Gotcha! Entering study mode...\n"
            + "Done! To get started, enter one of the three default timers\n"
            + "using 'start <mode_number>':\n"
            + "1) 30 minutes\n"
            + "2) 1 hour\n"
            + "3) 1.5 hours\n\n"
            + "For testing purposes, you may start a 30s timer\nwith mode number 0.\n"
            + "Feel free to choose your own timer with\n'start /custom <timer_duration>'.\n"
            + "Otherwise, you can start a stopwatch with 'start stopwatch'.\n"
            + "You can also use the \"show today\" command to\nsee what tasks you have planned to complete.";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";
    public static final String GOODBYE_MESSAGE_STUDY = "Leaving study session...\n"
            + "Welcome back to the main session! How can I help you?";

    private static final String HELP_MESSAGE = "For more information, please enter the 'help' command.";
    public static final String HELP_MESSAGE_SPECIFIC_COMMAND = "\n\nFor more information on "
            + "the command you wish to execute,\nenter 'help <command>' e.g. help add";
    public static final String HELP_MESSAGE_QUICK_START_COMMAND = "Command input quick start guide:\n"
            + "1) Show: show today/week/<date>/all/todo\n"
            + "2) Mark: mark <list_index>\n"
            + "3) Unmark: unmark <list_index>\n"
            + "4) Add: add <task_description> /by <task_due_date> /do_on <date_to_work_on_task>\n"
            + "5) Delete: delete <list_index>\n"
            + "6) Find: find <keyword>\n"
            + "7) Clear: clear\n"
            + "8) Bye: bye";
    public static final String HELP_MESSAGE_STUDY = "Timer command input quick start guide:\n"
            + "1) Start default timer: start <mode_number>\n"
            + "2) Start custom timer: start /custom <timer_duration>\n"
            + "3) Stop timer: stop\n"
            + "4) Pause timer: pause\n"
            + "5) Resume timer: resume\n"
            + "6) Exit study mode: leave\n"
            + "7) Mark task as done: mark <task_index>";

    public static final String ERROR_PREFIX = "Command error: ";
    public static final String EDIT_TASK_RESULT_MESSAGE = "Okay! I've edited this task:";
    public static final String DELETE_TASK_RESULT_MESSAGE = "Okay. I've removed this task:";
    public static final String ADD_TASK_RESULT_MESSAGE = "Got it! I've added this task:";

    public static final String TASK_COUNT_MESSAGE_1 = "You now have ";
    public static final String TASK_COUNT_MESSAGE_2 = " task(s) in your schedule!";

    public static final String UNMARK_TASK_RESULT_MESSAGE = "Ok, I've marked this task as not done yet:";
    public static final String MARK_TASK_RESULT_MESSAGE = "Nice! I've marked this task as done:";
    public static final String CLEAR_COMMAND_CONFIRMATION_MESSAGE = "Are you sure you want to delete all tasks?\n"
            + "You will not be able to recover them after deleting (Y/N): ";
    public static final String CLEAR_COMMAND_CONFIRMED_MESSAGE = "Understood. Proceeding to delete"
            + " all tasks in the list..........";
    public static final String CLEAR_COMMAND_RESULT_MESSAGE = "Done! Now you have 0 task in the list.";
    public static final String CLEAR_COMMAND_CANCEL_MESSAGE = "Okay, we'll keep it as it is.";
    public static final String WAITING_FOR_USER_NEXT_INPUT = "What would you like to do next?";
    public static final String ERROR_NO_VALUE_FOR_PARAMETER_MESSAGE = "Please enter a value for the parameter!";
    public static final String ERROR_EMPTY_EDIT_CONTENT_MESSAGE = "Please enter at least one optional parameter!";
    public static final String ERROR_START_DATE_IN_THE_PAST_MESSAGE = "Do date cannot be in the past!";
    public static final String ERROR_START_AFTER_END_TIME_MESSAGE = "Start time cannot be after/equals to end time!";
    public static final String ERROR_EMPTY_DESCRIPTION_MESSAGE = "Description cannot be blank and must be before "
            + "the date and time add commands";
    public static final String ERROR_INVALID_FREQUENCY_MESSAGE = "Invalid frequency!";
    public static final String ERROR_INVALID_INDEX_MESSAGE = "Invalid index!";
    public static final String ERROR_INVALID_DATETIME_MESSAGE = "Invalid date and/or time format!";
    public static final String ERROR_SCHEDULE_CLASH_MESSAGE = "I am unable to add this task as "
            + "it has some clashes\nwith your schedule :(";
    public static final String ERROR_EMPTY_ADD_COMMANDS_MESSAGE = "Add command is missing "
            + "description and/or date details!\n(and possibly a repeat frequency if you intend to add it in)";
    public static final String ERROR_BY_DATE_TIME_MISSING_MESSAGE = "Please specify both date and time for by date!";
    public static final String ERROR_BY_DATE_BEFORE_DO_ON_DATE = "Your deadline cannot be before your do date!";

    public static final String ERROR_INVALID_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE;
    public static final String ERROR_INVALID_STUDY_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE_STUDY;
    public static final String ERROR_INVALID_DELETE_INDEX_MESSAGE = "It seems that you've given\n"
            + "an invalid index to delete the task.";
    public static final String ERROR_IO_FAILURE_MESSAGE = "Oh no! We've encountered an error \nwhile "
            + "trying to processing the system.\n"
            + "Please reboot and execute the application again.";
    public static final String ERROR_INVALID_SAVED_FILE_MESSAGE_1 = "Oops! It seems that your saved file "
            + "contains invalid data.\nWould you like to start with a new save "
            + "file? (Y/N):";
    public static final String ERROR_INVALID_SAVED_FILE_MESSAGE_2 = "We're sorry this happened. "
            + "Please refer to the troubleshooting section in the user guide "
            + "or contact the developers for help.";
    public static final String ERROR_INVALID_MARKING_INDEX_MESSAGE = "Please"
            + " key in a valid task number to mark/unmark your task.";
    public static final String ERROR_INVALID_TIMER_INPUT_MESSAGE = "Oops! Your timer input "
            + "does not seem to be correct.\n\n"
            + "Please select one of the three default modes with\n"
            + "\t'start <mode_number>'\n\n"
            + "or choose your own custom timer with\n"
            + "\t'start /custom <timer_duration>'";
}
