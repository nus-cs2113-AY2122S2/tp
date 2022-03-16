package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HiCommand;

public class Parser {
    public static Command parse(String userCommand) {
        switch (userCommand) {
        case ("bye"):
            return new ExitCommand();
        default:
            return new HiCommand();
        }
    }
}
