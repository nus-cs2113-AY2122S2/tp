package seedu.duke;

import java.util.ArrayList;
import seedu.duke.command.Command;

public class AgeIncreaseCommand extends Command {

    /**
     * Increases all housekeeper's age by one and records housekeeper who has exceed age limit. Housekeeper who exceed
     * age limit will be removed from the list.
     *
     * @param listContainer The list of information.
     * @param ui The instance of the Ui class used for printing additional messages when a command is executed.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.increaseAllAgeByOne();
        ui.printMessage("Everyone age has increased by one");
        ArrayList<Housekeeper> overAgeHousekeeperList = housekeeperList.getHousekeeperExceedValidAgeList();
        ui.printMessage("**Over age limit housekeeper will be removed from list**");
        ui.printOverAgeList(overAgeHousekeeperList);
        housekeeperList.deleteOverAgeHousekeeper();
    }
}
