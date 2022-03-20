package seedu.sherpass.util;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.util.ArrayList;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

import static seedu.sherpass.Main.LOGGER;
import java.util.logging.Level;

import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.dateOnlyFormat;
import static seedu.sherpass.constant.TimetableConstant.DATE_SPACE_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.DAYS_IN_A_WEEK;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_PIPE_LINE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_SPACE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_SPACE_COMPARE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_SPACE_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_SPACE_COMPARE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TIMETABLE_SIZE_OFFSET;
import static seedu.sherpass.constant.TimetableConstant.WHITE_SPACE_FRONT_OFFSET_LENGTH;

public class Timetable {

    private static int findTaskDescriptionLength(ArrayList<Task> filteredTasks) {
        int max = 0;
        for (Task task : filteredTasks) {
            int taskLength = task.getDescription().length();
            if (taskLength > max) {
                max = taskLength;
            }
        }
        return max;
    }

    private static int findTaskLength(ArrayList<Task> filteredTasks) {
        int descriptionLength = findTaskDescriptionLength(filteredTasks);
        if (descriptionLength > TASK_SPACE_COMPARE_LENGTH) {
            return descriptionLength + TASK_SPACE_COMPARE_OFFSET_LENGTH;
        }
        return TASK_SPACE_FULL_LENGTH;
    }

    private static int calculateColBackWhiteSpace(int maxDescriptionLength,
                                                  String description) {
        return maxDescriptionLength - (description.length() + WHITE_SPACE_FRONT_OFFSET_LENGTH);
    }

    private static void printRow(String colOne, String colTwo, String colThree,
                                   String colFour, String colFive,
                                   int taskLength, int doOnDateLength, int iteration, Ui ui) {
        String taskColumnBackWhiteSpace = ui.getRepeatedCharacters(" ",
                calculateColBackWhiteSpace(taskLength, colFour));
        String doOnDateColumnBackWhiteSpace = ui.getRepeatedCharacters(" ",
                calculateColBackWhiteSpace(doOnDateLength, colFive));
        if (iteration == 1) {
            ui.showToUser("|  " + colOne + "       |  " + colTwo + "       |  "
                    + colThree + " |  " + colFour + taskColumnBackWhiteSpace
                    + "|  " + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else if (iteration == 2) {
            ui.showToUser("|  " + colOne + "       | " + colTwo + " |      "
                    + colThree + "       | " + colFour + taskColumnBackWhiteSpace
                    + "| " + colFive + doOnDateColumnBackWhiteSpace + "|");
        }  else if (iteration == 3) {
            ui.showToUser("| " + colOne + " | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskColumnBackWhiteSpace + "|  "
                    + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else {
            ui.showToUser("|            | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskColumnBackWhiteSpace + "|  "
                    + colFive + doOnDateColumnBackWhiteSpace + "|");
        }
    }

    private static void printTimetable(String day, String date, ArrayList<Task> filteredTasks,
                                       Ui ui, int taskLength, int doOnDateLength, int partitionLength) {
        int j = 0;
        for (int i = 0; i < filteredTasks.size() + TIMETABLE_SIZE_OFFSET; i++) {
            if ((i == 0) || (i == filteredTasks.size() - 1)) {
                ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
            } else if (i == 1) {
                printRow("Day", "Time", "Mark Status",
                        "Task Description", "Do on Date",
                        taskLength, doOnDateLength, i, ui);
            } else if (i == 2){
                printRow(day, filteredTasks.get(j).getTimePeriod(), filteredTasks.get(j).getStatusIcon(),
                        filteredTasks.get(j).getIndex() + ". " + filteredTasks.get(j).getDescription(),
                        filteredTasks.get(j).getDoOnDateString(), taskLength, doOnDateLength, i, ui);
                assert (j < filteredTasks.size());
                j++;
            } else if (i == 3) {
                printRow(date, filteredTasks.get(j).getTimePeriod(), filteredTasks.get(j).getStatusIcon(),
                        filteredTasks.get(j).getIndex() + ". " + filteredTasks.get(j).getDescription(),
                        filteredTasks.get(j).getDoOnDateString(), taskLength, doOnDateLength, i, ui);
                assert (j < filteredTasks.size());
                j++;
            } else {
                printRow("", filteredTasks.get(j).getTimePeriod(), filteredTasks.get(j).getStatusIcon(),
                        filteredTasks.get(j).getIndex() + ". " + filteredTasks.get(j).getDescription(),
                        filteredTasks.get(j).getDoOnDateString(), taskLength, doOnDateLength, i, ui);
                assert (j < filteredTasks.size());
                j++;
            }
        }
    }

    private static int calcPartitionLength(int taskLength, int dateLength) {
        return PARTITION_SPACE_OFFSET_LENGTH + taskLength + dateLength + PARTITION_PIPE_LINE_LENGTH;
    }

    private static void prepareTimetable(LocalDate dateInput, ArrayList<Task> filteredTasks, Ui ui) {
        String day = dateInput.format(dayOnlyFormat);
        String date = dateInput.format(dateOnlyFormat);
        int taskLength = findTaskLength(filteredTasks);
        int doOnDateLength = DATE_SPACE_FULL_LENGTH;
        int partitionLength = calcPartitionLength(taskLength, doOnDateLength);
        printTimetable(day, date, filteredTasks, ui, taskLength, doOnDateLength, partitionLength);
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
        if (filteredTasks.size() == 0) {
            ui.showToUser("Looks like you do not have any tasks added.\n"
                    + "Try adding some to generate your timetable.");
            return;
        }
        prepareTimetable(dateInput, filteredTasks, ui);
    }

    private static LocalDate resetDateToMonday(String currentDate, Ui ui) {
        switch(currentDate) {
        case "Mon":
            return LocalDate.now();
        case "Tue":
            return LocalDate.now().minus(1, ChronoUnit.DAYS);
        case "Wed":
            return LocalDate.now().minus(2, ChronoUnit.DAYS);
        case "Thu":
            return LocalDate.now().minus(3, ChronoUnit.DAYS);
        case "Fri":
            return LocalDate.now().minus(4, ChronoUnit.DAYS);
        case "Sat":
            return LocalDate.now().minus(5, ChronoUnit.DAYS);
        case "Sun":
            return LocalDate.now().minus(6, ChronoUnit.DAYS);
        default:
            ui.showToUser("Oops! There seems to be some error\n"
                    + "while running the system.\n"
                    + "Please contact the developers for help.\n");
            LOGGER.log(Level.WARNING, "Input that caused the error: " + currentDate);
            return null;
        }
    }

    /**
     * Generate a timetable for the current week.
     *
     * @param taskList Representation of an array of tasks.
     * @param ui       The user interface which interacts with the user.
     */
    public static void showScheduleOfTheWeek(TaskList taskList, Ui ui) {
        LocalDate currentDate = resetDateToMonday(LocalDate.now().format(dayOnlyFormat), ui);
        for (int i = 0; i < DAYS_IN_A_WEEK ; i++) {
            showScheduleByDay(currentDate, taskList, ui);
            assert (currentDate != null);
            currentDate.plus(1, ChronoUnit.DAYS);
        }
    }
}
