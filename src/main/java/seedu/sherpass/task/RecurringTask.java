package seedu.sherpass.task;

import java.time.LocalDateTime;

public class RecurringTask extends Task {

    public RecurringTask(String description, LocalDateTime doOnDate, boolean hasDoOnTime) {
        super(description,null, doOnDate, false, hasDoOnTime);
    }
}
