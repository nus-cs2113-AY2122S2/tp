package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;
/**
 * Represents an executable command.
 */

public abstract class Command {

    public abstract String execute(MasterTimetable masterTimetable);

}
