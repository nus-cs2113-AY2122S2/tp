package seedu.sherpass.task;

import java.util.Comparator;

public class TaskDateComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getDoOnStartDateTime().isBefore(t2.getDoOnStartDateTime())) {
            return -1;
        }
        if (t1.getDoOnStartDateTime().isAfter(t2.getDoOnStartDateTime())) {
            return 1;
        }
        return 0;
    }
}
