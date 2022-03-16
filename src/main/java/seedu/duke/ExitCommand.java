package seedu.duke;


public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        System.out.println("see u again!");
    }
}
