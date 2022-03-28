package seedu.meetingjio.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meetingjio.timetables.MasterTimetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.meetingjio.common.ErrorMessages.ERROR_FREE_INPUT_INVALID;
import static seedu.meetingjio.common.ErrorMessages.ERROR_NO_FREE_TIMESLOT;

public class FreeCommandTest {
    private MasterTimetable masterTimetable;

    private Command addCommand1;
    private Command addCommand2;

    private String answer;
    private String answerAllFree;
    private String answerBelowMinimum;

    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();

        addCommand1 = new AddLessonCommand(
                "John", "CS2113", "Friday",
                1600, 1800, "online"
        );

        addCommand2 = new AddLessonCommand(
                "Peter", "CS3235", "Monday",
                1000, 1100, "physical"
        );

        answerAllFree = "Monday 0800 2359\n"
                + "Tuesday 0800 2359\n"
                + "Wednesday 0800 2359\n"
                + "Thursday 0800 2359\n"
                + "Friday 0800 2359\n"
                + "Saturday 0800 2359\n"
                + "Sunday 0800 2359";

        answer = "Monday 0800 1000\n"
                + "Monday 1100 2359\n"
                + "Tuesday 0800 2359\n"
                + "Wednesday 0800 2359\n"
                + "Thursday 0800 2359\n"
                + "Friday 0800 1600\n"
                + "Friday 1800 2359\n"
                + "Saturday 0800 2359\n"
                + "Sunday 0800 2359";

        answerBelowMinimum = "Monday 1100 2359\n"
                + "Tuesday 0800 2359\n"
                + "Wednesday 0800 2359\n"
                + "Thursday 0800 2359\n"
                + "Friday 0800 1600\n"
                + "Friday 1800 2359\n"
                + "Saturday 0800 2359\n"
                + "Sunday 0800 2359";

    }

    /**
     * Test method to ensure that the programme outputs all possible timeslots where the Master Timetable is empty.
     */
    @Test
    public void freeCommand_emptyMasterTimetable() {
        FreeCommand freeCommand = new FreeCommand("");
        assertEquals(answerAllFree, freeCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme prints out the correct timeslots after adding events in timetables.
     */
    @Test
    public void freeCommand_someFree() {
        addCommand1.execute(masterTimetable);
        addCommand2.execute(masterTimetable);
        FreeCommand freeCommand = new FreeCommand("");
        assertEquals(answer, freeCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme filters out timeslots with a duration less than the specified minimum
     * duration.
     */
    @Test
    public void freeCommand_belowMinimum() {
        addCommand1.execute(masterTimetable);
        addCommand2.execute(masterTimetable);
        FreeCommand freeCommand = new FreeCommand("3");
        assertEquals(answerBelowMinimum, freeCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme informs user and continues running as intended when there are no common
     * timeslots where everyone is free.
     */
    @Test
    public void freeCommand_noFree() {
        addCommand1.execute(masterTimetable);
        addCommand2.execute(masterTimetable);
        FreeCommand freeCommand = new FreeCommand("20");
        assertEquals(ERROR_NO_FREE_TIMESLOT, freeCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme informs user and continues running as intended when the user's input is
     * invalid.
     */
    @Test
    public void freeCommand_invalidInput() {
        FreeCommand freeCommand = new FreeCommand("abc");
        assertEquals(ERROR_FREE_INPUT_INVALID, freeCommand.execute(masterTimetable));
    }

}
