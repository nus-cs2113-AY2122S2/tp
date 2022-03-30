package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.HotelLiteManagerException;


public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        System.out.println("see u again!");
    }
}
