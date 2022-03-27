package seedu.meetingjio.commands;

import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_MEETING;

public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "add_meeting";

    private final String title;
    private final String day;
    private final int startTime;
    private final int endTime;
    private final String mode;

    public AddMeetingCommand(String title, String day, int startTime, int endTime, String mode) {
        this.title = title;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
    }

    @Override
    public String execute(MasterTimetable masterTimetable) {
        // check all timetables, see if start or end time of each event/lesson is equal to each other
        try {
            Meeting meeting = new Meeting(title, day, startTime, endTime, mode);
            if (masterTimetable.checkIfClash(meeting)) {
                return ERROR_OVERLAPPING_MEETING;
            } else if (masterTimetable.checkIfMeetingExistsAlready(meeting)) {
                return ERROR_DUPLICATE_MEETING;
            } else {
                return masterTimetable.addMeetingToEveryoneTimetable(meeting);
            }
        } catch (Exception e) {
            return "ERROR DETECTED";
        }

    }

    public static String addMeetingConfirmation(Meeting meeting) {
        return String.format("The following meeting has been added to everyone's timetable:\n%s",
                meeting);
    }

}
