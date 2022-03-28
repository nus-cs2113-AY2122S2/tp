package seedu.duke;

import seedu.duke.command.Command;

public class ViewHousekeeperListCommand extends Command {

    /**
     * Printing the list of housekeeper name, age and availability.
     *
     * @param ui The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        ui.printHousekeeperList(housekeeperList);
    }

}