package seedu.sherpass.timetable;

import seedu.sherpass.enums.TaskContentType;
import seedu.sherpass.task.Task;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.sherpass.Main.LOGGER;

import static seedu.sherpass.constant.DateAndTimeFormat.dayOnlyFormat;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_PIPE_LINE_LENGTH_ONE;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_SPACE_OFFSET_LENGTH_ONE;
import static seedu.sherpass.constant.TimetableConstant.PARTITION_SPACE_OFFSET_LENGTH_TWO;
import static seedu.sherpass.constant.TimetableConstant.WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE;
import static seedu.sherpass.constant.TimetableConstant.WHITE_SPACE_FRONT_OFFSET_LENGTH_TWO;

public class TimetableLogic {
    protected static long findTaskContentLength(ArrayList<Task> filteredTasks, TaskContentType type) {
        long max = 0;
        long contentLength;
        for (Task task : filteredTasks) {
            switch (type) {
            case TASK_DESCRIPTION:
                contentLength = task.getDescription().length();
                break;
            case TASK_NUMBER:
                contentLength = String.valueOf(task.getIndex()).length();
                break;
            case BY_DATE:
                contentLength = task.getByDateString().length();
                break;
            default:
                contentLength = task.getDoOnDateString().length();
            }
            if (contentLength > max) {
                max = contentLength;
            }
        }
        return max;
    }

    protected static long prepareTaskContentLength(ArrayList<Task> filteredTasks,
                                                   long taskContentCompareLength,
                                                   long taskOffsetLength,
                                                   long taskFullLength, TaskContentType type) {
        long descriptionLength = findTaskContentLength(filteredTasks, type);
        if (descriptionLength > taskContentCompareLength) {
            return descriptionLength + taskOffsetLength;
        }
        return taskFullLength;
    }

    protected static long calcOffset(boolean condition) {
        if (condition) {
            return WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE;
        }
        return WHITE_SPACE_FRONT_OFFSET_LENGTH_TWO;
    }

    protected static long calculateColBackWhiteSpace(long maxDescriptionLength,
                                                   String description, long offset) {
        return maxDescriptionLength - (description.length() + offset);
    }

    protected static long calcPartitionLength(long taskLength, long byDateLength,
                                              long doOnDateLength, long taskNumberLength,
                                              boolean isFullTimetable) {
        if (isFullTimetable) {
            return PARTITION_SPACE_OFFSET_LENGTH_ONE + taskLength + byDateLength + PARTITION_PIPE_LINE_LENGTH_ONE;
        }
        return PARTITION_SPACE_OFFSET_LENGTH_TWO + taskLength + byDateLength + doOnDateLength + taskNumberLength;
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
