package seedu.duke.commands;

import seedu.duke.Timetable;
import static seedu.duke.common.Messages.MESSAGE_HELP;

public class CommandResult extends Command {

    public final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(Timetable timetable) {
        return MESSAGE_HELP;
    }
}
