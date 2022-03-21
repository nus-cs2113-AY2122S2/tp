package seedu.sherpass.task;

import java.time.LocalDateTime;

public class RecurringTask extends Task {
    public RecurringTask(String description, LocalDateTime doOnDate) {
        super(description,null, doOnDate);
    }
}
