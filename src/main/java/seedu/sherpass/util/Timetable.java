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
import static seedu.sherpass.constant.TimetableConstant.BLANK_MARK_STATUS;
import static seedu.sherpass.constant.TimetableConstant.BLANK_TIME_PERIOD;
import static seedu.sherpass.constant.TimetableConstant.DATE_SPACE_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.DAYS_IN_A_WEEK;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_PIPE_LINE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_SPACE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.STRING_COMPARE_OFFSET;
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

    private static long calculateColBackWhiteSpace(long maxDescriptionLength,
                                                   String description) {
        return maxDescriptionLength - (description.length() + WHITE_SPACE_FRONT_OFFSET_LENGTH);
    }

    private static void printRow(String colOne, String colTwo, String colThree,
                                 String colFour, String colFive,
                                 long taskLength, long doOnDateLength, int rowNumber, Ui ui) {
        String taskColumnBackWhiteSpace = ui.getRepeatedCharacters(" ",
                calculateColBackWhiteSpace(taskLength, colFour));
        String doOnDateColumnBackWhiteSpace = ui.getRepeatedCharacters(" ",
                calculateColBackWhiteSpace(doOnDateLength, colFive));
        if (rowNumber == 1) {
            ui.showToUser("|  " + colOne + "       |  " + colTwo + "       |  "
                    + colThree + " |  " + colFour + taskColumnBackWhiteSpace
                    + "|  " + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else if (rowNumber == 2) {
            ui.showToUser("|  " + colOne + "       | " + colTwo + " |      "
                    + colThree + "       |  " + colFour + taskColumnBackWhiteSpace
                    + "|  " + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else if (rowNumber == 3) {
            ui.showToUser("| " + colOne + " | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskColumnBackWhiteSpace + "|  "
                    + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else {
            ui.showToUser("|            | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskColumnBackWhiteSpace + "|  "
                    + colFive + doOnDateColumnBackWhiteSpace + "|");
        }
    }

    private static boolean isDuplicate(String currentString, String prevString, boolean isOriginal) {
        return currentString.equals(prevString) || !isOriginal;
    }

    private static void printTimetable(String day, String date, ArrayList<Task> filteredTasks,
                                       Ui ui, long taskLength, long doOnDateLength, long partitionLength) {
        int j = 0;
        String colOne;
        String colTwo = "Time";
        String colThree = "Mark Status";
        String colFour = "Task Description";
        String colFive = "Do on Date";
        boolean isOriginal = true;
        for (int i = 0; i < filteredTasks.size() + TIMETABLE_SIZE_OFFSET; i++) {
            if ((i == 0) || (i == filteredTasks.size() + TIMETABLE_SIZE_OFFSET - 1)) {
                ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
                continue;
            }

            colOne = (i == 1) ? "Day" : ((i == 2) ? day : date);
            if (i >= 2) {
                colTwo = (j < filteredTasks.size())
                        ? (isDuplicate(filteredTasks.get(j).getTimePeriod(), colTwo, isOriginal)
                        ? BLANK_TIME_PERIOD : filteredTasks.get(j).getTimePeriod()) : BLANK_TIME_PERIOD;
                isOriginal = j < filteredTasks.size()
                        && (!isDuplicate(filteredTasks.get(j).getTimePeriod(), colTwo, isOriginal));
                colThree = (j < filteredTasks.size()) ? filteredTasks.get(j).getStatusIcon() : BLANK_MARK_STATUS;
                colFour = (j < filteredTasks.size())
                        ? (filteredTasks.get(j).getIndex() + ". " + filteredTasks.get(j).getDescription())
                        : ui.getRepeatedCharacters(" ", taskLength - STRING_COMPARE_OFFSET);
                colFive = (j < filteredTasks.size()) ? filteredTasks.get(j).getDoOnDateString(true)
                        : ui.getRepeatedCharacters(" ", doOnDateLength - STRING_COMPARE_OFFSET);
                j++;
            }
            printRow(colOne, colTwo, colThree, colFour, colFive, taskLength, doOnDateLength, i, ui);
        }
    }

    private static long calcPartitionLength(long taskLength, long dateLength) {
        return PARTITION_SPACE_OFFSET_LENGTH + taskLength + dateLength + PARTITION_PIPE_LINE_LENGTH;
    }

    private static void printEmptyTimetable(Ui ui, String day, String date, long partitionLength) {
        ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
        ui.showToUser("|  Day       |  Time       |  Mark status |  Task Description    |  Do on date  |");
        String thirdRow = "|  " + day + "       |             Your schedule is empty for the day!";
        ui.showToUser(thirdRow + ui.getRepeatedCharacters(" ",
                partitionLength - thirdRow.length() - 1) + "|");
        String fourthRow = "| " + date + " |";
        ui.showToUser(fourthRow + ui.getRepeatedCharacters(" ",
                partitionLength - fourthRow.length() - 1) + "|");
        ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
    }

    private static void prepareTimetable(LocalDate dateInput, ArrayList<Task> filteredTasks, Ui ui) {
        String day = dateInput.format(dayOnlyFormat);
        String date = dateInput.format(dateOnlyFormat);
        long taskLength = findTaskLength(filteredTasks);
        long doOnDateLength = DATE_SPACE_FULL_LENGTH;
        long partitionLength = calcPartitionLength(taskLength, doOnDateLength);
        if (filteredTasks.isEmpty()) {
            printEmptyTimetable(ui, day, date, partitionLength);
            return;
        }
        printTimetable(day, date, filteredTasks, ui, taskLength, doOnDateLength, partitionLength);
    }

    public static void showTodaySchedule(TaskList taskList, Ui ui) {
        ArrayList<Task> filteredTasks = taskList.getFilteredTasksByDate(LocalDate.now());
        prepareTimetable(LocalDate.now(), filteredTasks, ui);
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
        prepareTimetable(dateInput, filteredTasks, ui);
    }

    private static LocalDate resetDateToMonday(String currentDate, Ui ui) {
        switch (currentDate) {
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
        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            showScheduleByDay(currentDate, taskList, ui);
            assert (currentDate != null);
            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
    }
}
