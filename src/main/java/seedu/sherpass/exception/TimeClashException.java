package seedu.sherpass.exception;

import seedu.sherpass.task.Task;

public class TimeClashException extends Exception {

    public TimeClashException(Task clashingTask) {
        super(clashingTask.toString());
    }
}
