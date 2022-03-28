package seedu.meetingjio.parser;

import seedu.meetingjio.commands.AddLessonCommand;
import seedu.meetingjio.commands.AddMeetingCommand;
import seedu.meetingjio.commands.Command;
import seedu.meetingjio.exceptions.InvalidEventTypeException;
import seedu.meetingjio.timetables.MasterTimetable;

import java.io.FileNotFoundException;
import java.util.Locale;

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

    private static String missing_key_word;

    public static String prepareLoadLesson(String name, String data, MasterTimetable masterTimetable) {

        String[] eventDescription = splitArguments(data);
        String day = eventDescription[DAY_INDEX].toLowerCase();
        int startTime = Integer.parseInt(eventDescription[START_TIME_INDEX]);
        int endTime = Integer.parseInt(eventDescription[END_TIME_INDEX]);
        String mode = eventDescription[MODE_INDEX].toLowerCase();
        String title = eventDescription[TITLE_INDEX];
        return new AddLessonCommand(name, title, day, startTime, endTime, mode).execute(masterTimetable);
    }

    public static String prepareLoadMeeting(String data, MasterTimetable masterTimetable) {

        String[] eventDescription = splitArguments(data);
        String day = eventDescription[DAY_INDEX].toLowerCase();
        int startTime = Integer.parseInt(eventDescription[START_TIME_INDEX]);
        int endTime = Integer.parseInt(eventDescription[END_TIME_INDEX]);
        String mode = eventDescription[MODE_INDEX].toLowerCase();
        String title = eventDescription[TITLE_INDEX];
        return new AddMeetingCommand(title, day, startTime, endTime, mode).execute(masterTimetable);
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

    private static void checkEventType(String event) throws InvalidEventTypeException {
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


    private static boolean checkHeadings(String data) {
        boolean isFound = false;
        for (String str : HEADINGS) {
            isFound = data.indexOf(str) !=-1? true: false;
            if (isFound == false) {
                missing_key_word = str;
                break;
            }
        }
        return isFound;
    }
}
