package seedu.duke;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.duke.command.Command;

/**
 * Number given from user to view list of housekeeper available on any day from Monday to Sunday.
 */
public class GetAvailableHousekeeper extends Command {
    private int searchDay;
    private static Logger logger = Logger.getLogger("housekeeperLogger");
    private static final int MONDAY_INDICATE = 1;
    private static final int SUNDAY_INDICATE = 7;

    public GetAvailableHousekeeper(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            logger.log(Level.WARNING, "Empty Day.");
            throw new EmptyDayException();
        }
        searchDay = checkCorrectDayGiven(commandStringWithoutCommand);
        assert (searchDay >= MONDAY_INDICATE & searchDay <= SUNDAY_INDICATE) : "Input day incorrect range.";
    }

    /**
     * This method checks the day input by the user is within 1 to 7.
     *
     * @param commandStringWithoutCommand Day input given by user.
     * @return Day within valid range.
     * @throws InvalidDayException if input is not an integer or not between 1 and 7.
     */
    private int checkCorrectDayGiven(String commandStringWithoutCommand) throws InvalidDayException {
        int day;
        try {
            day = Integer.parseInt(commandStringWithoutCommand);
        } catch (NumberFormatException numberError) {
            logger.log(Level.WARNING, "Day is not an integer.");
            throw new InvalidDayException();
        }
        if (day < MONDAY_INDICATE | day > SUNDAY_INDICATE) {
            logger.log(Level.WARNING, "Day is not within range.");
            throw new InvalidDayException();
        }
        return day;
    }

    public int getSearchDay() {
        return searchDay;
    }

    public void setSearchDay(int searchDay) {
        this.searchDay = searchDay;
    }

    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        ArrayList<Housekeeper> foundList = housekeeperList.getAvailableHousekeeperByDay(searchDay);
        ui.printFoundHousekeeperList(foundList, searchDay);
    }
}
