package seedu.duke;

/**
 * Abstract class that generalizes all Commands.
 * Contains an abstract "execute" method and keeps track of
 * whether the program should exit.
 */

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    /**
     * Abstract execute command that all Command classes should
     * override based on their specific behavior.
     * @param housekeeperList The list of Housekeeper objects to execute the command on.
     * @param satisfactionList The list of Satisfaction objects to execute the command on.
     * @param roomList The list of Room objects to execute the command on.
     * @param listOfItems The list of Item objects to execute the command on.
     * @param ui The instance of the Ui class (used for printing additional messages when a command is executed.
     * @throws HotelLiteManagerException If there is any error with user input.
     * @throws WrongCommandException If the command is not recognized.
     */
    public abstract void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList,
                                 ItemList listOfItems, Ui ui) throws HotelLiteManagerException, WrongCommandException;

    public boolean isExit() {
        return this.isExit;
    }


}
