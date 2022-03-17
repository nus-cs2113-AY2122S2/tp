package seedu.meetingjio.commands;

import seedu.meetingjio.Timetable;
import static seedu.meetingjio.common.Messages.MESSAGE_HELP;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public String execute(Timetable timetable) {
        return MESSAGE_HELP;
    }
}
