package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.Timetable;

import static seedu.duke.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.duke.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;

class AddCommandTest {
    private Timetable timetable;
    private Command addCommand;
    private Command addCommandDifferentEventSameTime;
    private Command addCommandStartTimeOverlap;
    private Command addCommandEndTimeOverlap;
    private Command addCommandTotalOverlap;

    @BeforeEach
    public void setUp() {
        timetable = new Timetable();
        addCommand = new AddCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
        addCommandStartTimeOverlap = new AddCommand(
                "John", "CS2113", "Monday",
                1200, 1400, "online"
        );
        addCommandEndTimeOverlap = new AddCommand(
                "John", "CS2113", "Monday",
                1100, 1300, "online"
        );
        addCommandDifferentEventSameTime = new AddCommand(
                "John", "CS2113T", "Monday",
                1200, 1300, "online"
        );
        addCommandTotalOverlap = new AddCommand(
                "John", "CS2113", "Monday",
                1000, 1600, "online"
        );
    }

    @Test
    public void addCommand_duplicateEvent_throwException() {
        addCommand.execute(timetable);
        assertEquals(ERROR_DUPLICATE_EVENT, addCommand.execute(timetable));
    }

    @Test
    public void addCommand_startTimeOverlap_throwException() {
        addCommand.execute(timetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandStartTimeOverlap.execute(timetable));
    }

    @Test
    public void addCommand_endTimeOverlap_throwException() {
        addCommand.execute(timetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandEndTimeOverlap.execute(timetable));
    }

    @Test
    public void addCommand_differentEventSameTime_throwException() {
        addCommand.execute(timetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandDifferentEventSameTime.execute(timetable));
    }

    @Test
    public void addCommand_totalOverlap_throwException() {
        addCommand.execute(timetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandTotalOverlap.execute(timetable));
    }

}