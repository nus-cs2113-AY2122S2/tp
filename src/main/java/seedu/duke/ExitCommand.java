package seedu.duke;


public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(HousekeeperList housekeeperList, HousekeeperPerformanceList housekeeperPerformanceList,
                        SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        System.out.println("see u again!");
    }
}
