//@@author lihao-InfoSec

package seedu.meetingjio.parser;

import seedu.meetingjio.commands.AddLessonCommand;
import seedu.meetingjio.commands.AddMeetingCommand;
import seedu.meetingjio.exceptions.InvalidEventTypeException;
import seedu.meetingjio.exceptions.DuplicateTimetableException;
import seedu.meetingjio.exceptions.InvalidNameException;
import seedu.meetingjio.exceptions.MissingValueException;
import seedu.meetingjio.exceptions.InvalidTimeException;
import seedu.meetingjio.exceptions.InvalidDayException;
import seedu.meetingjio.exceptions.InvalidModeException;
import seedu.meetingjio.exceptions.MissingParameterException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.*;

public class ParserLocalData {

    private static final String[] HEADINGS = {"TITLE:", "DAY:", "START:", "END:", "MODE:"};
    private static final int TITLE_INDEX = 0;
    private static final int TITLE_CHAR_COUNT = 6;
    private static final int DAY_INDEX = 1;
    private static final int DAY_CHAR_COUNT = 4;
    private static final int START_TIME_INDEX = 2;
    private static final int START_TIME_CHAR_COUNT = 6;
    private static final int END_TIME_INDEX = 3;
    private static final int END_TIME_CHAR_COUNT = 4;
    private static final int MODE_INDEX = 4;
    private static final int MODE_CHAR_COUNT = 5;
    private static final String EVENT_TYPE_DELIMITER_FRONT = "[";
    private static final String EVENT_TYPE_DELIMITER_BACK = "]";
    private static ParserHelperMethods parserHelperMethods = new ParserHelperMethods();
    public static final String MEETING_SYMBOL = "M";
    public static final String LESSON_SYMBOL = "L";

    /**
     * Creates a person's timetable.
     *
     * @param name name given in the data file
     * @param masterTimetable Array of timetables
     * @throws RuntimeException if there is duplicate name found in the data file
     */
    public static void prepareLoadName(String name, MasterTimetable masterTimetable) {
        try {
            parserHelperMethods.checkName(name);
            Timetable timetable = new Timetable(name);
            masterTimetable.addTimetable(timetable);
        } catch (DuplicateTimetableException det) {
            throw new RuntimeException("[" + name + "] " + ERROR_DUPLICATE_USER_LOAD_NAME);
        } catch (InvalidNameException ine) {
            throw new RuntimeException("[" + name + "] " + ERROR_INVALID_NAME);
        }
    }

    /**
     * Loads lesson into the masterTimetable.
     *
     * @param name name given in the data file
     * @param data a row of data in the data file
     * @param masterTimetable Array of timetables
     * @return feedback on the execution
     * @throws RuntimeException if any of the specified exceptions is caught during the process
     */
    public static String prepareLoadLesson(String name, String data, MasterTimetable masterTimetable) {
        try {
            checkHeadings(data);
            String[] eventDescription = splitArguments(data);
            parserHelperMethods.checkNonNullValues(eventDescription);
            String day = eventDescription[DAY_INDEX].toLowerCase();
            int startTime = Integer.parseInt(eventDescription[START_TIME_INDEX]);
            int endTime = Integer.parseInt(eventDescription[END_TIME_INDEX]);
            String mode = eventDescription[MODE_INDEX].toLowerCase();
            parserHelperMethods.checkDay(day);
            parserHelperMethods.checkTime(startTime, endTime);
            parserHelperMethods.checkMode(mode);
            String title = eventDescription[TITLE_INDEX];
            return new AddLessonCommand(name, title, day, startTime, endTime, mode).execute(masterTimetable);

        } catch (ArrayIndexOutOfBoundsException | NullPointerException npe) {
            throw new RuntimeException(ERROR_MISSING_PARAMETERS_LOAD_LESSON);
        } catch (MissingValueException mve) {
            throw new RuntimeException(ERROR_MISSING_VALUES_LOAD_LESSON);
        } catch (InvalidTimeException | NumberFormatException ite) {
            throw new RuntimeException(ERROR_INVALID_TIME_LOADING);
        } catch (InvalidDayException ide) {
            throw new RuntimeException(ERROR_INVALID_DAY_LOADING);
        } catch (InvalidModeException ime) {
            throw new RuntimeException(ERROR_INVALID_MODE_LOADING);
        } catch (MissingParameterException mpe) {
            throw new RuntimeException(ERROR_MISSING_PARAMETERS_LOAD_LESSON);
        }
    }

