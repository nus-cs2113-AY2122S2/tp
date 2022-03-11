package seedu.duke.commands;

import seedu.duke.Timetable;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(Timetable timetable) {
        for (int i = 0; i < timetable.size(); i++) {
            System.out.println(i + 1 + "." + timetable.get(i));
        }
    }
}
