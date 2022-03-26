package seedu.sherpass.util.timetable;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import java.util.ArrayList;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

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

    public static void showTodaySchedule(TaskList taskList, Ui ui) {
        ArrayList<Task> filteredTasks = taskList.getFilteredTasksByDate(LocalDate.now());
        Timetable timetable = prepareTimetable(LocalDate.now(), filteredTasks, ui);
        timetable.printSchedule();
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

    /**
     * Generate a timetable for the current week.
     *
     * @param taskList Representation of an array of tasks.
     * @param ui       The user interface which interacts with the user.
     */
    public static void showScheduleOfTheWeek(TaskList taskList, Ui ui) {
        LocalDate currentDate = TimetableLogic.resetDateToMonday(LocalDate.now().format(dayOnlyFormat), ui);
        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            showScheduleByDay(currentDate, taskList, ui);
            assert (currentDate != null);
            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
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
