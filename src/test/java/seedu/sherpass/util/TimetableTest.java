package seedu.sherpass.util;

import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimetableTest {

    @Test
    void prepareTimetable_TodayDate_expectTodayTimetable() {
        ArrayList<Task> dummyList = new ArrayList<>();
        Ui ui = new Ui();
        Task testTask = new Task("submit DG", LocalDate.now(), null);
        dummyList.add(testTask);
        TaskList testList = new TaskList(dummyList);
        ArrayList<Task> filteredList = testList.getFilteredTasksByDate(LocalDate.now());

        Timetable actualTimetable = Timetable.prepareTimetable(LocalDate.now(), filteredList, ui);
        Timetable expectTimetable = Timetable.prepareTimetable(LocalDate.now(), dummyList, ui);

        assertEquals(expectTimetable, actualTimetable);
    }

    @Test
    void prepareTimetable_TodayDate_expectEmptyTimetable() {
        ArrayList<Task> testArrayList = new ArrayList<>();
        Task testTask = new Task("testing", LocalDate.now().plusDays(1), null);
        testArrayList.add(testTask);
        TaskList actualTaskList = new TaskList(testArrayList);
        ArrayList<Task> filteredList = actualTaskList.getFilteredTasksByDate(LocalDate.now());
        Ui ui = new Ui();

        Timetable actualTimetable = Timetable.prepareTimetable(LocalDate.now(), filteredList, ui);
        Timetable expectTimetable = Timetable.prepareTimetable(LocalDate.now(), new ArrayList<>(), ui);

        assertEquals(expectTimetable, actualTimetable);
    }
}
