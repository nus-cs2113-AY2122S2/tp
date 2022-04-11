//@@author angyongming

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
    private Command addUserJohn;
    private Command addUserJohnny;
    private Command addMeetingCommand1;
    private Command addMeetingCommand2;
    private Command addMeetingCommand3;
    private Command addMeetingCommand4;
    private Command addMeetingCommand5;

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
                "Johnny", "CS3235", "Monday",
                1000, 1100, "physical"
        );

        addMeetingCommand1 = new AddMeetingCommand("meeting", "Tuesday",
                1230, 1330, "online"
        );

        addMeetingCommand2 = new AddMeetingCommand("meeting", "Wednesday",
                1230, 1330, "online"
        );

        addMeetingCommand3 = new AddMeetingCommand("meeting", "Thursday",
                1230, 1330, "online"
        );

        addMeetingCommand4 = new AddMeetingCommand("meeting", "Saturday",
                1230, 1330, "online"
        );

        addMeetingCommand5 = new AddMeetingCommand("meeting", "Sunday",
                1230, 1330, "online"
        );

        addUserJohn = new AddUserCommand("John");
        addUserJohnny = new AddUserCommand("Johnny");

        answerAllFree = "Monday 0000 2359\n"
                + "Tuesday 0000 2359\n"
                + "Wednesday 0000 2359\n"
                + "Thursday 0000 2359\n"
                + "Friday 0000 2359\n"
                + "Saturday 0000 2359\n"
                + "Sunday 0000 2359";

        answer = "Monday 0000 1000\n"
                + "Monday 1100 2359\n"
                + "Tuesday 0000 2359\n"
                + "Wednesday 0000 2359\n"
                + "Thursday 0000 2359\n"
                + "Friday 0000 1600\n"
                + "Friday 1800 2359\n"
                + "Saturday 0000 2359\n"
                + "Sunday 0000 2359";

        answerBelowMinimum = "Monday 1100 2359\n"
                + "Tuesday 0000 2359\n"
                + "Wednesday 0000 2359\n"
                + "Thursday 0000 2359\n"
                + "Friday 0000 1600\n"
                + "Saturday 0000 2359\n"
                + "Sunday 0000 2359";

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
        addUserJohn.execute(masterTimetable);
        addUserJohnny.execute(masterTimetable);
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
        addUserJohn.execute(masterTimetable);
        addUserJohnny.execute(masterTimetable);
        addCommand1.execute(masterTimetable);
        addCommand2.execute(masterTimetable);
        FreeCommand freeCommand = new FreeCommand("13");
        assertEquals(answerBelowMinimum, freeCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme informs user and continues running as intended when there are no common
     * timeslots where everyone is free.
     */
    @Test
    public void freeCommand_noFree_throwException() {
        addUserJohn.execute(masterTimetable);
        addUserJohnny.execute(masterTimetable);
        addCommand1.execute(masterTimetable);
        addCommand2.execute(masterTimetable);
        addMeetingCommand1.execute(masterTimetable);
        addMeetingCommand2.execute(masterTimetable);
        addMeetingCommand3.execute(masterTimetable);
        addMeetingCommand4.execute(masterTimetable);
        addMeetingCommand5.execute(masterTimetable);
        FreeCommand freeCommand = new FreeCommand("23");
        assertEquals(ERROR_NO_FREE_TIMESLOT, freeCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme informs user and continues running as intended when the user's input is
     * invalid.
     */
    @Test
    public void freeCommand_invalidInput_throwException() {
        FreeCommand freeCommand = new FreeCommand("abc");
        assertEquals(ERROR_FREE_INPUT_INVALID, freeCommand.execute(masterTimetable));

        FreeCommand freeCommand2 = new FreeCommand("0.5");
        assertEquals(ERROR_FREE_INPUT_INVALID, freeCommand2.execute(masterTimetable));

        FreeCommand freeCommand3 = new FreeCommand("-5");
        assertEquals(ERROR_FREE_INPUT_INVALID, freeCommand3.execute(masterTimetable));

        FreeCommand freeCommand4 = new FreeCommand("25");
        assertEquals(ERROR_FREE_INPUT_INVALID, freeCommand4.execute(masterTimetable));
    }

}
