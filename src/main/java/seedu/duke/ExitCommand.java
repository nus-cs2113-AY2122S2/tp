package seedu.duke;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute() {
        System.out.println("see u again!");
    }
}
