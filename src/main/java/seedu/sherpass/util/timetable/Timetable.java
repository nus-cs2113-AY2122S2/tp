package seedu.sherpass.util.timetable;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import java.time.Month;
import java.util.ArrayList;

import java.time.temporal.ChronoUnit;
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
import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.dateOnlyFormat;
import static seedu.sherpass.constant.TimetableConstant.DATE_SPACE_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.DAYS_IN_A_WEEK;

public class Timetable {
    private static Timetable timetable;
    private static LocalDate localDate;
    private static ArrayList<Task> tasks;
    private static Ui ui;

    private Timetable(LocalDate localDate, ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.localDate = localDate;
        this.ui = ui;
    }

    private void setTimetable(LocalDate dateInput, ArrayList<Task> filteredTasks, Ui userInterface) {
        localDate = dateInput;
        tasks = filteredTasks;
        ui = userInterface;
    }

    public static Timetable prepareTimetable(LocalDate dateInput, ArrayList<Task> filteredTasks, Ui ui) {
        if (timetable == null) {
            timetable = new Timetable(dateInput, filteredTasks, ui);
        } else {
            timetable.setTimetable(dateInput, filteredTasks, ui);
        }
        return timetable;
    }

    /**
     * Generate a timetable for today.
     *
     * @param taskList Representation of an array of tasks.
     * @param ui       The user interface which interacts with the user.
     */
    public static void showTodaySchedule(TaskList taskList, Ui ui) {
        LocalDate todayDate = LocalDate.now();
        showScheduleByDay(todayDate, taskList, ui);
    }

    /**
     * Generate a timetable for tomorrow.
     *
     * @param taskList Representation of an array of tasks.
     * @param ui       The user interface which interacts with the user.
     */
    public static void showTomorrowSchedule(TaskList taskList, Ui ui) {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        showScheduleByDay(tomorrowDate, taskList, ui);
    }

    /**
     * Generate a timetable for the day according to the date input.
     *
     * @param dateInput The date input.
     * @param taskList  Representation of an array of tasks.
     * @param ui        The user interface which interacts with the user.
     */
    public static void showScheduleByDay(LocalDate dateInput, TaskList taskList, Ui ui) {
        ArrayList<Task> filteredTasks = taskList.getFilteredTasksByDate(dateInput);
        Timetable timetable = prepareTimetable(dateInput, filteredTasks, ui);
        timetable.printSchedule();
    }


    public static void showScheduleOfTheWeek(TaskList taskList, Ui ui) {
        LocalDate currentDate = TimetableLogic.resetDateToMonday(LocalDate.now(), ui);
        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            showScheduleByDay(currentDate, taskList, ui);
            assert (currentDate != null);
            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
    }


    /**
     * Generate a weekly timetable schedule.
     *
     * @param taskList Representation of an array of tasks.
     * @param ui       The user interface which interacts with the user.
     */
    public static void showNextWeekSchedule(TaskList taskList, Ui ui) {
        LocalDate nextMondayDate = LocalDate.now().plusWeeks(1);
        LocalDate nextDate = TimetableLogic.resetDateToMonday(nextMondayDate, ui);
        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            showScheduleByDay(nextDate, taskList, ui);
            assert (nextDate != null);
            nextDate = nextDate.plusDays(1);
        }
    }

    public static void showMonthlySchedule(TaskList taskList, Ui ui) {
        Month currentMonth = LocalDate.now().getMonth();
        TimetableLogic.showMonthlySchedule(taskList, ui, currentMonth);
    }

    public static void showJanuarySchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, JANUARY);
    }

    public static void showFeburarySchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, FEBRUARY);
    }

    public static void showMarchSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, MARCH);
    }

    public static void showAprilSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, APRIL);
    }

    public static void showMaySchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, MAY);
    }

    public static void showJuneSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, JUNE);
    }

    public static void showJulySchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, JULY);
    }

    public static void showAuguestSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, AUGUST);
    }

    public static void showSeptemberSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, SEPTEMBER);
    }

    public static void showOctoberSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, OCTOBER);
    }

    public static void showNovemberSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, NOVEMBER);
    }

    public static void showDecemberSchedule(TaskList taskList, Ui ui) {
        TimetableLogic.showMonthlySchedule(taskList, ui, DECEMBER);
    }

    private void printSchedule() {
        // assert localDate != null;
        String day = localDate.format(dayOnlyFormat);
        String date = localDate.format(dateOnlyFormat);
        long taskLength = TimetableLogic.findTaskLength(tasks);
        long doOnDateLength = DATE_SPACE_FULL_LENGTH;
        long partitionLength = TimetableLogic.calcPartitionLength(taskLength, doOnDateLength);

        if (!tasks.isEmpty()) {
            TimetablePrinting.printTimetable(day, date, tasks, ui, taskLength, doOnDateLength, partitionLength);
        } else {
            TimetablePrinting.printEmptyTimetable(ui, day, date, partitionLength);
        }
    }

    /**
     * Compare the two timetable object
     * and whether the arrayList in the timetable are equals.
     *
     * @param obj To check if the obj is equals to the given Timetable object.
     * @return boolean object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Timetable) {
            Timetable timetable = (Timetable) obj;
            return timetable.tasks.equals(this.tasks);
        } else {
            return false;
        }
    }
}
