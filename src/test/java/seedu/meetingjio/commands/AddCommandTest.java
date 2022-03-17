package seedu.meetingjio.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.Timetable;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;

class AddCommandTest {
    private Timetable timetable;
    private Command addCommand;
    private Command addCommandDifferentEventSameTime;
    private Command addCommandStartTimeOverlap;
    private Command addCommandEndTimeOverlap;
    private Command addCommandTotalOverlap;
    private Command addCommandOverlapDifferentDay;
    private Command addCommandOverlapDifferentName;

    /**
     * Set up multiple AddCommands to test out varying edge cases for overlapping lessons.
     */
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
        addCommandOverlapDifferentDay = new AddCommand(
                "John", "CS2113", "Friday",
                1200, 1300, "online"
        );
        addCommandOverlapDifferentName = new AddCommand(
                "Johnny", "CS2113", "Monday",
                1200, 1300, "online"
        );
    }

    @Test
    public void addCommand_duplicateEvent_throwException() {
        addCommand.execute(timetable);
        assertEquals(ERROR_DUPLICATE_EVENT, addCommand.execute(timetable));
    }

    /**
     * The following 4 test methods ensure that the program informs user and continues running smoothly
     * when an event to be added has a timing clash with an existing event in the user's timetable.
     */
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

    /**
     * Test method to ensure that no error is detected when the timing of an event to be added
     * overlaps with another event on a different day.
     */
    @Test
    public void addCommand_OverlapDifferentDay_noExceptionThrown() {
        addCommand.execute(timetable);
        assertNotEquals(ERROR_OVERLAPPING_EVENT, addCommandOverlapDifferentDay.execute(timetable));
    }

    /**
     * Test method to ensure that no error is detected when a person adds an event that has timing
     * clash with an event that another person has added.
     */
    @Test
    public void addCommand_OverlapDifferentName_noExceptionThrown() {
        addCommand.execute(timetable);
        assertNotEquals(ERROR_OVERLAPPING_EVENT, addCommandOverlapDifferentName.execute(timetable));
    }

}