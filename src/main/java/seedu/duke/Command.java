package seedu.duke;

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public abstract void execute(SatisfactionList satisfactionList, RoomList roomList, ItemList listOfItems, Ui ui)
            throws HotelLiteManagerException, WrongCommandException;


    public boolean isExit() {
        return this.isExit;
    }


}
