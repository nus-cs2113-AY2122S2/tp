package seedu.duke;


public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(Ui ui) {
        System.out.println("see u again!");
    }
}
