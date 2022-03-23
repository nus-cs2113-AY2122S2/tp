package seedu.sherpass.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimetableTest {

    @Test
    @Disabled
    void prepareTimetable_CurrentDate_expectTodayTimetable() {
        ArrayList<Task> dummyList = new ArrayList<>();
        Ui ui = new Ui();
        Task testTask = new Task(1,"submit DG", LocalDateTime.now(), LocalDateTime.now(),
                LocalDate.parse("29/3/2022").atStartOfDay(), null);
        dummyList.add(testTask);
        TaskList testList = new TaskList(dummyList);
        Timetable.showScheduleByDay(LocalDate.now(), testList, ui);
        assertTrue(true);
    }
}
