package seedu.duke.command.housekeepercommands;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import seedu.duke.ListContainer;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidAgeException;
import seedu.duke.exceptions.InvalidHousekeeperProfileException;
import seedu.duke.exceptions.UnderAgeException;
import seedu.duke.exceptions.OverAgeException;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.NameNotStringException;
import seedu.duke.Ui;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.command.Command;
import seedu.duke.storage.HousekeeperFileManager;

/**
 * Extract name and age of housekeeper from user input and record it into the housekeeper list.
 */
public class AddHousekeeperCommand extends Command {
    private static final int CONTAIN_ONE_SLASH_ONLY = 1;
    private static final String ADD_HOUSEKEEPER_COMMAND = "add housekeeper";
    private Housekeeper housekeeper;
    private static final String AGE_INDICATE = "/";
    private static final char AGE_INDICATE_CHARACTER = '/';
    private static final int MIN_AGE_ACCEPTED = 21;
    private static final int MAX_AGE_ACCEPTED = 60;
    private static Logger logger = Logger.getLogger("housekeeperLogger");

    /**
     * Creates a new housekeeper profile consisting of their name and age which would be recorded
     * into housekeeper list.
     *
     * @param commandStringWithoutCommand Input of age and name given by user.
     * @throws HotelLiteManagerException When user input an empty data.
     */
    public AddHousekeeperCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            logger.log(Level.WARNING, "Housekeeper command usage is found to be wrong.");
            throw new InvalidHousekeeperProfileException();
        }
        String inputWithNoSpace = commandStringWithoutCommand.trim();
        if (inputWithNoSpace.contains(ADD_HOUSEKEEPER_COMMAND)) {
            logger.log(Level.WARNING, "Repeated add housekeeper command given.");
            throw new DuplicateCommandException();
        }
        Housekeeper housekeeper = extractDetails(commandStringWithoutCommand);
        setHousekeeper(housekeeper);
    }

    /**
     * Extract name and age details of the housekeeper.
     *
     * @param commandStringWithoutCommand Input given by user.
     * @return housekeeper profile.
     * @throws HotelLiteManagerException Age enter is invalid and command enter regarding the housekeeper profile
     *                                   is wrong.
     */
    private Housekeeper extractDetails(String commandStringWithoutCommand) throws HotelLiteManagerException {
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(AGE_INDICATE);
        if (isSymbolIncorrect) {
            logger.log(Level.WARNING, "Housekeeper command usage is found to be wrong.");
            throw new InvalidHousekeeperProfileException();
        }
        long slashCounts = commandStringWithoutCommand.codePoints()
                .filter(t -> t == AGE_INDICATE_CHARACTER)
                .count();
        if (!(slashCounts == CONTAIN_ONE_SLASH_ONLY)) {
            logger.log(Level.WARNING, "Contains more than one slash.");
            throw new InvalidHousekeeperProfileException();
        }
        String[] input;
        String inputAge;
        String inputName;
        try {
            input = commandStringWithoutCommand.split(AGE_INDICATE);
            inputName = input[0];
            inputAge = input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Housekeeper command is found to be empty.");
            throw new InvalidHousekeeperProfileException();
        }
        int ageNumber;
        String name;
        name = extractName(inputName);
        ageNumber = extractAge(inputAge);
        assert (!name.isEmpty()) : "Housekeeper name should not be empty.";
        Housekeeper housekeeper = new Housekeeper(name, ageNumber);
        return housekeeper;
    }

    /**
     * This method extracts the age of housekeeper from the user input.
     *
     * @param inputAge Input age given by user.
     * @return Valid age.
     * @throws HotelLiteManagerException When age given is not valid.
     */
    private int extractAge(String inputAge) throws HotelLiteManagerException {
        int ageNumber;
        String age;
        try {
            age = inputAge.trim();
            ageNumber = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new InvalidAgeException();
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAgeException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidAgeException();
        }
        assert (!age.isEmpty()) : "Age should not be empty.";
        if (ageNumber < MIN_AGE_ACCEPTED) {
            throw new UnderAgeException();
        }
        if (ageNumber > MAX_AGE_ACCEPTED) {
            throw new OverAgeException();
        }
        assert (ageNumber >= MIN_AGE_ACCEPTED & ageNumber <= MAX_AGE_ACCEPTED) : "Age range is invalid.";
        return ageNumber;
    }

    /**
     * This method extracts the name of the housekeeper from the user input. It checks if there exist any digits
     * or symbol in the name.
     *
     * @param inputName Input name give by user.
     * @return Valid name.
     * @throws HotelLiteManagerException When name given is empty and when name has symbols and digits.
     */
    private String extractName(String inputName) throws HotelLiteManagerException {
        String name;
        try {
            name = inputName.trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidHousekeeperProfileException();
        }
        if (name.isEmpty()) {
            throw new InvalidHousekeeperProfileException();
        }
        if (!name.matches("^([a-z]|\\s|[A-Z])+$")) {
            throw new NameNotStringException();
        }
        return name;
    }

    public Housekeeper getHousekeeper() {
        return housekeeper;
    }

    public void setHousekeeper(Housekeeper housekeeper) {
        this.housekeeper = housekeeper;
    }

    /**
     * Method to add new housekeeper profile into list and rejects any profile that has already been recorded.
     *  @param listContainer Containing the list of information
     * @param ui The user interface for this execution method.
     * @return
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.addHousekeeperInList(getHousekeeper());
        ui.printHousekeeperNoted(housekeeper);
    }

    /**
     * This method updates the new profile into the file.
     *
     * @param listContainer Containing the list of information.
     * @throws IOException Write to file failed.
     */
    public void writeHousekeeperToFile(ListContainer listContainer) throws IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        HousekeeperFileManager housekeeperFileManager = new HousekeeperFileManager();
        housekeeperFileManager.save(housekeeperList);
    }
}
