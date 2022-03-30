package seedu.duke.command.housekeepercommands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.command.Command;
import seedu.duke.ListContainer;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.Ui;
import seedu.duke.UserDoesNotExistException;
import seedu.duke.EmptyNameException;
import seedu.duke.HousekeeperList;
import seedu.duke.AssignmentMap;

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
        housekeeperList.removeHousekeeperInList(name);
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        assignmentMap.removeAssignment(name);
        ui.printNotedLine();
        ui.printMessage("Deleted " + name + " from the list of profile");
        ui.printMessage("Take note! Total pax of housekeeper:  " + housekeeperList.getTotalHousekeeper());
        ui.printBottomLine();
    }
}
