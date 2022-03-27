package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_FREE_INPUT_INVALID;

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
            if (time.length() == 0) {
                duration = 0;
            } else {
                duration = Integer.parseInt(this.time);
            }
        } catch (NumberFormatException nfe) {
            return ERROR_FREE_INPUT_INVALID;
        }
        return masterTimetable.listFree(duration);
    }


}