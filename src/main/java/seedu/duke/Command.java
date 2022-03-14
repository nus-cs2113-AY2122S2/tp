package seedu.duke;

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public abstract void execute() throws HotelLiteManagerException;

    public abstract void execute(RoomList list) throws HotelLiteManagerException;

    public boolean isExit() {
        return this.isExit;
    }
}
