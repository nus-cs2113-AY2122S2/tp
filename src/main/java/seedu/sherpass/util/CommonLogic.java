package seedu.sherpass.util;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.TaskList;

import java.time.LocalDateTime;

public class CommonLogic {
    public static LocalDateTime incrementDate(LocalDateTime date, Frequency frequency) {
        if (frequency == Frequency.DAILY) {
            return date.plusDays(1);
        } else if (frequency == Frequency.WEEKLY) {
            return date.plusWeeks(1);
        }
        return date.plusMonths(1);
    }

    public static LocalDateTime getEndDateForRecurrence(LocalDateTime date, Frequency frequency) {
        if (frequency == Frequency.DAILY) {
            return date.plusMonths(1);
        } else if (frequency == Frequency.WEEKLY) {
            return date.plusMonths(2);
        }
        return date.plusYears(1);
    }
}
