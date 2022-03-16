package seedu.duke.commands;

import seedu.duke.Timetable;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    @Override
    public String execute(Timetable timetable) {
        timetable.clear();
        printClearConfirmation();
        return "";
    }

    public static void printClearConfirmation() {
        System.out.println("Your whole timetable has been cleared");
    }

}
