//@@author yanjie1017

package seedu.meetingjio.timetables;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Lesson;
import seedu.meetingjio.timetables.Timetable;

import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;

class TimetableTest {
    Timetable timetable;
    Event event1;
    Event event2;
    Event event3;

    @BeforeEach
    public void setUp() {
        timetable = new Timetable("john");
        event1 = new Lesson("cs2113", "friday", 1200, 1330, "online");
        event2 = new Lesson("cs2113", "friday", 1400, 1600, "physical");
        event3 = new Lesson("cs2113", "friday", 1300, 1500, "physical");
    }

    @Test
    public void add_differentEvent_notThrowException() {
        assertDoesNotThrow(() -> timetable.add(event1));
        assertDoesNotThrow(() -> timetable.add(event2));
    }

    @Test
    public void add_duplicateEvent_throwException() {
        assertDoesNotThrow(() -> timetable.add(event1));
        assertThrows(DuplicateEventException.class, () -> timetable.add(event1));
    }

    @Test
    public void add_overlappingEvent_throwException() {
        assertDoesNotThrow(() -> timetable.add(event1));
        assertThrows(OverlappingEventException.class, () -> timetable.add(event3));
    }
}