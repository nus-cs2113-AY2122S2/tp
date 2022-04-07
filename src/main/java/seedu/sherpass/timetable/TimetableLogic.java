package seedu.sherpass.timetable;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.sherpass.Main.LOGGER;

import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_PIPE_LINE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_SPACE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_SPACE_COMPARE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_SPACE_COMPARE_OFFSET_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TASK_SPACE_FULL_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.WHITE_SPACE_FRONT_OFFSET_LENGTH;

public class TimetableLogic {
    protected static int findTaskDescriptionLength(ArrayList<Task> filteredTasks) {
        int max = 0;
        for (Task task : filteredTasks) {
            int taskLength = task.getDescription().length();
            if (taskLength > max) {
                max = taskLength;
            }
        }
        return max;
    }

    protected static int findTaskLength(ArrayList<Task> filteredTasks) {
        int descriptionLength = findTaskDescriptionLength(filteredTasks);
        if (descriptionLength > TASK_SPACE_COMPARE_LENGTH) {
            return descriptionLength + TASK_SPACE_COMPARE_OFFSET_LENGTH;
        }
        return TASK_SPACE_FULL_LENGTH;
    }

    protected static long calculateColBackWhiteSpace(long maxDescriptionLength,
                                                   String description) {
        return maxDescriptionLength - (description.length() + WHITE_SPACE_FRONT_OFFSET_LENGTH);
    }

    protected static long calcPartitionLength(long taskLength, long dateLength) {
        return PARTITION_SPACE_OFFSET_LENGTH + taskLength + dateLength + PARTITION_PIPE_LINE_LENGTH;
    }

    /**
     * Returns the LocalDate object with its day set to Monday.
     *
     *
     * @param localDate The date that is given
     * @param ui User interface
     * @return Returns LocalDate with the day of Monday
     */
    public static LocalDate resetDateToMonday(LocalDate localDate, Ui ui) {
        String currentDate = localDate.format(dayOnlyFormat);
        switch (currentDate) {
        case "Mon":
            return localDate;
        case "Tue":
            return localDate.minus(1, ChronoUnit.DAYS);
        case "Wed":
            return localDate.minus(2, ChronoUnit.DAYS);
        case "Thu":
            return localDate.minus(3, ChronoUnit.DAYS);
        case "Fri":
            return localDate.minus(4, ChronoUnit.DAYS);
        case "Sat":
            return localDate.minus(5, ChronoUnit.DAYS);
        case "Sun":
            return localDate.minus(6, ChronoUnit.DAYS);
        default:
            ui.showToUser("Oops! There seems to be some error\n"
                    + "while running the system.\n"
                    + "Please contact the developers for help.\n");
            LOGGER.log(Level.WARNING, "Input that caused the error: " + currentDate);
            return null;
        }
    }

    protected static void showMonthlySchedule(TaskList taskList, Ui ui, Month month) {
        LocalDate firstDayOfMonth = TimetableLogic.getFirstDayOfMonth(month);
        ArrayList<Task> monthlySchedule = taskList.getFilteredTasksByMonth(firstDayOfMonth);

        if (monthlySchedule.isEmpty()) {
            ui.showToUser(String.format("Your schedule is empty for %s", month));
        } else {
            taskList.printTaskList(monthlySchedule, ui);
        }
    }

    protected static LocalDate getFirstDayOfMonth(Month month) {
        Month currentMonth = LocalDate.now().getMonth();

        int year = LocalDate.now().getYear();

        if (isRequestedMonthAfterCurrentMonth(month, currentMonth)) {
            return LocalDate.of(year + 1, month, 1);
        } else {
            return LocalDate.of(year, month, 1);
        }
    }

    private static boolean isRequestedMonthAfterCurrentMonth(Month month, Month currentMonth) {
        return month.getValue() - currentMonth.getValue() < 0;
    }
}
