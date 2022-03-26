package seedu.duke;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Extract name and age of housekeeper from user input and record it into the housekeeper list.
 */
public class AddHousekeeperCommand extends Command {
    private Housekeeper housekeeper;
    private static final String AGE_INDICATE = "~";
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
            throw new InvalidHousekeeperProfile();
        }
        Housekeeper housekeeper = extractDetails(commandStringWithoutCommand);
        setHousekeeper(housekeeper);
    }

    /**
     * Extract name and age details of the housekeeper.
     *
     * @param commandStringWithoutCommand Input given by user.
     * @return housekeeper profile.
     * @throws InvalidAgeException       Age enter is invalid.
     * @throws InvalidHousekeeperProfile Command enter regarding the housekeeper profile is wrong.
     */
    private Housekeeper extractDetails(String commandStringWithoutCommand)
            throws InvalidAgeException, InvalidHousekeeperProfile, UnderAgeException, OverAgeException {
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(AGE_INDICATE);
        if (isSymbolIncorrect) {
            logger.log(Level.WARNING, "Housekeeper command usage is found to be wrong.");
            throw new InvalidHousekeeperProfile();
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
            throw new InvalidHousekeeperProfile();
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
     * @throws InvalidAgeException When age given is not valid.
     */
    private int extractAge(String inputAge) throws InvalidAgeException, UnderAgeException, OverAgeException {
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
     * This method extracts the name of the housekeeper from the user input.
     *
     * @param inputName Input name give by user.
     * @return Valid name.
     * @throws InvalidHousekeeperProfile When name given is empty
     */
    private String extractName(String inputName) throws InvalidHousekeeperProfile {
        String name;
        try {
            name = inputName.trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidHousekeeperProfile();
        }
        if (name.isEmpty()) {
            throw new InvalidHousekeeperProfile();
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
     *
     * @param ui The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws InvalidUserException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        boolean isRecorded = housekeeperList.hasNameAdded(getHousekeeper().getName());
        if (!isRecorded) {
            housekeeperList.addHousekeeper(getHousekeeper());
            ui.printHousekeeperNoted(housekeeper);
        } else {
            throw new InvalidUserException();
        }

    }
}
