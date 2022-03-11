package seedu.duke.commands;

import seedu.duke.Timetable;

public abstract class Command {

    public abstract void execute(Timetable timetable);
}
