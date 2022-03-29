package seedu.duke.command.housekeepercommands;

import seedu.duke.HousekeeperList;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.InvalidNewWeekException;

/**
 * Reset availability of each housekeeper when needed such as a start of a new week.
 */
public class ResetAvailabilityCommand extends Command {
    private static final String RESET_AVAILABILITY = "is a new week";

    public ResetAvailabilityCommand(String input) throws HotelLiteManagerException {
        if (!input.equals(RESET_AVAILABILITY)) {
            throw new InvalidNewWeekException();
        }
    }

    /**
     * Call method to reset every housekeeper availability to null and print out the new list for verification.
     *
     * @param listContainer List of information.
     * @param ui            The instance of the Ui class (used for printing additional messages when a command is executed.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.resetAvailability();
        ui.printHousekeeperListReset(housekeeperList);
    }
}
