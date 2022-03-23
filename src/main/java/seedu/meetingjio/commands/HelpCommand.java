package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.Messages.MESSAGE_HELP;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public String execute(MasterTimetable masterTimetable) {
        return MESSAGE_HELP;
    }
}
