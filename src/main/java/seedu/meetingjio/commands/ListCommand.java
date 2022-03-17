package seedu.meetingjio.commands;

import seedu.meetingjio.Timetable;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_LIST;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * List out all entries in the user's timetable.
     * If there are no lessons found, notify user accordingly.
     *
     * @param timetable User's timetable containing a list of lessons
     * @return String containing all of the user's lessons
     */
    @Override
    public String execute(Timetable timetable) {
        if (timetable.size() == 0) {
            return ERROR_EMPTY_LIST;
        }
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
