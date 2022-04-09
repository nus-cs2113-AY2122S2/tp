package seedu.sherpass.constant;

import static seedu.sherpass.constant.Message.HELP_MESSAGE_STUDY;

public class CommandMessage {
    public static final String COMMAND_WORD_ADD = "add";
    public static final String MESSAGE_USAGE_ADD = "Add: Adds a task into the task list.\n"
            + "Usage: add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME /repeat FREQUENCY\n"
            + "       add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME [/bydate DEADLINE "
            + "/bytime END_TIME]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm\n"
            + "FREQUENCY: daily, weekly, or monthly\n\n"
            + "Warning:\n"
            + "If using the optional deadline parameter, both the bydate and the bytime needs to be used together";
    public static final String COMMAND_WORD_CLEAR = "clear";
    public static final String MESSAGE_USAGE_CLEAR = "Clear: Deletes multiple tasks in the list quickly.\n"
            + "Usage: clear CLEAR_SCOPE\n"
            + "CLEAR_SCOPE: all, expired, or done";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String MESSAGE_USAGE_DELETE = "Delete: Deletes a task in the task list.\n"
            + "Usage: delete TASK_NUMBER [/repeat]";
    public static final String COMMAND_WORD_EDIT = "edit";
    public static final String MESSAGE_USAGE_EDIT = "Edit: Edit a task in the task list.\n"
            + "Usage: edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE /start START_TIME /end END_TIME]"
            + " [/by DEADLINE]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm";
    public static final String COMMAND_WORD_EXIT = "bye";
    public static final String MESSAGE_USAGE_EXIT = "Bye: Exits the program.";
    public static final String COMMAND_WORD_HELP = "help";
    public static final String COMMAND_WORD_HELP_VARIANT = "quick start";
    public static final String COMMAND_WORD_MARK = "mark";
    public static final String MESSAGE_USAGE_MARK = "Mark: Marks a task as done. "
            + "\nTo mark a specific task, enter 'mark <task_number>'.\n\n Here, "
            + "'task_number' denotes the index of a task \n based on the task list under the command 'show all'.\n"
            + "\nE.g., 'mark 1' marks the first task in the task list as done.\n\n"
            + "Note: You can only mark one task per command input.";
    public static final String COMMAND_WORD_SHOW = "show";
    public static final String MESSAGE_USAGE_SHOW = String.format("Show: shows the array of tasks in a list format%n"
            + "or in a timetable format.%n%n"
            + ""
            + "To generate the timetable or a list of monthly schedule, use %n"
            + "1) `show today`%n"
            + "2) `show tomorrow`, where `tomorrow can be shorten to `tmr`%n"
            + "3) `show week`%n"
            + "4) `show next week`, where `next week` can be shorten to `nextweek`%n"
            + "5) `show <date>`, where date is in the format d/M/YYYY.%n"
            + "6) `show month`%n"
            + "7) `show <month>`, where month can be the full spelling or%n"
            + "                   the abbreviation of the specific months%n"
            + "                   E.g. `show sep` or `show september`%n"
            + "    Caution: show <month> will not display previous month task but task that is in the upcoming month%n"
            + "E.g. show 23/5/2022 to show timetable for 23th May 2022.%n%n"
            + ""
            + "To generate a list of all tasks, use%n"
            + "8) `show all`.%n"
            + "To generate a list of pending tasks, use%n"
            + "9) `show todo`.");
    public static final String COMMAND_WORD_STUDY = "study";
    public static final String MESSAGE_USAGE_STUDY = "Study: Enters a study session.\n\n"
            + "User can access timer features while in a study session.\n\n"
            + HELP_MESSAGE_STUDY;
    public static final String COMMAND_WORD_UNMARK = "unmark";
    public static final String MESSAGE_USAGE_UNMARK = "Unmark: Marks a task as undone."
            + "\nTo unmark a specific task, enter 'unmark <task_number>'.\n\n Here, "
            + "'task_number' denotes the index of a task \n based on the task list under the command 'show all'.\n"
            + "\nE.g., 'unmark 3' unmarks the third task in the task list.\n\n"
            + "Note: You can only unmark one task per command input.";
}
