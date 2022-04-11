//@@author yanjie1017

package seedu.meetingjio.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.timetables.MasterTimetable;

import java.util.HashMap;
import java.util.Map;

import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_ATTRIBUTE_VALUE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_INDEX;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EDIT_MEETING;

class EditCommandTest {
    MasterTimetable masterTimetable;
    Command addUserJohn;
    Command addLesson1;
    Command addLesson2;
    Command addMeeting;

    private static final String TITLE = "t";
    private static final String DAY = "d";
    private static final String START_TIME = "st";
    private static final String END_TIME = "et";
    private static final String MODE = "m";
    private static final String ABORT = " Edit aborted.";

    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();
        addUserJohn = new AddUserCommand("john");
        addLesson1 = new AddLessonCommand("john", "cs2113", "monday", 1200, 1300, "online");
        addLesson2 = new AddLessonCommand("john", "cs2113", "tuesday", 1500, 1800, "online");
        addMeeting = new AddMeetingCommand("meeting", "friday", 1000, 1200, "online");
        addUserJohn.execute(masterTimetable);
        addLesson1.execute(masterTimetable);
        addLesson2.execute(masterTimetable);
        addMeeting.execute(masterTimetable);
    }

    @Test
    public void editCommand_timetableNotFound_throwException() {
        String name = "alex";
        int index = 1;
        Map<String, String> newValues = new HashMap<>();
        newValues.put(TITLE, "cs2030");

        EditCommand editCommand = new EditCommand(name, index, newValues);
        assertEquals(ERROR_INVALID_USER + ABORT, editCommand.execute(masterTimetable));
    }

    @Test
    public void editCommand_editMeeting_throwException() {
        String name = "john";
        int index = 3;
        Map<String, String> newValues = new HashMap<>();
        newValues.put(TITLE, "cs2030");

        EditCommand editCommand = new EditCommand(name, index, newValues);
        assertEquals(ERROR_EDIT_MEETING + ABORT, editCommand.execute(masterTimetable));
    }

    @Test
    public void editCommand_invalidIndex_throwException() {
        String name = "john";
        int index = 4;
        Map<String, String> newValues = new HashMap<>();
        newValues.put(TITLE, "cs2030");

        EditCommand editCommand = new EditCommand(name, index, newValues);
        assertEquals(ERROR_INVALID_INDEX + ABORT, editCommand.execute(masterTimetable));
    }

    @Test
    public void editCommand_invalidDayValue_throwException() {
        String name = "john";
        int index = 1;
        Map<String, String> newValues = new HashMap<>();
        newValues.put(DAY, "mond");

        EditCommand editCommand = new EditCommand(name, index, newValues);
        assertEquals(ERROR_INVALID_ATTRIBUTE_VALUE + ABORT, editCommand.execute(masterTimetable));
    }

    @Test
    public void editCommand_invalidModeValue_throwException() {
        String name = "john";
        int index = 1;
        Map<String, String> newValues = new HashMap<>();
        newValues.put(MODE, "onl");

        EditCommand editCommand = new EditCommand(name, index, newValues);
        assertEquals(ERROR_INVALID_ATTRIBUTE_VALUE + ABORT, editCommand.execute(masterTimetable));
    }

    @Test
    public void editCommand_invalidTimeValue_throwException() {
        String name = "john";
        int index = 1;
        Map<String, String> newValues = new HashMap<>();
        newValues.put(START_TIME, "1299");

        EditCommand editCommand = new EditCommand(name, index, newValues);
        assertEquals(ERROR_INVALID_ATTRIBUTE_VALUE + ABORT, editCommand.execute(masterTimetable));
    }

    @Test
    public void editCommand_overlappingEvent_throwException() {
        String name = "john";
        int index = 2;
        Map<String, String> newValues = new HashMap<>();
        newValues.put(DAY, "monday");
        newValues.put(START_TIME, "1200");

        EditCommand editCommand = new EditCommand(name, index, newValues);
        assertEquals(ERROR_OVERLAPPING_EVENT + ABORT, editCommand.execute(masterTimetable));
    }

}