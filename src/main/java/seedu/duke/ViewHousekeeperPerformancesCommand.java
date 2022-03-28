package seedu.duke;
import seedu.duke.command.Command;

/**
 * Represents a command to view the list of housekeeper performances.
 * The user command is "view housekeeper performances."
 */

public class ViewHousekeeperPerformancesCommand extends Command {

    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        HousekeeperPerformanceList housekeeperPerformanceList = listContainer.getHousekeeperPerformanceList();
        housekeeperPerformanceList.viewPerformances();
    }
}
