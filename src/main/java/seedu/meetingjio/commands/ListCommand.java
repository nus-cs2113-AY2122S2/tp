package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_LIST;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * List out all entries in the user's timetable.
     * If there are no lessons found, notify user accordingly.
     *
     *
     * @param masterTimetable MasterTimetable
     * @return String containing all of the user's lessons
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        Timetable timetable = masterTimetable.getByIndex(0); // changes needed
        if (timetable.size() == 0) {
            return ERROR_EMPTY_LIST;
        }
        assert timetable.size() > 0 : ERROR_EMPTY_LIST;
        String str = "";
        for (int i = 0; i < timetable.size(); i++) {
            int listIndex = i + 1;
            str += listIndex + "." + timetable.get(i);
            if (i != timetable.size() - 1) {
                str += '\n';
            }
        }
        return str;
    }
}
