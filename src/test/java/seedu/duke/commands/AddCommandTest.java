package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.Timetable;

import static seedu.duke.common.ErrorMessages.ERROR_DUPLICATE_EVENT;

class AddCommandTest {
    private Timetable timetable;
    private Command addCommand;

    @BeforeEach
    public void setUp() {
        timetable = new Timetable();
        addCommand = new AddCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
    }

    @Test
    public void addCommand_duplicateEvent_throwException() {
        addCommand.execute(timetable);
        assertEquals(ERROR_DUPLICATE_EVENT, addCommand.execute(timetable));
    }
}