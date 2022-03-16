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
        int numEntries = timetable.size();
        for (int i = 0; i < numEntries; i++) {
            timetable.remove(0);
        }
        printClearConfirmation();
        return "";
    }

    public static void printClearConfirmation() {
        System.out.println("Your whole timetable has been cleared");
    }

}
