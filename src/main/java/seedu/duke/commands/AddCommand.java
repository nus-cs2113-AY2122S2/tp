package seedu.duke.commands;

import seedu.duke.events.Event;
import seedu.duke.events.Lesson;
import seedu.duke.Timetable;

import seedu.duke.exceptions.DuplicateEventException;
import static seedu.duke.common.ErrorMessages.ERROR_DUPLICATE_EVENT;

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
    public String execute(Timetable timetable) {
        try {
            Event newEvent = new Lesson(name, title, day, startTime, endTime, mode);
            timetable.add(newEvent);
            return addConfirmation(newEvent);
        } catch (DuplicateEventException dee) {
            return ERROR_DUPLICATE_EVENT;
        }
    }

    private String addConfirmation(Event event) {
        return "The following event has been added to your timetable:\n"
                + event;
    }
}
