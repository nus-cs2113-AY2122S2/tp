package seedu.duke.commands;

import seedu.duke.Timetable;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(Timetable timetable) {
        if (timetable.size() == 0) {
            return "There are no lessons in your timetable yet!";
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
