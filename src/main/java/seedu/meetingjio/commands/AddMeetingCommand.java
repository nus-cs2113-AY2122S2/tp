package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

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
                Meeting meeting = new Meeting(title,day,startTime,endTime,mode);
                if (masterTimetable.checkIfEveryoneFree(masterTimetable,meeting)) {
                        return addMeetingConfirmation(meeting);
                }
                return null;
        }

        private String addMeetingConfirmation(Meeting meeting) {
                return String.format("The following meeting has been added:\n%s",
                        meeting);
        }


}
