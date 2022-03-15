package seedu.duke.commands;

import seedu.duke.Timetable;
import static seedu.duke.common.Messages.MESSAGE_HELP;

public class HelpCommand extends Command {
    @Override
    public String execute(Timetable timetable) {
        return MESSAGE_HELP;
    }
    // helper messages
}
