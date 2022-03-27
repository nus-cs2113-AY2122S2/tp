package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_FREE_INPUT_INVALID;
import static seedu.meetingjio.common.ErrorMessages.ERROR_NO_FREE_TIMESLOT;

public class FreeCommand extends Command {

    public static final int NUM_DAYS = 7;
    public static final int NUM_MINS = 960;
    public static final int OFFSET = 480;
    public static final int HOUR_PARAMETER_IN_24_HOURS = 100;
    public static final int MINS_IN_1_HOUR = 60;
    public static final int HOUR_IN_24_HOUR = 100;
    public static final int BUSY = 1;
    public static final int FREE = 0;


    public static final String COMMAND_WORD = "free";

    private final String time;

    public FreeCommand(String time) {
        this.time = time;
    }

    /**
     * The Free Command searches all timetables in the Master Timetable, and finds common timeslots where everyone is
     * free.
     * The Free Command has an attribute 'time', which is extracted from the user input. Time is an integer that
     * indicates the user's desired duration (in hours) for a meeting. Thus, if a user inputs time 2, the algorithm
     * searches for free timeslots that are at least 2 hours long.
     *
     * @param masterTimetable The collection of everyone's timetable
     * @return freeTimeSlots A string containing the common timeslots where everyone is free
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        int duration;
        int[][] busySlots;
        try {
            if (time.length() == 0) {
                duration = 0;
            } else {
                duration = Integer.parseInt(this.time);
            }
        } catch (NumberFormatException nfe) {
            return ERROR_FREE_INPUT_INVALID;
        }
        busySlots = masterTimetable.listBusy();
        String freeTimeSlots = listFree(busySlots, duration);
        return freeTimeSlots;
    }

    /**
     * Given the finalised busySlots array and the minimum duration, find the timeslots where everyone is free and the
     * duration meets the minimum duration.
     * Each row in busySlots contains 960 0s and 1s, where free timeslots are represented by consecutive 0s.
     * 2 strings are initialised: freeSlotsString to store free slots and newEntry to store each new entry.
     * An integer count and a boolean isStart is also initialised as 0 and true respectively.
     * For each day, iterating from left to right, when a 0 (FREE) is encountered, the respective day and time is added
     * to newEntry, and count will be incremented.
     * For each subsequent 0 (FREE), count will keep incrementing.
     * When a 1 (BUSY) is encountered, the count is checked to determine if the free timeslot exceeds the minimum
     * duration. If it does, the time is added to newEntry, and newEntry is added to freeSlotsString.
     * Subsequently, newEntry and count will reset until the next 0 is encountered, and the process repeats itself.
     *
     * @param busySlots 7 x 960 array representing the timeframe from 0800 to 2359 for each day. 1 indicates BUSY and 0
     *                  indicates FREE
     * @param duration Minimum time required for the free time slot
     * @return truncateString(freeSlotsString) freeSlotsString but truncated without the last newline character
     */
    public static String listFree(int[][] busySlots, int duration) {
        String freeSlotsString = "";
        String newEntry = "";
        boolean isStart = true;
        int count = 0;
        for (int i = 0; i < NUM_DAYS; i++) {
            for (int j = 0; j < NUM_MINS; j++) {
                if (busySlots[i][j] == FREE) {
                    if (isStart) {
                        newEntry += convertFreeArrayIndexToDay(i + 1);
                        newEntry += " ";
                        newEntry += convertFreeArrayIndexToTime(j);
                        isStart = false;
                    }
                    count++;
                }
                if (busySlots[i][j] == BUSY && !isStart) {
                    if (count >= duration * MINS_IN_1_HOUR) {
                        newEntry += " ";
                        newEntry += convertFreeArrayIndexToTime(j);
                        newEntry += "\n";
                        freeSlotsString += newEntry;
                    }
                    count = 0;
                    newEntry = "";
                    isStart = true;
                }
            }
        }
        return truncateString(freeSlotsString);
    }

    /**
     * Remove the last newline character from the string. If the string is empty, return an error message informing the
     * user that there is no free time slot that matches the given requirements.
     *
     * @param freeSlotsString String containing the common timeslots where everyone is free
     * @return truncatedFreeSlotsString freeSlotsString but truncated without the last newline character
     */
    private static String truncateString(String freeSlotsString) {
        if (freeSlotsString.length() == 0) {
            return ERROR_NO_FREE_TIMESLOT;
        }
        String truncatedFreeSlotsString = freeSlotsString.substring(0, freeSlotsString.length() - 1);
        return truncatedFreeSlotsString;
    }

    /**
     * Converts an integer (1-7) into its corresponding day, with 1 as Monday and 7 as Sunday.
     *
     * @param numericDay Integer to be converted into a day
     * @return day String indicating the actual day
     */
    private static String convertFreeArrayIndexToDay(int numericDay) {
        String day;
        switch (numericDay) {
        case 1:
            day = "Monday";
            break;
        case 2:
            day = "Tuesday";
            break;
        case 3:
            day = "Wednesday";
            break;
        case 4:
            day = "Thursday";
            break;
        case 5:
            day = "Friday";
            break;
        case 6:
            day = "Saturday";
            break;
        case 7:
            day = "Sunday";
            break;
        default:
            day = "";
            break;
        }
        return day;
    }

    /**
     * Convert the number of minutes starting from 0800 to 24-hour format.
     *
     * @param mins The number of minutes starting from 0800
     * @return timeIn24Hour The corresponding time in 24-hour format
     */
    private static String convertFreeArrayIndexToTime(int mins) {
        mins += OFFSET;
        int hours = mins / MINS_IN_1_HOUR;
        int minutes = mins % MINS_IN_1_HOUR;
        int timeInt = hours * HOUR_IN_24_HOUR + minutes;
        String timeIn24Hour = String.format("%04d", timeInt);
        return timeIn24Hour;
    }

    /**
     * This helper method takes in a time in 24-hour format, and convert it to the number of minutes starting from 0800.
     *
     * @param time Time to be converted
     * @return timeInMinutes The number of minutes converted from time
     */
    public static int convertTimeToFreeArrayIndex(int time) {
        int hours = time / HOUR_PARAMETER_IN_24_HOURS;
        int minutes = time % HOUR_PARAMETER_IN_24_HOURS;
        int timeInMinutes = hours * MINS_IN_1_HOUR + minutes;
        int index = timeInMinutes - OFFSET;
        return index;
    }

}