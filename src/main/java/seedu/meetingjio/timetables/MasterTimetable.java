package seedu.meetingjio.timetables;

import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.commands.ListCommand;

import static seedu.meetingjio.common.ErrorMessages.ERROR_NO_FREE_TIMESLOT;

import java.util.ArrayList;

public class MasterTimetable {

    private final ArrayList<Timetable> timetables;

    public static final int NUM_DAYS = 7;
    public static final int NUM_MINS = 960;
    public static final int OFFSET = 480;
    public static final int MINS_IN_1_HOUR = 60;
    public static final int HOUR_IN_24_HOUR = 100;
    public static final int BUSY = 1;
    public static final int FREE = 0;

    public MasterTimetable() {
        this.timetables = new ArrayList<>();
    }

    public Timetable getByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (name.equalsIgnoreCase(timetable.getName())) {
                return timetable;
            }
        }
        throw new TimetableNotFoundException();
    }

    public void removeByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (name.equalsIgnoreCase(timetable.getName())) {
                timetables.remove(timetable);
            }
        }
        throw new TimetableNotFoundException();
    }

    public Timetable getByIndex(int index) {
        return timetables.get(index);
    }

    public void removeByIndex(int index) {
        timetables.remove(index);
    }

    public void add(Timetable timetable) {
        timetables.add(timetable);
    }

    /**
     * This method iterates through every timetable in the Master Timetable.
     * For each timetable, the name of the person that the timetable belongs to, and the contents of the timetable
     * itself, will be appended to the string.
     * The contents of each timetable is obtained by calling the listUser method from ListCommand
     * Once all timetables have been iterated through, the string is returned
     *
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @return str A string containing the labelled timetables of everyone
     */
    public String collateAll(MasterTimetable masterTimetable) {
        String str = "";
        for (Timetable timetable : timetables) {
            String user = timetable.getName();
            str += user;
            str += '\n';
            str += ListCommand.listUser(user, masterTimetable);
            str += '\n';
        }
        return str;
    }

    public int getSize() {
        return timetables.size();
    }

    public String listFree(int duration) {
        int[][] busySlots = new int[NUM_DAYS][NUM_MINS];
        for (int i = 0; i < NUM_DAYS; i++) {
            busySlots[i][NUM_MINS - 1] = BUSY;
        }
        for (Timetable timetable : timetables) {
            timetable.populateBusySlots(busySlots);
        }
        return showOutput(busySlots, duration);
    }

    private String showOutput(int[][] busySlots, int duration) {
        String freeSlotsString = "";
        String newEntry = "";
        boolean isStart = true;
        int count = 0;
        for (int i = 0; i < NUM_DAYS; i++) {
            for (int j = 0; j < NUM_MINS; j++) {
                if (busySlots[i][j] == FREE) {
                    if (isStart) {
                        newEntry += convertDayIntToString(i + 1);
                        newEntry += " ";
                        newEntry += convertMinsToTime(j);
                        isStart = false;
                    }
                    count++;
                }
                if (busySlots[i][j] == BUSY && !isStart) {
                    if (count >= duration * MINS_IN_1_HOUR) {
                        newEntry += " ";
                        newEntry += convertMinsToTime(j);
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

    private String truncateString(String freeSlotsString) {
        if (freeSlotsString.length() == 0) {
            return ERROR_NO_FREE_TIMESLOT;
        }
        String filteredFreeSlotsString = freeSlotsString.substring(0, freeSlotsString.length() - 1);
        return filteredFreeSlotsString;
    }

    private String convertDayIntToString(int numericDay) {
        switch (numericDay) {
        case 1:
            return "Monday";
        case 2:
            return "Tuesday";
        case 3:
            return "Wednesday";
        case 4:
            return "Thursday";
        case 5:
            return "Friday";
        case 6:
            return "Saturday";
        case 7:
            return "Sunday";
        default:
            return "";
        }
    }

    private String convertMinsToTime(int mins) {
        mins += OFFSET;
        int hours = mins / MINS_IN_1_HOUR;
        int minutes = mins % MINS_IN_1_HOUR;
        int timeInt = hours * HOUR_IN_24_HOUR + minutes;
        String timeIn24Hour = String.format("%04d", timeInt);
        return timeIn24Hour;
    }

}
