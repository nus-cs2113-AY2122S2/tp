package seedu.meetingjio.parser;

import seedu.meetingjio.commands.AddLessonCommand;
import seedu.meetingjio.commands.AddMeetingCommand;
import seedu.meetingjio.exceptions.InvalidDayException;
import seedu.meetingjio.exceptions.InvalidModeException;
import seedu.meetingjio.exceptions.InvalidTimeException;
import seedu.meetingjio.exceptions.MissingValueException;
import seedu.meetingjio.exceptions.MissingParameterException;
import seedu.meetingjio.exceptions.InvalidEventTypeException;
import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_LOAD_LESSON;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_LOAD_LESSON;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_TIME_LOADING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_DAY_LOADING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_MODE_LOADING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_LOAD_MEETING;

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

    public static String prepareLoadLesson(String name, String data, MasterTimetable masterTimetable) {
        try {
            checkHeadings(data);
            String[] eventDescription = splitArguments(data);
            parserHelperMethods.checkNonNullValues(eventDescription, HEADINGS.length - 1);
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

    public static String prepareLoadMeeting(String data, MasterTimetable masterTimetable) {

        try {
            checkHeadings(data);
            String[] eventDescription = splitArguments(data);
            parserHelperMethods.checkNonNullValues(eventDescription, HEADINGS.length - 1);
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
            throw new RuntimeException(ERROR_MISSING_PARAMETERS_LOAD_LESSON);
        }


    }

    private static String[] splitArguments(String data) {
        String[] eventDescription = new String[5];
        eventDescription[TITLE_INDEX] = getTitle(data);
        eventDescription[DAY_INDEX] = getDay(data);
        eventDescription[START_TIME_INDEX] = getStartTime(data);
        eventDescription[END_TIME_INDEX] = getEndTime(data);
        eventDescription[MODE_INDEX] = getMode(data);
        return eventDescription;
    }

    public static String getEventType(String data) {
        String eventType = data.substring(data.indexOf(EVENT_TYPE_DELIMITER_FRONT) + 1,
                data.indexOf(EVENT_TYPE_DELIMITER_BACK));
        String trimmedEventType = eventType.trim();
        return trimmedEventType;
    }

    public static void checkEventType(String event) throws InvalidEventTypeException {
        if (event.equals("M") || event.equals("L")) {
            return;
        }
        throw new InvalidEventTypeException();
    }

    private static String getTitle(String data) {
        String title = data.substring(data.indexOf(HEADINGS[TITLE_INDEX]) + TITLE_CHAR_COUNT);
        String trimmedTitle = title.substring(0, title.indexOf(HEADINGS[DAY_INDEX]));
        trimmedTitle =  trimmedTitle.trim();
        return trimmedTitle;
    }

    private static String getDay(String data) {
        String day = data.substring(data.indexOf(HEADINGS[DAY_INDEX]) + DAY_CHAR_COUNT);
        String trimmedDay = day.substring(0, day.indexOf(HEADINGS[START_TIME_INDEX]));
        trimmedDay =  trimmedDay.trim();
        return trimmedDay;
    }

    private static String getStartTime(String data) {
        String startTime = data.substring(data.indexOf(HEADINGS[START_TIME_INDEX]) + START_TIME_CHAR_COUNT);
        String trimmedStartTime = startTime.substring(0, startTime.indexOf(HEADINGS[END_TIME_INDEX]));
        trimmedStartTime =  trimmedStartTime.trim();
        return trimmedStartTime;
    }

    private static String getEndTime(String data) {
        String endTime = data.substring(data.indexOf(HEADINGS[END_TIME_INDEX]) + END_TIME_CHAR_COUNT);
        String trimmedEndTime = endTime.substring(0, endTime.indexOf(HEADINGS[MODE_INDEX]));
        trimmedEndTime =  trimmedEndTime.trim();
        return trimmedEndTime;
    }

    private static String getMode(String data) {
        String mode = data.substring(data.indexOf(HEADINGS[MODE_INDEX]) + MODE_CHAR_COUNT);
        String trimmedMode = mode.trim();
        return trimmedMode;
    }


    private static void checkHeadings(String data) throws MissingParameterException {
        boolean isFound = false;
        for (String str : HEADINGS) {
            isFound = data.contains(str);
            if (isFound == false) {
                throw new MissingParameterException();
            }
        }
        return;
    }
}
