package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Lesson;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.timetables.MasterTimetable;

import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;

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

    /**
     * Execute Add command using the timetable provided.
     *
     * @param masterTimetable MasterTimetable
     *
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        Timetable timetable;
        try {
            timetable  = masterTimetable.getByName(name);
        } catch (TimetableNotFoundException tnfe) {
            timetable = new Timetable(name);
            masterTimetable.add(timetable);
        }
        try {
            Event newEvent = new Lesson(title, day, startTime, endTime, mode);
            timetable.add(newEvent);
            return addConfirmation(newEvent, name);
        } catch (DuplicateEventException dee) {
            return ERROR_DUPLICATE_EVENT;
        } catch (OverlappingEventException oee) {
            return ERROR_OVERLAPPING_EVENT;
        }
    }

    /**
     * Inform user that add has happened.
     *
     * @param event Event that inform user that said event has been event
     *
     */
    private String addConfirmation(Event event, String name) {
        return String.format("The following event has been added to %s's timetable:\n%s",
                name, event);
    }
}
