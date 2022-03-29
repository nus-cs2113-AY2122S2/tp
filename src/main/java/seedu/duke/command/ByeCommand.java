package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservations;

public class ByeCommand extends Command {
    public ByeCommand() {
        setIsExit(true);
    }

    public void execute(Packages packages) {
        System.out.println("Thank you for using TARBS. See you again!");
    }
}
