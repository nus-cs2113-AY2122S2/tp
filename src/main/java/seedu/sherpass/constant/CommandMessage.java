package seedu.sherpass.constant;

import static seedu.sherpass.constant.Message.HELP_MESSAGE_STUDY;

public class CommandMessage {
    public static final String COMMAND_WORD_ADD = "add";
    public static final String MESSAGE_USAGE_ADD = "Add: Adds a task into the task list.\n"
            + "Usages:\n"
            + "       add TASK_DESCRIPTION /do DATE /start START_TIME \n"
            + "       /end END_TIME /repeat FREQUENCY\n\n"
            + "       add TASK_DESCRIPTION /do DATE /start START_TIME\n"
            + "       /end END_TIME [/bydate DEADLINE /bytime END_TIME]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm\n"
            + "FREQUENCY: daily, weekly, or monthly\n\n"
            + "Warning: If using the optional deadline parameter, both\n"
            + "         the bydate and the bytime need to be used together";
    public static final String COMMAND_WORD_CLEAR = "clear";
    public static final String MESSAGE_USAGE_CLEAR = "Clear: Deletes multiple tasks in the list quickly.\n"
            + "Usage: clear CLEAR_SCOPE\n"
            + "CLEAR_SCOPE: all, expired, or done";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String MESSAGE_USAGE_DELETE = "Delete: Deletes a task in the task list.\n"
            + "Usage: delete TASK_NUMBER [/repeat]";
    public static final String COMMAND_WORD_EDIT = "edit";
    public static final String MESSAGE_USAGE_EDIT = "Edit: Edit a task in the task list.\n"
            + "Usages:\n"
            + "       edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE]\n"
            + "       [/start START_TIME] [/end END_TIME]\n"
            + "       [/bydate DUE DATE /bytime DUE_TIME]\n\n"
            + "       edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE]\n"
            + "       [/start START_TIME] [/end END_TIME]\n"
            + "       [/bydate DUE DATE /bytime DUE_TIME] /repeat\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm";
    public static final String COMMAND_WORD_EXIT = "bye";
    public static final String MESSAGE_USAGE_EXIT = "Bye: Exits the program.\nUsage: bye";
    public static final String COMMAND_WORD_HELP = "help";
    public static final String COMMAND_WORD_HELP_VARIANT = "quick start";
    public static final String COMMAND_WORD_SHOW = "show";
    public static final String MESSAGE_USAGE_SHOW = "Show: Displays the tasks in either a list or timetable\n"
            + "      format.\n"
            + "Usages:\n"
            + "       Show timetable of the day or week:\n"
            + "         1) show today\n"
            + "         2) show tomorrow/tmr\n"
            + "         3) show week\n"
            + "         4) show next week/nextweek\n"
            + "         5) show DATE   (DATE format: d/M/yyyy)\n"
            + "       Show list of tasks in the current/specified month:\n"
            + "         6) show month\n"
            + "         7) show MONTH  (MONTH is either the full spelling\n"
            + "                         of the specific month or is its\n"
            + "                         3-letter abbreviation\n"
            + "                         E.g. `show sep`, `show september`)\n"
            + "            Caution: show MONTH will not display tasks in\n"
            + "                     the previous months\n"
            + "       Show all tasks:\n"
            + "         8) show all\n"
            + "       Show list of unfinished tasks:\n"
            + "         9) show todo";
    public static final String COMMAND_WORD_STUDY = "study";
    public static final String MESSAGE_USAGE_STUDY = "Study: Creates a study session.\n"
            + "Usage: study\n\n"
            + "You can access timer features after entering a study session\n"
            + "(i.e. after entering `study`)\n\n"
            + HELP_MESSAGE_STUDY;
    public static final String COMMAND_WORD_MARK = "mark";
    public static final String MESSAGE_USAGE_MARK = "Mark: Marks a task as done.\n"
            + "Usage: mark TASK_NUMBER\n\n"
            + "TASK_NUMBER is based on the number assigned\n"
            + "to the task under the command 'show all'.";
    public static final String COMMAND_WORD_UNMARK = "unmark";
    public static final String MESSAGE_USAGE_UNMARK = "Unmark: Marks a task as undone.\n"
            + "Usage: unmark TASK_NUMBER\n\n"
            + "TASK_NUMBER is based on the number assigned\n"
            + "to the task under the command 'show all'.";
}
