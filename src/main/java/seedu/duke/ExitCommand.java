package seedu.duke;


public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    // Must fix these parameters...
    public void execute() {
        System.out.println("see u again!");
    }

    public void execute(SatisfactionList satisfactionList) {

    }
}
