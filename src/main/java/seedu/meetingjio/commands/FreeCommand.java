package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;

public class FreeCommand extends Command {

    public static final String COMMAND_WORD = "free";

    private final String time;

    public FreeCommand(String time) {
        this.time = time;
    }

    @Override
    public String execute(MasterTimetable masterTimetable) {
        int duration;
        try {
            duration = Integer.parseInt(this.time);
        } catch (NumberFormatException nfe) {
            duration = 0;
        }
        return masterTimetable.listFree(duration);
    }


}