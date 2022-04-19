package seedu.duke.command;

import seedu.duke.Packages;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ByeCommand() {
        setIsExit(true);
    }

    public void execute(Packages packages) {
        System.out.println("Thank you for using TARBS. See you again!");
    }
}
