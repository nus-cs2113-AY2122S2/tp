//@@author lihao-InfoSec

package seedu.meetingjio.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_LOAD_LESSON;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_LOAD_LESSON;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_TIME_LOADING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_DAY_LOADING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_MODE_LOADING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_LOAD_MEETING;

import seedu.meetingjio.timetables.MasterTimetable;

public class ParserLocalDataTest {

    MasterTimetable masterTimetable = new MasterTimetable();

    /**
     * Test method to ensure that the program informs user on the specified error in loading lesson data
     * from the data file when there is missing headers.
     */
    @Test
    public void prepareLoadLesson_missingParameters_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[L] ITLE: cs2113 DAY: friday START: 1230 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_MISSING_PARAMETERS_LOAD_LESSON));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading lesson data
     * from the data file when there is missing value.
     */
    @Test
    public void prepareLoadLesson_missingValue_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[L] TITLE:  DAY: friday START: 1230 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_MISSING_VALUES_LOAD_LESSON));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading lesson data
     * from the data file when the data for 'time' is invalid.
     */
    @Test
    public void prepareLoadLesson_invalidTime_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[L] TITLE: cs2113 DAY: friday START: 12301 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_INVALID_TIME_LOADING));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading lesson data
     * from the data file when the data for 'day' is invalid.
     */
    @Test
    public void prepareLoadLesson_invalidDay_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[L] TITLE: cs2113 DAY: fri START: 1230 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_INVALID_DAY_LOADING));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading lesson data
     * from the data file when the data for 'mode' is invalid.
     */
    @Test
    public void prepareLoadLesson_invalidMode_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[L] TITLE: cs2113 DAY: friday START: 1230 END: 1330 MODE: on";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_INVALID_MODE_LOADING));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading meeting data
     * from the data file when there is missing headers.
     */
    @Test
    public void prepareLoadMeeting_missingParameters_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[M] : meeting DAY: thursday START: 1230 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadMeeting(data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_MISSING_PARAMETERS_LOAD_MEETING));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading meeting data
     * from the data file when there is missing value.
     */
    @Test
    public void prepareLoadMeeting_missingValue_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[M] TITLE:  DAY: thursday START: 1230 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_MISSING_VALUES_LOAD_LESSON));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading meeting data
     * from the data file when the data for 'time' is invalid.
     */
    @Test
    public void prepareLoadMeeting_invalidTime_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[M] TITLE: meeting DAY: thursday START: 12301 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_INVALID_TIME_LOADING));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading meeting data
     * from the data file when the data for 'day' is invalid.
     */
    @Test
    public void prepareLoadMeeting_invalidDay_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[M] TITLE: meeting DAY: thursdays START: 1230 END: 1330 MODE: online";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_INVALID_DAY_LOADING));
    }

    /**
     * Test method to ensure that the program informs user on the specified error in loading meeting data
     * from the data file when the data for 'mode' is invalid.
     */
    @Test
    public void prepareLoadMeeting_invalidMode_throwException() {
        String name = "john";
        ParserLocalData.prepareLoadName(name, masterTimetable);
        String data = "1.[M] TITLE: meeting DAY: thursday START: 1230 END: 1330 MODE: onli";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ERROR_INVALID_MODE_LOADING));
    }

}
