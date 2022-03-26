package seedu.duke;

import java.util.Locale;

public class AddHousekeeperPerformanceCommand extends Command {
    private HousekeeperPerformance housekeeperPerformance;

    public AddHousekeeperPerformanceCommand(String userInput) throws HotelLiteManagerException {
        if (!userInput.contains("/")) {
            throw new InvalidCommandException();
        }
        String housekeeperName = extractHousekeeperName(userInput);
        int housekeeperRating = extractHousekeeperRating(userInput);
        HousekeeperPerformance housekeeperPerformance = new HousekeeperPerformance(housekeeperName, housekeeperRating);
        setHousekeeperPerformance(housekeeperPerformance);
    }

    public String extractHousekeeperName(String userInput) throws HotelLiteManagerException {
        String[] splitInput = userInput.split("/");
        String housekeeperName = splitInput[0].trim();
        if (housekeeperName.isEmpty()) {
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
            throw new InvalidSatisfactionValueException();
        }
        if (ratingValue < 1 || ratingValue > 5) {
            throw new InvalidSatisfactionValueException();
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
    public void execute(HousekeeperList housekeeperList, HousekeeperPerformanceList housekeeperPerformanceList,
                        SatisfactionList satisfactionList, AssignmentMap assignmentMap, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        housekeeperPerformanceList.addHousekeeperPerformance(housekeeperPerformance);
        ui.printAddHousekeeperPerformanceAcknowledgementMessage(housekeeperPerformanceList, housekeeperPerformance);
        housekeeperPerformanceList.viewPerformances();

    }

}
