package seedu.duke.commands;

import seedu.duke.events.Event;
import seedu.duke.events.Lesson;
import seedu.duke.Timetable;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    private final String name;
    private final String title;
    private final String day;
    private final int startTime;
    private final int endTime;
    private final String mode;

    public AddCommand(String name, String title, String day, int startTime, int endTime, String mode) {
        this.name = name;
        this.title = title;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
    }

    @Override
    public void execute(Timetable timetable) {
        Event newEvent = new Lesson(name, title, day, startTime, endTime, mode);
        timetable.add(newEvent);
        printConfirmation(newEvent);
    }

    private void printConfirmation(Event event) {
        System.out.println("The following event has been added to your timetable:");
        System.out.println(event);
    }
}
