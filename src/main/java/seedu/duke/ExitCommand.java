package seedu.duke;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    // Must fix these parameters...
    public void execute(SatisfactionList satisfactionList, Ui ui) {
        System.out.println("see u again!");
    }
}
