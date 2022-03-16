package seedu.duke.commands;

import seedu.duke.Timetable;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    /**
     * Execute clear command using the timetable provided.
     *
     * @param timetable Timetable object initialised by programme
     *
     */
    @Override
    public String execute(Timetable timetable) {
        for (int i = 0; i < timetable.size(); i++) {
            timetable.remove(i);
        }
        printClearConfirmation();
        return "";
    }

    public static void printClearConfirmation() {
        System.out.println("Your whole timetable has been cleared");
    }

}
