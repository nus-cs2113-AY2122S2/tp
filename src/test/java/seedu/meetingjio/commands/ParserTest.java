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
    public void prepareAddMeetingMissingParameter() {
        String inputString = "add_meeting d/Thursday st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeetingMissingParameterValuesGeneral() {
        String inputString = "add_meeting t/ d/Thursday st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeetingMissingParameterDay() {
        String inputString = "add_meeting t/meeting st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeetingMissingParameterValuesTwo() {
        String inputString = "add_meeting t/ d/ st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        //should check parameters too
        assertEquals(ERROR_MISSING_VALUES_ADD_MEETING, command.execute(masterTimetable));
    }
    @Test
    public void prepareAddMeetingMissingParameterValuesDay() {
        String inputString = "add_meeting t/t d/ st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeetingInvalidDay() {
        String inputString = "add_meeting t/meeting d/myday st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_DAY, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeetingInvalidDay() {
        String inputString = "add_meeting t/meeting d/myday st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_DAY, command.execute(masterTimetable));
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
