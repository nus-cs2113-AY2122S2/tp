package seedu.sherpass.util.timetable;

import seedu.sherpass.task.Task;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.sherpass.Main.LOGGER;
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
     * @param currentDate The current date
     * @param ui User interface
     * @return Returns LocalDate with the day of Monday
     */
    public static LocalDate resetDateToMonday(String currentDate, Ui ui) {
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
}
