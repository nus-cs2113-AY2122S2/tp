package seedu.meetingjio.commands;

import org.junit.jupiter.api.Test;
import seedu.meetingjio.parser.Parser;
import seedu.meetingjio.timetables.MasterTimetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.meetingjio.common.ErrorMessages.*;

public class ParserTest {

    MasterTimetable masterTimetable = new MasterTimetable();

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when the input for 'day' is invalid.
     */
    @Test
    public void prepareAdd_invalidDay_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/mon st/1600 et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_DAY, command.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when the input for 'time' is invalid.
     */
    @Test
    public void prepareAdd_timeOutOfRange_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/monday st/1690 et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_TIME, command.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when the given start time is later than the given end time.
     */
    @Test
    public void prepareAdd_startTimeGreaterThanEndTime_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/monday st/1800 et/1600 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_TIME, command.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when the given start time is equal to the given end time.
     */
    @Test
    public void prepareAdd_startTimeEqualToEndTime_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/monday st/1600 et/1600 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_TIME, command.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when the input for 'time' is not an integer.
     */
    @Test
    public void prepareAdd_timeNotInteger_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/mon st/noon et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_TIME, command.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when the input for 'mode' is invalid.
     */
    @Test
    public void prepareAdd_invalidMode_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/monday st/1600 et/1800 m/lecture";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_MODE, command.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when certain parameters are missing from the user's input.
     */
    @Test
    public void prepareAddLesson_missingParameters_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/monday st/1600 et/1800";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_ADD_EVENT, command.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program informs user and continues running smoothly
     * when the values of certain parameters are missing from the user's input.
     */
    @Test
    public void prepareAddLesson_parametersMissingValues_throwException() {
        String inputString = "add_lesson n/john t/cs2113 d/ st/1600 et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES_ADD_EVENT, command.execute(masterTimetable));
    }


    @Test
    public void prepareAddMeetingNoUsers() {
        ClearCommand clearCommand = new ClearCommand("all");
        clearCommand.execute(masterTimetable);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand("meeting", "Thursday",
                1230, 1330, "online"
        );
        assertEquals(ERROR_NO_USER_TO_ADD_MEETING, addMeetingCommand.execute(masterTimetable));
    }
    @Test
    public void prepareAddDuplicateMeetings() {
        ClearCommand clearCommand = new ClearCommand("all");
        clearCommand.execute(masterTimetable);
        AddUserCommand addUserOne = new AddUserCommand("john");
        addUserOne.execute(masterTimetable);
        AddMeetingCommand addMeetingCommandOne = new AddMeetingCommand("meeting", "Thursday",
                1230, 1330, "online"
        );
        AddMeetingCommand addMeetingCommandTwo = new AddMeetingCommand("meeting", "Thursday",
                1230, 1330, "online"
        );
        addMeetingCommandOne.execute(masterTimetable);
        assertEquals(ERROR_DUPLICATE_MEETING, addMeetingCommandTwo.execute(masterTimetable));
    }

    @Test
    public void prepareAddOverlappingMeetings() {
        ClearCommand clearCommand = new ClearCommand("all");
        clearCommand.execute(masterTimetable);
        AddUserCommand addUserOne = new AddUserCommand("john");
        addUserOne.execute(masterTimetable);
        AddLessonCommand addCommand = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
        addCommand.execute(masterTimetable);
        AddMeetingCommand addMeetingCommandOne = new AddMeetingCommand("meeting", "Monday",
                1230, 1330, "online"
        );
        AddMeetingCommand addMeetingCommandTwo = new AddMeetingCommand("meeting", "Monday",
                1230, 1330, "online"
        );
        addMeetingCommandOne.execute(masterTimetable);
        assertEquals(ERROR_OVERLAPPING_MEETING, addMeetingCommandTwo.execute(masterTimetable));
    }

    /*
    @Test
    public void prepareDeleteCommand_invalidIndex_throwException() {
        String inputString = "delete hello";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_INDEX_FORMAT, command.execute(masterTimetable));
    }

    @Test
    public void prepareDeleteCommand_noIndex_throwException() {
        String inputString = "delete";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_INDEX_FORMAT, command.execute(masterTimetable));
    }
    */
}
