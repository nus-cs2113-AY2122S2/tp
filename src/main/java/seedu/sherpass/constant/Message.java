package seedu.sherpass.constant;

public class Message {
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String TAB_INDENT = "\t";
    public static final String PARTITION_LINE = "______________________________"
            + "______________________________";
    public static final String LS = System.lineSeparator();
    public static final String LOGO = "  ____  _\n"
            + " / ___|| |__   ___ _ __ "
            + "_ __   __ _ ___ ___\n"
            + " \\___ \\| '_ \\ / _ \\ '__| "
            + "'_ \\ / _` / __/ __|\n"
            + "  ___) | | | |  __/ |  "
            + "| |_) | (_| \\__ \\__ \\\n"
            + " |____/|_| |_|\\___|_|  "
            + "| .__/ \\__,_|___/___/\n"
            + "                       "
            + "|_|";
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
            + "the command you wish to execute,\nenter 'help COMMAND_WORD' e.g. help add";
    public static final String HELP_MESSAGE_QUICK_START_COMMAND = "Command input quick start guide:\n"
            + " 1) Show tasks:    show today/week/DATE/all/todo\n"
            + " 2) Mark a task:   mark TASK_NUMBER\n"
            + " 3) Unmark a task: unmark TASK_NUMBER\n"
            + " 4) Add task: add TASK_DESCRIPTION /do DATE /start START_TIME\n"
            + "              /end END_TIME /repeat FREQUENCY\n"
            + "              or\n"
            + "              add TASK_DESCRIPTION /do DATE /start START_TIME\n"
            + "              /end END_TIME [/bydate DEADLINE /bytime END_TIME]\n"
            + " 5) Edit task: edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE]\n"
            + "               [/start START_TIME] [/end END_TIME]\n"
            + "               [/bydate DUE DATE /bytime DUE_TIME]\n"
            + "               or\n"
            + "               edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE]\n"
            + "               [/start START_TIME] [/end END_TIME]\n"
            + "               [/bydate DUE DATE /bytime DUE_TIME] /repeat\n"
            + " 6) Delete a task:    delete TASK_NUMBER\n"
            + " 7) Clear tasks:      clear all/expired/done\n"
            + " 8) Enter study mode: study\n"
            + " 9) Help:             help [COMMAND_WORD/quick start]\n"
            + "10) Exit:             bye";
    public static final String HELP_MESSAGE_STUDY = "Timer command input quick start guide:\n"
            + "1) Start default timer: start MODE_NUMBER\n"
            + "2) Start custom timer:  start /custom TIMER_DURATION\n"
            + "3) Stop timer:          stop\n"
            + "4) Pause timer:         pause\n"
            + "5) Resume timer:        resume\n"
            + "6) Show tasks:          show today/week/DATE/all/todo\n"
            + "7) Mark task as done:   mark TASK_NUMBER\n"
            + "8) Exit study mode:     leave";

    public static final String ERROR_PREFIX = "Command error: ";
    public static final String EDIT_TASK_RESULT_MESSAGE = "Okay! I've edited this task:";
    public static final String DELETE_TASK_RESULT_MESSAGE = "Okay. I've removed this task:";
    public static final String ADD_TASK_RESULT_MESSAGE = "Got it! I've added this task:";

    public static final String TASK_COUNT_MESSAGE_1 = "You now have ";
    public static final String TASK_COUNT_MESSAGE_2 = " task(s) in your schedule!";

    public static final String UNMARK_TASK_RESULT_MESSAGE = "Ok, I've marked this task as not done yet:";
    public static final String MARK_TASK_RESULT_MESSAGE = "Nice! I've marked this task as done:";
    public static final String CLEAR_ALL_COMMAND_CONFIRMATION_MESSAGE = "Are you sure you want to delete all tasks?\n"
            + "You will not be able to recover them after deleting (Y/N): ";
    public static final String CLEAR_ALL_COMMAND_CONFIRMED_MESSAGE = "Understood. Proceeding to delete"
            + " all tasks in the list......";
    public static final String CLEAR_ALL_COMMAND_RESULT_MESSAGE = "Done! Now you have 0 tasks in the list.";
    public static final String CLEAR_ALL_COMMAND_CANCEL_MESSAGE = "Okay, we'll keep it as it is.";
    public static final String CLEAR_NO_EXPIRED_TASK_MESSAGE = "There are no expired tasks to be removed.";
    public static final String CLEAR_NO_COMPLETED_TASK_MESSAGE = "There are no completed tasks to be removed.";
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
    public static final String ERROR_INVALID_STUDY_INPUT_MESSAGE = "Please key in an appropriate command.\n\n"
            + HELP_MESSAGE_STUDY;
    public static final String ERROR_INVALID_DELETE_INDEX_MESSAGE = "It seems that you've given\n"
            + "an invalid index to delete the task.";
    public static final String ERROR_INVALID_CLEAR_MESSAGE = "Please use appropriate keywords";
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
            + "\t'start MODE_NUMBER'\n\n"
            + "or choose your own custom timer with\n"
            + "\t'start /custom TIMER_DURATION'";
}
