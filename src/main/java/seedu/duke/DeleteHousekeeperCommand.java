package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteHousekeeperCommand extends Command {
    private String name;
    private static Logger logger = Logger.getLogger("housekeeperDeletionLogger");

    public DeleteHousekeeperCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            throw new EmptyNameException();
        }
        name = commandStringWithoutCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Remove Housekeeper that is not active from the list and print out the total strength of housekeeper in the list.
     *
     * @param listContainer List of information.
     * @param ui The instance of the Ui class (used for printing additional messages when a command is executed.
     * @throws UserDoesNotExistException User given is not in housekeeper list.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws UserDoesNotExistException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        boolean isRecorded = housekeeperList.hasNameAdded(name);
        if (isRecorded) {
            int housekeeperToRemoveIndex = housekeeperList.getHousekeeperRemove(name);
            housekeeperList.removeHousekeeper(housekeeperToRemoveIndex);
            ui.printMessage("Deleted " + name + " from the list of profile");
            ui.printMessage("Take note! Total pax of housekeeper:  " + housekeeperList.getTotalHousekeeper());
        } else {
            logger.log(Level.WARNING, "Housekeeper to be deleted was not in the list.");
            throw new UserDoesNotExistException();
        }
    }
}
