//@@author lihao-InfoSec

package seedu.meetingjio.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import seedu.meetingjio.exceptions.IncorrectIndexException;
import seedu.meetingjio.exceptions.InvalidEventTypeException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.parser.ParserLocalData;

import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_EVENT_TYPE_LOADING;

import static seedu.meetingjio.common.Messages.MESSAGE_DIVIDER;
import static seedu.meetingjio.common.Messages.SAVE_DATA_MESSAGE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_DATA_SAVE_FAILED;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_ROW;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INCORRECT_INDEX;
import static seedu.meetingjio.parser.ParserLocalData.checkEventType;
import static seedu.meetingjio.parser.ParserLocalData.MEETING_SYMBOL;
import static seedu.meetingjio.parser.ParserLocalData.LESSON_SYMBOL;


/**
 * Represents the file used to store timetable data.
 */
public class StorageFile {

    public static final String DATA_FILE_PATH = "MeetingJio.txt";

    private static final String NO_TIMETABLE = "There are no events in your timetable yet!";

    /**
     * Saves all the timetables in a readable format as MeetingJio.txt.
     * If IOException is caught, the save process has failed.
     * It will print a corresponding error message to the exception caught.
     *
     * @param masterTimetable Array of timetables
     */
    public static void saveData(MasterTimetable masterTimetable) {
        System.out.println(MESSAGE_DIVIDER);
        try {
            File dataFile = new File(DATA_FILE_PATH);
            dataFile.createNewFile();
            FileOutputStream outFile = new FileOutputStream(dataFile, false);
            FileWriter dataWriter = new FileWriter(dataFile);
            String str = masterTimetable.collateAll(masterTimetable, 0);
            String truncatedString = "";
            if (str != "") {
                truncatedString = str.substring(0, str.length() - 1);
            }
            dataWriter.write(truncatedString);
            dataWriter.close();
            System.out.println(SAVE_DATA_MESSAGE);
        } catch (IOException ioe) {
            System.out.println(ERROR_DATA_SAVE_FAILED);
        }
    }

    /**
     * Loads the data stored in the MeetingJio.txt into the masterTimetable.
     *
     * @param masterTimetable Array of timetables
     * @throws IncorrectIndexException If the sequence of the index is incorrect.
     * @throws RuntimeException If InvalidEventTypeException or IncorrectIndexException is caught
     */
    public static void loadData(MasterTimetable masterTimetable) {
        try {
            File dataFile = new File(DATA_FILE_PATH);
            Scanner dataReader = new Scanner(dataFile);
            int listNum = 0;
            String name = "";
            String eventType;
            boolean hasMeeting = false;
            List<String> meetingList = new ArrayList<>();
            int personCount = 0;
            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();
                if (isName(data)) {
                    name = data;
                    listNum = 1;
                    personCount++;
                    ParserLocalData.prepareLoadName(name, masterTimetable);
                } else {
                    if (!isNoTimetableMessage(data)) {
                        if (isExpectedIndex(data, listNum)) {
                            eventType = ParserLocalData.getEventType(data);
                            checkEventType(eventType);
                            if (eventType.equals(LESSON_SYMBOL)) {
                                ParserLocalData.prepareLoadLesson(name, data, masterTimetable);
                            } else if (eventType.equals(MEETING_SYMBOL) && personCount == 1) {
                                meetingList.add(data);
                                hasMeeting = true;
                            }
                            listNum++;
                        } else {
                            throw new IncorrectIndexException();
                        }
                    }
                }
            }
            if (hasMeeting) {
                for (String str : meetingList) {
                    ParserLocalData.prepareLoadMeeting(str, masterTimetable);
                }
            }
        } catch (FileNotFoundException ffe) {
            System.out.println("");
        } catch (InvalidEventTypeException iete) {
            throw new RuntimeException(ERROR_INVALID_EVENT_TYPE_LOADING);
        } catch (IncorrectIndexException iie) {
            throw new RuntimeException(ERROR_INCORRECT_INDEX);
        }
    }

    /**
     * Checks if the index is in the correct sequence.
     *
     * @param data a row of data in the data file
     * @param expectedIndex the expected number
     * @return true if the index is in correct sequence and false otherwise
     */
    private static boolean isExpectedIndex(String data, int expectedIndex) {
        String index = getFirstChar(data);
        int i = Integer.parseInt(index);
        if (i == expectedIndex) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the row of the data is a name or not.
     *
     * @param data a row of data in the data file
     * @return true if the first character is an alphabet and false otherwise
     */
    private static boolean isName(String data) {
        String firstChar = getFirstChar(data);
        if (firstChar == null || isNoTimetableMessage(data) == true) {
            return false;
        }
        try {
            int i = Integer.parseInt(firstChar);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a row data is a no timetable message.
     *
     * @param data a row of data in the data file
     * @return true if it is the no timetable message and false otherwise
     */
    private static boolean isNoTimetableMessage(String data) {
        if (NO_TIMETABLE.equalsIgnoreCase(data)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the first character of a row data.
     *
     * @param data a row of data in the data file
     * @return firstChar the first character in the row of the data
     * @throws StringIndexOutOfBoundsException if the value of data is empty
     */
    private static String getFirstChar(String data) throws StringIndexOutOfBoundsException {
        try {
            data = data.trim();
            String firstChar = data.substring(0, 1);
            return firstChar;
        } catch (StringIndexOutOfBoundsException sibe) {
            throw new RuntimeException(ERROR_EMPTY_ROW);
        }
    }

}
