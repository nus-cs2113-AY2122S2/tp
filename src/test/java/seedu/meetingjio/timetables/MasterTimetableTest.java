//@@author yanjie1017

package seedu.meetingjio.timetables;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Lesson;
import seedu.meetingjio.timetables.Timetable;

import seedu.meetingjio.exceptions.DuplicateTimetableException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;

class MasterTimetableTest {
    MasterTimetable masterTimetable;
    String johnName;
    Timetable johnTimetable;
    Lesson lesson;

    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();
        johnName = "john";
        johnTimetable = new Timetable(johnName);
        lesson = new Lesson("cs2113", "friday", 1200, 1400, "online");
    }

    @Test
    public void getByName_timetableNotExist_throwException() {
        assertThrows(TimetableNotFoundException.class, () -> masterTimetable.getByName(johnName));
    }

    @Test
    public void removeByName_timetableNotExist_exceptionNotThrown() {
        assertDoesNotThrow(() -> masterTimetable.removeByName(johnName));
    }

    @Test
    public void addTimetable_duplicateTimetable_throwException() {
        assertDoesNotThrow(() -> masterTimetable.addTimetable(johnTimetable));
        assertThrows(DuplicateTimetableException.class, () -> masterTimetable.addTimetable(johnTimetable));
    }

    @Test
    public void addLesson_timetableNotExist_throwException() {
        assertThrows(TimetableNotFoundException.class, () -> masterTimetable.addLesson(lesson, johnName));
    }
}