package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to add a new HousekeeperPerformance object to the HousekeeperPerformanceList.
 * In other words, implements the user command of recording a housekeeper's name and their performance rating.
 */

public class AddHousekeeperPerformanceCommand extends Command {
    private HousekeeperPerformance housekeeperPerformance;
    private static Logger logger = Logger.getLogger("housekeeperPerformanceLogger");

    /**
     * Takes in the user input and checks if the formatting of the command for
     * adding a housekeeper's performance rating is valid.
     * Extracts the housekeeper's name and performance rating from the user input and creates an
     * AddHousekeeperPerformanceCommand object.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException If there is no "/" character included in the user input, if there is no
     *                                   housekeeper name provided, if there is no housekeeper rating provided, or if
     *                                   the rating is invalid (not an integer from 1-5 inclusive).
     */
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

    /**
     * Returns the housekeeper's name that is extracted from user input.
     * @param userInput The user's input.
     * @return The name of the housekeeper specified in the user input.
     * @throws HotelLiteManagerException if the name provided by the user is empty.
     */
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

    /**
     * Returns the housekeeper's performance rating that is extracted from user input.
     * @param userInput The user's input.
     * @return The performance rating of the housekeeper specified in user input.
     * @throws HotelLiteManagerException if the performance rating provided by the user is empty.
     */
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
    /**
     * Adds a new HousekeeperPerformance object to the HousekeeperPerformanceList using the
     * housekeeper name and performance rating found in the housekeeperPerformance instance variable.
     * Returns an acknowledgement message to inform the user that the performance has been recorded.
     *
     * @param listContainer The object containing the data structures necessary for recording a housekeeper's
     *                      performance. In this case, we are manipulating the housekeeperPerformanceList object
     *                      (adding to it), and we require information from the housekeeperList object.
     * @param ui The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     */
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
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