    /**
     * Loads meeting into the masterTimetable.
     *
     * @param data a row of data in the data file
     * @param masterTimetable Array of timetables
     * @return feedback on the execution
     * @throws RuntimeException if any of the specified exceptions is caught during the process
     */
    public static String prepareLoadMeeting(String data, MasterTimetable masterTimetable) {
        try {
            checkHeadings(data);
            String[] eventDescription = splitArguments(data);
            parserHelperMethods.checkNonNullValues(eventDescription);
            String day = eventDescription[DAY_INDEX].toLowerCase();
            int startTime = Integer.parseInt(eventDescription[START_TIME_INDEX]);
            int endTime = Integer.parseInt(eventDescription[END_TIME_INDEX]);
            String mode = eventDescription[MODE_INDEX].toLowerCase();
            parserHelperMethods.checkDay(day);
            parserHelperMethods.checkTime(startTime, endTime);
            parserHelperMethods.checkMode(mode);
            String title = eventDescription[TITLE_INDEX];
            return new AddMeetingCommand(title, day, startTime, endTime, mode).execute(masterTimetable);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException npe) {
            throw new RuntimeException(ERROR_MISSING_PARAMETERS_LOAD_MEETING);
        } catch (MissingValueException mve) {
            throw new RuntimeException(ERROR_MISSING_VALUES_LOAD_LESSON);
        } catch (InvalidTimeException | NumberFormatException ite) {
            throw new RuntimeException(ERROR_INVALID_TIME_LOADING);
        } catch (InvalidDayException ide) {
            throw new RuntimeException(ERROR_INVALID_DAY_LOADING);
        } catch (InvalidModeException ime) {
            throw new RuntimeException(ERROR_INVALID_MODE_LOADING);
        } catch (MissingParameterException mpe) {
            throw new RuntimeException(ERROR_MISSING_PARAMETERS_LOAD_MEETING);
        }
    }

    /**
     * Splits the data and get the value for each parameter.
     *
     * @param data a row of data in the data file
     * @return eventDescription an array of value for each parameter
     */
    private static String[] splitArguments(String data) {
        String[] eventDescription = new String[5];
        eventDescription[TITLE_INDEX] = getTitle(data);
        eventDescription[DAY_INDEX] = getDay(data);
        eventDescription[START_TIME_INDEX] = getStartTime(data);
        eventDescription[END_TIME_INDEX] = getEndTime(data);
        eventDescription[MODE_INDEX] = getMode(data);
        return eventDescription;
    }

    /**
     * Gets the event type from the data.
     *
     * @param data a row of data in the data file
     * @return trimmedEventType the event type
     */
    public static String getEventType(String data) {
        String eventType = data.substring(data.indexOf(EVENT_TYPE_DELIMITER_FRONT) + 1,
                data.indexOf(EVENT_TYPE_DELIMITER_BACK));
        String trimmedEventType = eventType.trim();
        String uppercaseEventType = trimmedEventType.toUpperCase();
        return uppercaseEventType;
    }

    /**
     * Checks the validity of the user's given event type in the data file. M stands for Meeting
     * and L stands for Lesson.
     *
     * @param event lesson or meeting
     * @throws InvalidEventTypeException if the value for event does not match "M" or "L"
     */
    public static void checkEventType(String event) throws InvalidEventTypeException {
        if (event.equals(MEETING_SYMBOL) || event.equals(LESSON_SYMBOL)) {
            return;
        }
        throw new InvalidEventTypeException();
    }

    /**
     * Extracts title from a row of data.
     *
     * @param data a row of data in the data file
     * @return trimmedTitle title of an event
     */
    private static String getTitle(String data) {
        String title = data.substring(data.indexOf(HEADINGS[TITLE_INDEX]) + TITLE_CHAR_COUNT);
        String trimmedTitle = title.substring(0, title.indexOf(HEADINGS[DAY_INDEX]));
        trimmedTitle =  trimmedTitle.trim();
        return trimmedTitle;
    }

    /**
     * Extracts day from a row of data.
     *
     * @param data a row of data in the data file
     * @return trimmedDay day of an event
     */
    private static String getDay(String data) {
        String day = data.substring(data.indexOf(HEADINGS[DAY_INDEX]) + DAY_CHAR_COUNT);
        String trimmedDay = day.substring(0, day.indexOf(HEADINGS[START_TIME_INDEX]));
        trimmedDay =  trimmedDay.trim();
        return trimmedDay;
    }

    /**
     * Extracts start time from a row of data.
     *
     * @param data a row of data in the data file
     * @return trimmedStartTime start time of an event
     */
    private static String getStartTime(String data) {
        String startTime = data.substring(data.indexOf(HEADINGS[START_TIME_INDEX]) + START_TIME_CHAR_COUNT);
        String trimmedStartTime = startTime.substring(0, startTime.indexOf(HEADINGS[END_TIME_INDEX]));
        trimmedStartTime =  trimmedStartTime.trim();
        return trimmedStartTime;
    }

    /**
     * Extracts end time from a row of data.
     *
     * @param data a row of data in the data file
     * @return trimmedEndTime end time of an event
     */
    private static String getEndTime(String data) {
        String endTime = data.substring(data.indexOf(HEADINGS[END_TIME_INDEX]) + END_TIME_CHAR_COUNT);
        String trimmedEndTime = endTime.substring(0, endTime.indexOf(HEADINGS[MODE_INDEX]));
        trimmedEndTime =  trimmedEndTime.trim();
        return trimmedEndTime;
    }

    /**
     * Extracts mode from a row of data.
     *
     * @param data a row of data in the data file
     * @return trimmedMode mode of an event
     */
    private static String getMode(String data) {
        String mode = data.substring(data.indexOf(HEADINGS[MODE_INDEX]) + MODE_CHAR_COUNT);
        String trimmedMode = mode.trim();
        return trimmedMode;
    }

    /**
     * Checks if the data contains the required parameters.
     *
     * @param data a row of data in the data file
     * @throws MissingParameterException if there is missing parameter
     */
    private static void checkHeadings(String data) throws MissingParameterException {
        boolean isFound = false;
        for (String str : HEADINGS) {
            isFound = data.contains(str);
            if (!isFound) {
                throw new MissingParameterException();
            }
        }
    }
}
