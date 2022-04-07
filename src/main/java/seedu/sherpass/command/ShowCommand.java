package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.timetable.Timetable;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;

import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.TASK_COUNT_MESSAGE_1;
import static seedu.sherpass.constant.Message.TASK_COUNT_MESSAGE_2;
import static seedu.sherpass.constant.Message.WAITING_FOR_USER_NEXT_INPUT;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_TWO;

public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = String.format("Show: shows the array of tasks in a list format%n"
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
            + "                   the abrreviation of the specfic months%n"
            + "                   E.g. `show sep` or `show september`%n"
            + "E.g. show 23/5/2022 to show timetable for 23th May 2022.%n%n"
            + ""
            + "To generate a list of all tasks, use%n"
            + "8) `show all`.%n"
            + "To generate a list of pending tasks, use%n"
            + "9) `show todo`.");

    LocalDate dayInput;
    String selection;

    public ShowCommand(LocalDate dayInput, String selection) {
        this.dayInput = dayInput;
        this.selection = selection;
    }

    /**
     * Executes the show command. Shows the user the schedule
     * for the day that is specified, the list of incomplete
     * tasks, and the current weekly schedule.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing task content.
     * @param storage  Storage for overwriting/appending save data. Not used in this method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (dayInput != null) {
            ui.showToUser("Here is the schedule you wanted:");
            Timetable.showScheduleByDay(dayInput, taskList, ui);
            return;
        }

        switch (selection) {
        case "todo":
            ui.showToUser("Here are your pending tasks:");
            ui.showToUser(taskList.getPendingTasks());
            break;
        case "today":
            ui.showToUser(WELCOME_MESSAGE_TWO);
            Timetable.showTodaySchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "tomorrow":
        case "tmr":
            ui.showToUser("Here is your schedule for tomorrow:");
            Timetable.showTomorrowSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "week":
            ui.showToUser("Here is your schedule for the week:");
            Timetable.showScheduleOfTheWeek(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "next week":
        case "nextweek":
            ui.showToUser("Here is your schedule for the week:");
            Timetable.showNextWeekSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "month":
            ui.showToUser("Here is your schedule for the current Month:");
            Timetable.showMonthlySchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "january":
        case "jan":
            ui.showToUser("Here is your schedule for January:");
            Timetable.showJanuarySchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "feburary":
        case "feb":
            ui.showToUser("Here is your schedule for Feburary:");
            Timetable.showFebruarySchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "march":
        case "mar":
            ui.showToUser("Here is your schedule for March:");
            Timetable.showMarchSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "april":
        case "apr":
            ui.showToUser("Here is your schedule for April:");
            Timetable.showAprilSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "may":
            ui.showToUser("Here is your schedule for May:");
            Timetable.showMaySchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "june":
        case "jun":
            ui.showToUser("Here is your schedule for June:");
            Timetable.showJuneSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "july":
        case "jul":
            ui.showToUser("Here is your schedule for July:");
            Timetable.showJulySchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "auguest":
        case "aug":
            ui.showToUser("Here is your schedule for August:");
            Timetable.showAugustSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "september":
        case "sep":
            ui.showToUser("Here is your schedule for September:");
            Timetable.showSeptemberSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "october":
        case "oct":
            ui.showToUser("Here is your schedule for October:");
            Timetable.showOctoberSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "november":
        case "nov":
            ui.showToUser("Here is your schedule for November:");
            Timetable.showNovemberSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "decemeber":
        case "dec":
            ui.showToUser("Here is your schedule for December:");
            Timetable.showDecemberSchedule(taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "all":
            ui.showToUser("Here are the tasks in your list:");
            ui.showToUser(taskList.getAllTasksInString());
            ui.showToUser(TASK_COUNT_MESSAGE_1 + taskList.getSize() + TASK_COUNT_MESSAGE_2);
            break;
        default:
            ui.showToUser(ERROR_INVALID_INPUT_MESSAGE);
        }
    }
}
