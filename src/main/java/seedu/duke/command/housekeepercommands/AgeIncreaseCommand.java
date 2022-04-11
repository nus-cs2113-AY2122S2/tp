package seedu.duke.command.housekeepercommands;

import java.io.IOException;
import java.util.ArrayList;

import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.command.Command;
import seedu.duke.exceptions.InvalidNewYearException;
import seedu.duke.storage.HousekeeperFileManager;

/**
 * Represents a command to increase every housekeeper age by one and delete housekeeper whose age has exceeded age
 * limit.
 */
public class AgeIncreaseCommand extends Command {
    private static final String UPDATE_AGE_BY_ONE = "is a new year";

    public AgeIncreaseCommand(String input) throws HotelLiteManagerException {
        if (!input.equals(UPDATE_AGE_BY_ONE)) {
            throw new InvalidNewYearException();
        }
    }

    /**
     * Increases all housekeeper's age by one and records housekeeper who has exceed age limit. Housekeeper who exceed
     * age limit will be removed from the list.
     *
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @param ui            The printing of message to notify user that every housekeeper age has increase by one
     *                      and also prints the the list of overage housekeepers.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.increaseAllAgeByOne();
        ui.printMessage("Everyone age has increased by one");
        ArrayList<Housekeeper> overAgeHousekeeperList = housekeeperList.getHousekeeperExceedValidAgeList();
        ui.printMessage("**Over age limit housekeeper will be removed from list**");
        ui.printOverAgeList(overAgeHousekeeperList);
        housekeeperList.deleteOverAgeHousekeeper();
    }

    /**
     * This method updates all the age of housekeeper in records by one.
     *
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @throws IOException Write to has file failed.
     */
    public void writeAgeIncreaseToFile(ListContainer listContainer) throws IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        HousekeeperFileManager housekeeperFileManager = new HousekeeperFileManager();
        housekeeperFileManager.save(housekeeperList);
    }
}
