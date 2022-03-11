package seedu.duke.commands;

import seedu.duke.Timetable;

public abstract class Command {

    public abstract String execute(Timetable timetable);
}
