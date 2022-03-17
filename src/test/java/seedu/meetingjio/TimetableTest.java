package seedu.meetingjio;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Lesson;
import seedu.meetingjio.exceptions.DuplicateEventException;


class TimetableTest {
    private Timetable timetable;
    private Event event1;
    private Event event2;

    @BeforeEach
    public void setUp() {
        timetable = new Timetable();
        event1 = new Lesson("John","CS2113","Friday",
                1200,1330,"online");
        event2 = new Lesson("John","CS2113","Friday",
                1400,1600,"physical");
    }

    @Test
    public void add_differentEvent_notThrowException() {
        timetable.clear();
        assertDoesNotThrow(() -> timetable.add(event1));
        assertDoesNotThrow(() -> timetable.add(event2));
    }

    @Test
    public void add_duplicateEvent_throwException() {
        timetable.clear();
        assertDoesNotThrow(() -> timetable.add(event1));
        assertThrows(DuplicateEventException.class, () -> timetable.add(event1));
    }
}