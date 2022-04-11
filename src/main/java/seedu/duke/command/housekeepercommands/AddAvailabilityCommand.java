package seedu.duke.command.housekeepercommands;

import seedu.duke.ListContainer;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidAvailabilityException;
import seedu.duke.exceptions.InvalidDayException;
import seedu.duke.Ui;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.command.Command;
import seedu.duke.storage.HousekeeperFileManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Identifies the name and availability of the housekeeper and update availability into the
 * housekeeper list. An AddAvailabilityCommand object consists of the name of the housekeeper in list together with
 * their availabilities given. This can be used for updating or adding availabilities.
 */
public class AddAvailabilityCommand extends Command {
    private String name;
    private String availability;
    private static final String AVAILABILITY_INDICATE = "/";
    private static final char AVAILABILITY_INDICATE_CHARACTER = '/';
    private static final char COMMA_INDICATE_CHARACTER = ',';
    private static final int MONDAY_INDICATE = 1;
    private static final int SUNDAY_INDICATE = 7;
    private static final int CONTAIN_ONE_SLASH_ONLY = 1;
    private static Logger logger = Logger.getLogger("housekeeperLogger");

    public AddAvailabilityCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        String[] input;
        String inputName;
        String inputAvailability;
        if (commandStringWithoutCommand.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        try {
            input = extractInput(commandStringWithoutCommand);
            inputName = input[0].trim();
            inputAvailability = input[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Availability Housekeeper command is found to be empty.");
            throw new InvalidAvailabilityException();
        }
        String name = checkValidExtract(inputName);
        String availability = checkValidAvailability(inputAvailability);
        assert (!name.isEmpty()) : "Name of Housekeeper should not be empty.";
        assert (!availability.isEmpty()) : "Availability should not be empty";
        setName(name);
        setAvailability(availability);
    }

    /**
     * This method checks if the availability given by the user is valid. It is valid if any of the days in week given
     * is within 1 to 7 where 1 represents Monday and 7 represents Sunday.
     *
     * @param inputAvailability Availability given by the user.
     * @return A valid availability.
     * @throws HotelLiteManagerException When the given availability is not integer and between 1 and 7.
     */
    private String checkValidAvailability(String inputAvailability) throws HotelLiteManagerException {
        String availability = checkValidExtract(inputAvailability);
        long commaCounts = checkCorrectCommaGiven(inputAvailability);
        try {
            String[] splitDays = availability.split(",");
            long expectedLength = splitDays.length - 1;
            if (commaCounts != expectedLength) {
                throw  new InvalidDayException();
            }
            if (splitDays.length == 0) {
                throw new InvalidDayException();
            }
            for (String day : splitDays) {
                String trimDay = day.trim();
                String validDay = checkLengthInput(trimDay);
                int availableDay = Integer.parseInt(validDay);
                if (availableDay < MONDAY_INDICATE || availableDay > SUNDAY_INDICATE) {
                    logger.log(Level.WARNING, "Days given were not valid.");
                    throw new InvalidDayException();
                }
            }
        } catch (NumberFormatException numberError) {
            logger.log(Level.WARNING, "Number given not Integer.");
            throw new InvalidDayException();
        }
        return availability;
    }

    /**
     * Return the counts of commas.
     *
     * @param inputAvailability Availability given by user.
     * @return total counts of comma.
     */
    private long checkCorrectCommaGiven(String inputAvailability) {
        long commaCounts = inputAvailability.codePoints()
                .filter(t -> t == COMMA_INDICATE_CHARACTER)
                .count();
        return commaCounts;
    }

    /**
     * This method ensures that the derive day has a length of one as the user only can key in a single integer
     * number.
     *
     * @param day Number used to indicate the day in the week.
     * @return Valid day.
     * @throws HotelLiteManagerException When the day given has length more than1. Eg (12,2DF)
     */
    private String checkLengthInput(String day) throws HotelLiteManagerException {
        String trimmedDay = day.trim();
        if (trimmedDay.length() > 1) {
            logger.log(Level.WARNING, "Length given was more than 1.");
            throw new InvalidDayException();
        }
        assert (trimmedDay.length() == 1) : "Day mention has more than length of 1";
        return trimmedDay;
    }

    /**
     * This method verifies that input given by user is not empty.
     *
     * @param inputGiven Either the name or availability given by user.
     * @return Either the valid Name or Availability.
     * @throws HotelLiteManagerException When input given is empty.
     */
    private String checkValidExtract(String inputGiven) throws HotelLiteManagerException {
        if (inputGiven.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        return inputGiven;
    }

    /**
     * Method used to extract the name and availability of the housekeeper. Ensure slash given is only one and
     * is of correct delimiter.
     *
     * @param commandStringWithoutCommand Input entered by the user.
     * @return Input consisting of housekeeper name and availability.
     * @throws HotelLiteManagerException When description of availability is invalid.
     */
    private String[] extractInput(String commandStringWithoutCommand) throws HotelLiteManagerException {
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(AVAILABILITY_INDICATE);
        if (isSymbolIncorrect) {
            logger.log(Level.WARNING, "Availability Housekeeper command usage is wrong.");
            throw new InvalidAvailabilityException();
        }
        long slashCounts = commandStringWithoutCommand.codePoints()
                .filter(t -> t == AVAILABILITY_INDICATE_CHARACTER)
                .count();
        if (!(slashCounts == CONTAIN_ONE_SLASH_ONLY)) {
            logger.log(Level.WARNING, "Contains more than one slash.");
            throw new InvalidAvailabilityException();
        }
        String[] input = commandStringWithoutCommand.split(AVAILABILITY_INDICATE);
        return input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * Get the Name of the housekeeper and verify that housekeeper is in records. If in records, add
     * his/her availability into housekeeper list.
     *
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @param ui            The user interface for this execution method.
     * @throws HotelLiteManagerException If user does not exist in the records.
     * @throws IOException Write to file has failed.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.addAvailabilityInList(name, availability);
        ui.printNotedLine();
        ui.printMessage("Added " + name + " availability into records");
        ui.printBottomLine();
    }

    /**
     * This methods update the housekeeper's availability into the housekeeper file.
     *
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @throws IOException Write to file has failed.
     */
    public void writeAvailabilityToFile(ListContainer listContainer) throws IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        HousekeeperFileManager housekeeperFileManager = new HousekeeperFileManager();
        housekeeperFileManager.save(housekeeperList);
    }
}
