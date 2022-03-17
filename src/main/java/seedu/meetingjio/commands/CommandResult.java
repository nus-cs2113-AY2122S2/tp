package seedu.meetingjio.commands;

import seedu.meetingjio.Timetable;

/**
 * Represents the result of a command execution.
 */
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
