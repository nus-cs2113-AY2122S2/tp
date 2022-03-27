package seedu.duke;

public class ViewHousekeeperPerformancesCommand extends Command {

    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        HousekeeperPerformanceList housekeeperPerformanceList = listContainer.getHousekeeperPerformanceList();
        housekeeperPerformanceList.viewPerformances();
    }
}
