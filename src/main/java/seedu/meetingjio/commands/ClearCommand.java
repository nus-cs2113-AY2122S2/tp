package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_NON_EMPTY_LIST;

//@@author ibrahimisramos
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    /**
     * Execute clear command using the timetable provided.
     *
     * @param masterTimetable
     *
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        Timetable timetable = masterTimetable.getByIndex(0); // changes needed
        int numEntries = timetable.size();
        for (int i = 0; i < numEntries; i++) {
            timetable.remove(0);
        }
        assert (timetable.size() == 0) : ERROR_NON_EMPTY_LIST;
        return printClearConfirmation();
    }

    public static String printClearConfirmation() {
        return "Your whole timetable has been cleared";
    }

}
