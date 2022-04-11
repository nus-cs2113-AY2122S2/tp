package seedu.sherpass.task;

import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;

public class TaskListTest {

    //@@author jltha
    @Test
    void checkDateTimeClash_sameStartTimeDifferentEndTime_TimeClashExceptionThrown() {
        Task testTaskOne = new Task(1, "blah", null,
                LocalDateTime.parse("31/3/2023 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 12:00", inputWithTimeFormat));
        ArrayList<Task> dummyList = new ArrayList<>();
        dummyList.add(testTaskOne);
        Task testTaskTwo = new Task(2, "smt", null,
                LocalDateTime.parse("31/3/2023 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 13:00", inputWithTimeFormat));
        assertThrows(TimeClashException.class,
            () -> TaskUtil.checkDateTimeClash(dummyList, testTaskTwo, false, false));
    }

    @Test
    void checkDateTimeClash_diffStartTimeSameEndTime_TimeClashExceptionThrown() {
        Task testTaskOne = new Task(1, "blah", null,
                LocalDateTime.parse("31/3/2023 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 12:00", inputWithTimeFormat));
        ArrayList<Task> dummyList = new ArrayList<>();
        dummyList.add(testTaskOne);
        Task testTaskTwo = new Task(2, "smt", null,
                LocalDateTime.parse("31/3/2023 11:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 12:00", inputWithTimeFormat));
        assertThrows(TimeClashException.class,
            () -> TaskUtil.checkDateTimeClash(dummyList, testTaskTwo, false, false));
    }

    @Test
    void checkDateTimeClash_startTimeEndTimeWithinCurrentTime_TimeClashExceptionThrown() {
        Task testTaskOne = new Task(1, "blah", null,
                LocalDateTime.parse("31/3/2023 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 12:00", inputWithTimeFormat));
        ArrayList<Task> dummyList = new ArrayList<>();
        dummyList.add(testTaskOne);
        Task testTaskTwo = new Task(2, "smt", null,
                LocalDateTime.parse("31/3/2023 10:30", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 11:00", inputWithTimeFormat));
        assertThrows(TimeClashException.class,
            () -> TaskUtil.checkDateTimeClash(dummyList, testTaskTwo, false, false));
    }

    @Test
    void checkDateTimeClash_startTimeEndTImeContainCurrentTime_TimeClashExceptionThrown() {
        Task testTaskOne = new Task(1, "blah", null,
                LocalDateTime.parse("31/3/2023 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 12:00", inputWithTimeFormat));
        ArrayList<Task> dummyList = new ArrayList<>();
        dummyList.add(testTaskOne);
        Task testTaskTwo = new Task(2, "smt", null,
                LocalDateTime.parse("31/3/2023 09:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 13:00", inputWithTimeFormat));
        assertThrows(TimeClashException.class,
            () -> TaskUtil.checkDateTimeClash(dummyList, testTaskTwo, false, false));
    }

    @Test
    void checkDateTimeClash_validInput_expectSuccess() throws TimeClashException, InvalidInputException {
        Task testTaskOne = new Task(1, "blah", null,
                LocalDateTime.parse("31/3/2023 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 12:00", inputWithTimeFormat));
        ArrayList<Task> dummyList = new ArrayList<>();
        dummyList.add(testTaskOne);
        Task testTaskTwo = new Task(2, "break time!", null,
                LocalDateTime.parse("31/3/2023 12:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2023 13:00", inputWithTimeFormat));
        TaskUtil.checkDateTimeClash(dummyList, testTaskTwo, false, false);
        assertTrue(true);
    }

    @Test
    void checkDateTimeClash_byDateBeforeDoOnDate_TimeClashExceptionThrown() {
        Task testTask = new Task(1, "blah",
                LocalDateTime.parse("15/2/2022 15:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2022 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2022 12:00", inputWithTimeFormat));
        ArrayList<Task> dummyList = new ArrayList<>();
        assertThrows(InvalidInputException.class,
            () -> TaskUtil.checkDateTimeClash(dummyList, testTask, false, false));
    }
}
