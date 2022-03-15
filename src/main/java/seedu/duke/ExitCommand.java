package seedu.duke;


public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(RoomList roomList, ItemList listOfItems, Ui ui) throws WrongCommandException {
        System.out.println("see u again!");
    }
}
