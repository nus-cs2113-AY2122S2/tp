package seedu.duke;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddHousekeeperPerformanceCommand extends Command {
    private HousekeeperPerformance housekeeperPerformance;
    private static Logger logger = Logger.getLogger("housekeeperPerformanceLogger");

    public AddHousekeeperPerformanceCommand(String userInput) throws HotelLiteManagerException {
        if (!userInput.contains("/")) {
            logger.log(Level.WARNING, "A '/' character is needed to separate the housekeeper's name "
                    + "from their rating.");
            throw new InvalidCommandException();
        }
        String housekeeperName = extractHousekeeperName(userInput);
        int housekeeperRating = extractHousekeeperRating(userInput);
        HousekeeperPerformance housekeeperPerformance = new HousekeeperPerformance(housekeeperName, housekeeperRating);
        setHousekeeperPerformance(housekeeperPerformance);
    }

    public String extractHousekeeperName(String userInput) throws HotelLiteManagerException {
        String[] splitInput = userInput.split("/");
        String housekeeperName = "";
        try {
            housekeeperName = splitInput[0].trim();
            if (housekeeperName.isEmpty()) {
                throw new EmptyHousekeeperPerformanceNameException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyHousekeeperPerformanceNameException();
        }
        return housekeeperName;
    }

    public int extractHousekeeperRating(String userInput) throws HotelLiteManagerException {
        String[] splitInput = userInput.split("/");
        String ratingString = splitInput[1].trim();
        int ratingValue;
        try {
            ratingValue = Integer.parseInt(ratingString);
        } catch (NumberFormatException e) {
            throw new InvalidHousekeeperPerformanceRatingException();
        }
        if (ratingValue < 1 || ratingValue > 5) {
            throw new InvalidHousekeeperPerformanceRatingException();
        }
        return ratingValue;
    }

    public HousekeeperPerformance getHousekeeperPerformance() {
        return housekeeperPerformance;
    }

    public void setHousekeeperPerformance(HousekeeperPerformance housekeeperPerformance) {
        this.housekeeperPerformance = housekeeperPerformance;
    }


    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        HousekeeperPerformanceList housekeeperPerformanceList = listContainer.getHousekeeperPerformanceList();
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        // Checks if the user tries to add a housekeeper performance for a housekeeper not the HousekeeperList records
        if (!housekeeperList.hasNameAdded(housekeeperPerformance.getName())) {
            throw new NonexistentHousekeeperException();
        } else if (housekeeperPerformanceList.isHousekeeperInPerformanceList(housekeeperPerformance.getName())) {
            throw new RepeatHousekeeperPerformanceNameException();
        }
        housekeeperPerformanceList.addHousekeeperPerformance(housekeeperPerformance);
        ui.printAddHousekeeperPerformanceAcknowledgementMessage(housekeeperPerformanceList, housekeeperPerformance);

    }

}
