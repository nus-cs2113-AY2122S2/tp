//@@author ibrahimisramos

package seedu.meetingjio.commands;

import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.timetables.MasterTimetable;

import java.util.logging.Level;

import static seedu.meetingjio.common.ErrorMessages.ERROR_NO_USER_TO_ADD_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EXCEPTION_NOT_HANDLED;

import static seedu.meetingjio.parser.Parser.logger;

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

    /**
     * Execute AddMeeting command using the timetable provided.
     *
     * @param masterTimetable MasterTimetable
     *
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        // check all timetables, see if start or end time of each event/lesson is equal to each other
        if (masterTimetable.getSize() == 0) {
            return ERROR_NO_USER_TO_ADD_MEETING;
        }
        try {
            Meeting meeting = new Meeting(title, day, startTime, endTime, mode);
            if (masterTimetable.isPreExistingMeeting(meeting)) {
                return ERROR_DUPLICATE_MEETING;
            } else if (masterTimetable.isMeetingThatClashes(meeting)) {
                return ERROR_OVERLAPPING_MEETING;
            } else {
                return masterTimetable.addMeetingToEveryoneTimetable(meeting);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Unhandled Exception : " +  e.getMessage());
            return ERROR_EXCEPTION_NOT_HANDLED;
        }

    }

}
