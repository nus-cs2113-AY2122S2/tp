package seedu.duke;


public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute() {
        System.out.println("see u again!");
    }

    public void execute(RoomList roomList){

    }
}
