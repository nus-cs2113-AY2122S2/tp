package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seedu.duke.events.Event;
import seedu.duke.events.Lesson;
import seedu.duke.Timetable;

import seedu.duke.exceptions.DuplicateEventException;

class AddCommandTest {
    @Test
    public void addCommand_duplicateEvent_throwException() {
        Timetable timetable = new Timetable();
        AddCommand addCommand = new AddCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
        addCommand.execute(timetable);
        assertEquals(DuplicateEventException.message, addCommand.execute(timetable));
    }

    // days of week
    // mode
    // startTime/endTime
}