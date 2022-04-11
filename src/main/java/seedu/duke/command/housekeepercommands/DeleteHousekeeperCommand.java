package seedu.duke.command.housekeepercommands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.command.Command;
import seedu.duke.ListContainer;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.Ui;
import seedu.duke.exceptions.EmptyNameException;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.AssignmentMap;
import seedu.duke.storage.HousekeeperFileManager;

/**
 * Represents a command to delete an existing housekeeper within the housekeeper list.
 * A DeleteHousekeeperCommand object consists of the name of the housekeeper to be deleted.
 */
public class DeleteHousekeeperCommand extends Command {
    private String name;
    private static final String DELETE_PROFILE_COMMAND = "delete housekeeper";
    private static Logger logger = Logger.getLogger("housekeeperDeletionLogger");

    public DeleteHousekeeperCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            throw new EmptyNameException();
        }
        String inputWithNoSpace = commandStringWithoutCommand.trim();
        if (inputWithNoSpace.contains(DELETE_PROFILE_COMMAND)) {
            logger.log(Level.WARNING, "Repeated delete housekeeper command given.");
            throw new DuplicateCommandException();
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
     * @param ui            The instance of the Ui class used for printing additional messages when a command is executed.
     * @throws HotelLiteManagerException User given is not in housekeeper list.
     * @throws IOException               Write to file has failed.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.removeHousekeeperInList(name);
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        assignmentMap.removeAssignment(name);
        assignmentMap.save();
        ui.printNotifiedDeletionOfHousekeeper(housekeeperList, name);
    }

    /**
     * After deletion of housekeeper, the housekeeper file has to be updated. This method will update
     * by removing the housekeeper from the records.
     *
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @throws IOException Write to file has failed.
     */
    public void writeHousekeeperToFile(ListContainer listContainer) throws IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        HousekeeperFileManager housekeeperFileManager = new HousekeeperFileManager();
        housekeeperFileManager.save(housekeeperList);
    }
}
