package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_LIST;
import static seedu.meetingjio.timetables.MasterTimetable.timetables;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private final String name;

    public ListCommand(String name) {
        this.name = name;
    }

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
        String user = this.name;
        if (user.equals("all")) {
            return listAll();
        }
        return listUser(user);
    }

    private String listAll() {
        String str = "";
        for (Timetable timetable : timetables) {
            String user = timetable.getName();
            str += user;
            str += '\n';
            str += listUser(user);
            str += '\n';
        }
        return str.substring(0, str.length() - 1); //remove last newline character
    }

    private String listUser(String user) {
        Timetable timetable = null;
        for (Timetable t : timetables) {
            if (user.equals(t.getName())) {
                timetable = t;
            }
        }
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
