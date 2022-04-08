package seedu.meetingjio.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;

class AddLessonCommandTest {
    private MasterTimetable masterTimetable;
    private Command addCommand;
    private Command addCommandDifferentEventSameTime;
    private Command addCommandStartTimeOverlap;
    private Command addCommandEndTimeOverlap;
    private Command addCommandTotalOverlap;
    private Command addCommandOverlapDifferentDay;
    private Command addCommandOverlapDifferentName;
    private Command addUserJohn;
    private Command addUserJohnny;

    /**
     * Set up multiple AddCommands to test out varying edge cases for overlapping lessons.
     */
    @BeforeEach
    public void setUp() {

        masterTimetable = new MasterTimetable();

        addCommand = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
        addCommandStartTimeOverlap = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1200, 1400, "online"
        );
        addCommandEndTimeOverlap = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1100, 1300, "online"
        );
        addCommandDifferentEventSameTime = new AddLessonCommand(
                "John", "CS2113T", "Monday",
                1200, 1300, "online"
        );
        addCommandTotalOverlap = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1000, 1600, "online"
        );
        addCommandOverlapDifferentDay = new AddLessonCommand(
                "John", "CS2113", "Friday",
                1200, 1300, "online"
        );
        addCommandOverlapDifferentName = new AddLessonCommand(
                "Johnny", "CS2113", "Monday",
                1200, 1300, "online"
        );
        addUserJohn = new AddUserCommand("John");
        addUserJohnny = new AddUserCommand("Johnny");
    }

    /**
     * This test method checks that the program informs user and continues running smoothly when the user attempts to
     * add a event to a non-existing user.
     */
    @Test
    public void addCommand_nonExistsUser_throwException() {
        addUserJohnny.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertEquals(ERROR_INVALID_USER, addCommand.execute(masterTimetable));
    }

    /**
     * This test method checks that the program informs user and continues running smoothly when the user attempts to
     * add a particular event twice.
     */
    @Test
    public void addCommand_duplicateEvent_throwException() {
        addUserJohn.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertEquals(ERROR_DUPLICATE_EVENT, addCommand.execute(masterTimetable));
    }

    /**
     * The following 4 test methods ensure that the program informs user and continues running smoothly
     * when an event to be added has a timing clash with an existing event in the user's timetable.
     */
    @Test
    public void addCommand_startTimeOverlap_throwException() {
        addUserJohn.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandStartTimeOverlap.execute(masterTimetable));
    }

    @Test
    public void addCommand_endTimeOverlap_throwException() {
        addUserJohn.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandEndTimeOverlap.execute(masterTimetable));
    }

    @Test
    public void addCommand_differentEventSameTime_throwException() {
        addUserJohn.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandDifferentEventSameTime.execute(masterTimetable));
    }

    @Test
    public void addCommand_totalOverlap_throwException() {
        addUserJohn.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertEquals(ERROR_OVERLAPPING_EVENT, addCommandTotalOverlap.execute(masterTimetable));
    }

    /**
     * Test method to ensure that no error is detected when the timing of an event to be added
     * overlaps with another event on a different day.
     */
    @Test
    public void addCommand_OverlapDifferentDay_noExceptionThrown() {
        addUserJohn.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertNotEquals(ERROR_OVERLAPPING_EVENT, addCommandOverlapDifferentDay.execute(masterTimetable));
    }

    /**
     * Test method to ensure that no error is detected when a person adds an event that has timing
     * clash with an event that another person has added.
     */
    @Test
    public void addCommand_OverlapDifferentName_noExceptionThrown() {
        addUserJohn.execute(masterTimetable);
        addUserJohnny.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        assertNotEquals(ERROR_OVERLAPPING_EVENT, addCommandOverlapDifferentName.execute(masterTimetable));
    }

}