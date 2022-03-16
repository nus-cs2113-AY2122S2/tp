package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;

public class Parser {
    public static Command parse(String userCommand) {
        switch (userCommand) {
        default:
            return new ExitCommand();
        }
    }
}
