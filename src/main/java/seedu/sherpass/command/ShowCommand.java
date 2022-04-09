package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.timetable.Timetable;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.WAITING_FOR_USER_NEXT_INPUT;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_TWO;

public class ShowCommand extends Command {

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

        switch (selection.toLowerCase()) {
        case "todo":
            ui.showToUser("Here are your pending tasks:");
            Timetable.showPendingTasks(taskList, ui);
            ui.showToUser("You have a total of " + taskList.getPendingTasksCount() + " pending task(s)");
            break;
        case "today":
            ui.showToUser(WELCOME_MESSAGE_TWO);
            Timetable.showScheduleByDay(LocalDate.now(), taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "tomorrow":
        case "tmr":
            ui.showToUser("Here is your schedule for tomorrow:");
            Timetable.showScheduleByDay(LocalDate.now().plusDays(1), taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "week":
            ui.showToUser("Here is your schedule for the week:");
            Timetable.showScheduleOfTheWeek(LocalDate.now(), taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "next week":
        case "nextweek":
            ui.showToUser("Here is your schedule for next week:");
            Timetable.showScheduleOfTheWeek(LocalDate.now().plusWeeks(1), taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "month":
            ui.showToUser("Here is your schedule for the current month:");
            Timetable.showScheduleByMonth(LocalDate.now().getMonth(), taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "jan":
        case "january":
            ui.showToUser("Here is your schedule for January:");
            Timetable.showScheduleByMonth(JANUARY, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "feb":
        case "february":
            ui.showToUser("Here is your schedule for February:");
            Timetable.showScheduleByMonth(FEBRUARY, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "mar":
        case "march":
            ui.showToUser("Here is your schedule for March:");
            Timetable.showScheduleByMonth(MARCH, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "apr":
        case "april":
            ui.showToUser("Here is your schedule for April:");
            Timetable.showScheduleByMonth(APRIL, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "may":
            ui.showToUser("Here is your schedule for May:");
            Timetable.showScheduleByMonth(MAY, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "jun":
        case "june":
            ui.showToUser("Here is your schedule for June:");
            Timetable.showScheduleByMonth(JUNE, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "jul":
        case "july":
            ui.showToUser("Here is your schedule for July:");
            Timetable.showScheduleByMonth(JULY, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "aug":
        case "august":
            ui.showToUser("Here is your schedule for August:");
            Timetable.showScheduleByMonth(AUGUST, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "sep":
        case "september":
            ui.showToUser("Here is your schedule for September:");
            Timetable.showScheduleByMonth(SEPTEMBER, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "oct":
        case "october":
            ui.showToUser("Here is your schedule for October:");
            Timetable.showScheduleByMonth(OCTOBER, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "nov":
        case "november":
            ui.showToUser("Here is your schedule for November:");
            Timetable.showScheduleByMonth(NOVEMBER, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "dec":
        case "december":
            ui.showToUser("Here is your schedule for December:");
            Timetable.showScheduleByMonth(DECEMBER, taskList, ui);
            ui.showToUser(WAITING_FOR_USER_NEXT_INPUT);
            break;
        case "all":
            ui.showToUser("Here are the tasks in your list:");
            Timetable.prepareCondensedTimetable(taskList.getTasks(), ui);
            ui.showToUser("You have a total of " + taskList.getSize() + " task(s)!");
            break;
        default:
            ui.showToUser(ERROR_INVALID_INPUT_MESSAGE);
        }
    }
}
