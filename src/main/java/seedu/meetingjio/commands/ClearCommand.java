//@@author ibrahimisramos
package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_NON_EMPTY_LIST;
import static seedu.meetingjio.timetables.MasterTimetable.timetables;

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
        int num_Timetables = timetables.size();
        for (int i = 0; i < num_Timetables; i++) {
            Timetable timetable = masterTimetable.getByIndex(i);
            clearTimetable(timetable);
        }
        return printClearConfirmation();
    }

    private void clearTimetable(Timetable timetable){
        int numEntries = timetable.size();
        for (int i = 0; i < numEntries; i++) {
            timetable.remove(0);
        }
        assert (timetable.size() == 0) : ERROR_NON_EMPTY_LIST;
    }

    public static String printClearConfirmation() {
        return "Your whole timetable has been cleared";
    }

}
