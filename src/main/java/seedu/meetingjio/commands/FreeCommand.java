package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_FREE_INPUT_INVALID;

public class FreeCommand extends Command {

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
        String freeTimeSlots;
        try {
            if (time.length() == 0) {
                duration = 0;
            } else {
                duration = Integer.parseInt(this.time);
            }
            freeTimeSlots = masterTimetable.listFree(duration);
        } catch (NumberFormatException nfe) {
            return ERROR_FREE_INPUT_INVALID;
        }
        return freeTimeSlots;
    }


}