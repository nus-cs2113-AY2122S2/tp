package seedu.sherpass.timetable;

import seedu.sherpass.enums.TaskContentType;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import java.time.Month;
import java.util.ArrayList;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;


import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.outputDateOnlyFormat;
import static seedu.sherpass.constant.TimetableConstant.BY_DATE_COMPARE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.BY_DATE_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.BY_DATE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.DAYS_IN_A_WEEK;
import static seedu.sherpass.constant.TimetableConstant.DO_ON_DATE_COMPARE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.DO_ON_DATE_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.DO_ON_DATE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_NUMBER_COMPARE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_DESCRIPTION_COMPARE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_DESCRIPTION_COMPARE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_DESCRIPTION_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_NUMBER_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_NUMBER_OFFSET_LENGTH;

public class Timetable {
    private static LocalDate localDate;
    private static ArrayList<Task> tasks;
    private static Ui ui;

    private static void setInitialTimetableInputs(LocalDate dateInput,
                                                  ArrayList<Task> filteredTasks, Ui userInterface) {
        localDate = dateInput;
        tasks = filteredTasks;
        ui = userInterface;
    }

    /**
     * Generates a timetable for the day according to the date input.
     *
     * @param dateInput The date input.
     * @param taskList  Representation of an array of tasks.
     * @param ui        The user interface which interacts with the user.
     */
    public static void showScheduleByDay(LocalDate dateInput, TaskList taskList, Ui ui) {
        ArrayList<Task> filteredTasks = taskList.getFilteredTasksByDate(dateInput);
        setInitialTimetableInputs(dateInput, filteredTasks, ui);
        prepareFullTimetable();
    }

    /**
     * Generates a condensed timetable for the month according to the month input.
     * Applies only to future months, i.e. not for the months which have passed.
     *
     * @param month The month input.
     * @param taskList  Representation of an array of tasks.
     * @param ui        The user interface which interacts with the user.
     */
    public static void showScheduleByMonth(Month month, TaskList taskList, Ui ui) {
        LocalDate firstDayOfMonth = TimetableLogic.getFirstDayOfMonth(month);
        ArrayList<Task> monthlySchedule = taskList.getFilteredTasksByMonth(firstDayOfMonth);
        prepareCondensedTimetable(monthlySchedule, ui);
    }

    //@@author jltha
    /**
     * Generates a condensed timetable format of all the pending tasks.
     *
     * @param taskList Representation of an array of tasks.
     * @param ui The user interface which interacts with the user.
     */
    public static void showPendingTasks(TaskList taskList, Ui ui) {
        tasks = taskList.getPendingTasks();
        prepareCondensedTimetable(tasks, ui);
    }

    /**
     * Prepares a condensed timetable format and print the tasks given.
     *
     * @param tasks Representation of an array of tasks.
     * @param ui The user interface which interacts with the user.
     */
    public static void prepareCondensedTimetable(ArrayList<Task> tasks, Ui ui) {
        long taskLength = TimetableLogic.prepareTaskContentLength(tasks,
                TASK_DESCRIPTION_COMPARE_LENGTH, TASK_DESCRIPTION_COMPARE_OFFSET_LENGTH,
                TASK_DESCRIPTION_FULL_LENGTH, TaskContentType.TASK_DESCRIPTION);
        long byDateLength = TimetableLogic.prepareTaskContentLength(tasks,
                BY_DATE_COMPARE_LENGTH, BY_DATE_OFFSET_LENGTH,
                BY_DATE_FULL_LENGTH, TaskContentType.BY_DATE);
        long doOnDateLength = TimetableLogic.prepareTaskContentLength(tasks,
                DO_ON_DATE_COMPARE_LENGTH, DO_ON_DATE_OFFSET_LENGTH,
                DO_ON_DATE_FULL_LENGTH, TaskContentType.DO_ON_DATE);
        long taskNumberLength = TimetableLogic.prepareTaskContentLength(tasks,
                TASK_NUMBER_COMPARE_LENGTH, TASK_NUMBER_OFFSET_LENGTH,
                TASK_NUMBER_FULL_LENGTH, TaskContentType.TASK_NUMBER);
        long partitionLength = TimetableLogic.calcPartitionLength(taskLength, byDateLength, doOnDateLength,
                taskNumberLength, false);
        if (!tasks.isEmpty()) {
            TimetablePrinting.printCondensedTimetable(tasks, ui, taskLength, byDateLength,
                    doOnDateLength, taskNumberLength, partitionLength);
        } else {
            TimetablePrinting.printEmptyCondensedTimetable(ui);
        }
    }


    /**
     * Generates a timetable for the current week.
     *
     * @param dateInput The date input.
     * @param taskList  Representation of an array of tasks.
     * @param ui        The user interface which interacts with the user.
     */
    public static void showScheduleOfTheWeek(LocalDate dateInput, TaskList taskList, Ui ui) {
        LocalDate currentDate = TimetableLogic.resetDateToMonday(dateInput, ui);
        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            showScheduleByDay(currentDate, taskList, ui);
            assert (currentDate != null);
            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
    }


    /**
     * Prepares a full timetable and prints the timetable in the terminal.
     */
    private static void prepareFullTimetable() {
        assert localDate != null;
        String day = localDate.format(dayOnlyFormat);
        String date = localDate.format(outputDateOnlyFormat);
        long taskLength = TimetableLogic.prepareTaskContentLength(tasks,
                TASK_DESCRIPTION_COMPARE_LENGTH, TASK_DESCRIPTION_COMPARE_OFFSET_LENGTH,
                TASK_DESCRIPTION_FULL_LENGTH, TaskContentType.TASK_DESCRIPTION);
        long byDateLength = TimetableLogic.prepareTaskContentLength(tasks,
                BY_DATE_COMPARE_LENGTH, BY_DATE_OFFSET_LENGTH,
                BY_DATE_FULL_LENGTH, TaskContentType.BY_DATE);
        long partitionLength = TimetableLogic.calcPartitionLength(taskLength, byDateLength,
                0, 0, true);
        if (!tasks.isEmpty()) {
            TimetablePrinting.printFullTimetable(day, date, tasks, ui, taskLength, byDateLength, partitionLength);
        } else {
            TimetablePrinting.printEmptyFullTimetable(ui, day, date, partitionLength);
        }
    }
}
