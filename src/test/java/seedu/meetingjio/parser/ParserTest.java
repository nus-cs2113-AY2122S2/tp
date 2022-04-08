package seedu.meetingjio.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.meetingjio.parser.Parser;
import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_MODE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_TIME;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_DAY;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_ADD_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_ADD_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_ADD_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_ADD_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_DELETE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_INDEX;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_DELETE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EXTRA_PARAMETERS;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_ADD_USER;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_NAME;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_EDIT;

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
    public void prepareAddMeeting_MissingParameter_throwException() {
        String inputString = "add_meeting d/Thursday st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_missingParameterValuesGeneral_throwException() {
        String inputString = "add_meeting t/ d/Thursday st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_missingParameterDay_throwException() {
        String inputString = "add_meeting t/meeting st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_missingParameterValuesTwo_throwException() {
        String inputString = "add_meeting t/ d/ st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        //should check parameters too
        assertEquals(ERROR_MISSING_VALUES_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_missingParameterValuesDay_throwException() {
        String inputString = "add_meeting t/t d/ st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES_ADD_MEETING, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_invalidDay_throwException() {
        String inputString = "add_meeting t/meeting d/myday st/1230 et/1330 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_DAY, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_invalidMode_throwException() {
        String inputString = "add_meeting t/meeting d/Thursday st/1230 et/1330 m/ONLINE fdhxg";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_MODE, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_extraParameters_throwException() {
        String inputString = "add_meeting l/ t/funny d/Thursday st/1230 et/1330 m/ONLINE";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        ClearCommand clearCommand = new ClearCommand("all");
        clearCommand.execute(masterTimetable);
        AddUserCommand addUser = new AddUserCommand("john");
        addUser.execute(masterTimetable);
        AddLessonCommand addCommand = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
        assertEquals(ERROR_EXTRA_PARAMETERS, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_testUpperCaseMode_throwException() {
        String inputString = "add_meeting t/meeting d/Thursday st/1230 et/1330 m/ONLINE";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();

        ClearCommand clearCommand = new ClearCommand("all");
        clearCommand.execute(masterTimetable);
        AddUserCommand addUser = new AddUserCommand("john");
        addUser.execute(masterTimetable);
        AddLessonCommand addCommand = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
        addCommand.execute(masterTimetable);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand("meeting", "thursday",
                1230, 1330, "online"
        );
        String expectedOutput = "The following meeting has been added to everyone's timetable:\n"
                + "[M] TITLE: meeting\t\tDAY: thursday\t\tSTART: 1230\t\tEND: 1330\t\tMODE: online";
        assertEquals(expectedOutput, addMeetingCommand.execute(masterTimetable));
    }

    @Test
    public void prepareAddMeeting_trailingSpaces_throwException() {
        String inputString = "ADD_MEETING t/meeting d/Thursday st/1230 et/1330 m/online hjzgfxhgjk";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_MODE, command.execute(masterTimetable));
    }


    @Test
    public void prepareDeleteCommand_missingParametersIndex_throwException() {
        String inputString = "delete n/john";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_DELETE, command.execute(masterTimetable));
    }

    @Test
    public void prepareDeleteCommand_missingParametersName_throwException() {
        String inputString = "delete i/3";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_DELETE, command.execute(masterTimetable));
    }

    @Test
    public void prepareDeleteCommand_noIndex_throwException() {
        String inputString = "delete";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_DELETE, command.execute(masterTimetable));
    }

    @Test
    public void prepareDeleteCommand_missingValues_throwException() {
        String inputString = "delete n/ i/3";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES_DELETE, command.execute(masterTimetable));
    }

    @Test
    public void prepareDeleteCommand_IndexOutOfBounds_throwException() {
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
        String inputString = "delete n/john i/5";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_INDEX, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddUser_emptyName_throwExceptionn() {
        String inputString = "add_user";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES_ADD_USER, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddUser_allName_throwExceptionn() {
        String inputString = "add_user all";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_NAME, command.execute(masterTimetable));
    }

    @Test
    public void prepareAddUser_nameWithSpecialChar_throwExceptionn() {
        String inputString = "add_user john@1";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_NAME, command.execute(masterTimetable));
    }

    @Test
    public void prepareEdit_missingRequiredParamsIndex_throwExceptionn() {
        String inputString = "edit n/john t/cs2030";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_EDIT, command.execute(masterTimetable));
    }

    @Test
    public void prepareEdit_missingRequiredParamsName_throwExceptionn() {
        String inputString = "edit i/1 t/cs2030";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_EDIT, command.execute(masterTimetable));
    }

    @Test
    public void prepareEdit_missingOptionalParams_throwExceptionn() {
        String inputString = "edit n/john i/1";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS_EDIT, command.execute(masterTimetable));
    }

    @Test
    public void prepareEdit_invalidIndex_throwExceptionn() {
        String inputString = "edit n/john i/a t/cs2030";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_INDEX, command.execute(masterTimetable));
    }

}
