package seedu.duke.commands;

import seedu.duke.Timetable;

public class CommandResult extends Command {

    public final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(Timetable timetable) {
        return feedbackToUser;
    }
}
