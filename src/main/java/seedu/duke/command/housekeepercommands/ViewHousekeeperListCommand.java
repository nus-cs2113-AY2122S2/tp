package seedu.duke.command.housekeepercommands;

import seedu.duke.ListContainer;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.Ui;
import seedu.duke.exceptions.InvalidViewHousekeeperException;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.command.Command;

public class ViewHousekeeperListCommand extends Command {
    private static final String VIEW_HOUSEKEEPER_COMMAND = "view recorded housekeepers";

    public ViewHousekeeperListCommand(String input) throws HotelLiteManagerException {
        if (!input.equals(VIEW_HOUSEKEEPER_COMMAND)) {
            throw new InvalidViewHousekeeperException();
        }
    }

    /**
     * Printing the list of housekeeper name, age and availability.
     *
     * @param ui The user interface for this execution method.
     * @return
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        ui.printHousekeeperList(housekeeperList);
    }

}