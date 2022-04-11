package seedu.duke.command.housekeepercommands;

import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidNewWeekException;
import seedu.duke.storage.HousekeeperFileManager;

import java.io.IOException;

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
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @param ui            The instance of the Ui class used for printing additional messages when a command is
     *                      executed.
     * @throws IOException Write to file has failed.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.resetAvailability();
        ui.printHousekeeperListReset(housekeeperList);
    }

    /**
     * After all housekeeper's availability has been reset, this method will update the file and resets
     * every housekeeper availability.
     *
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @throws IOException Failed to write to file.
     */
    public void writeHousekeeperToFile(ListContainer listContainer) throws IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        HousekeeperFileManager housekeeperFileManager = new HousekeeperFileManager();
        housekeeperFileManager.save(housekeeperList);
    }
}
