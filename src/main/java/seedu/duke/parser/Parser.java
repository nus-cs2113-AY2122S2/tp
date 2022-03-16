package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ByeCommand;

public class Parser {
    public static Command parse(String userCommand) {
        switch (userCommand) {
        case ("bye"):
            return new ByeCommand();
        default:
            return new ByeCommand();
        }
    }
}
