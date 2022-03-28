package seedu.duke;

/**
 * Reset availability of each housekeeper when needed such as a start of a new week.
 */
public class ResetAvailabilityCommand extends Command {

    /**
     * Call method to reset every housekeeper availability to null and print out the new list for verification.
     *
     * @param listContainer List of information.
     * @param ui The instance of the Ui class (used for printing additional messages when a command is executed.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.resetAvailability();
        ui.printHousekeeperListReset(housekeeperList);
    }
}
