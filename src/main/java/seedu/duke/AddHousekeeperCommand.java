package seedu.duke;

/**
 * Extract name and age of housekeeper from user input and record it into the housekeeper list.
 */
public class AddHousekeeperCommand extends Command {
    private Housekeeper housekeeper;
    private static final String AGE_INDICATE = "~";

    /**
     * Creates a new housekeeper profile consisting of their name and age which would be recorded
     * into housekeeper list.
     *
     * @param commandStringWithoutCommand Input of age and name given by user.
     * @throws HotelLiteManagerException When user input an empty data.
     */
    public AddHousekeeperCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
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
            throws InvalidAgeException, InvalidHousekeeperProfile {
        int ageNumber;
        String inputAge;
        String inputName;
        String name;
        String[] input;
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(AGE_INDICATE);
        if (isSymbolIncorrect) {
            throw new InvalidHousekeeperProfile();
        }
        try {
            input = commandStringWithoutCommand.split(AGE_INDICATE);
            inputName = input[0];
            inputAge = input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidHousekeeperProfile();
        }
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
    private int extractAge(String inputAge) throws InvalidAgeException {
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
     * @param housekeeperList  The list of housekeeper recorded.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList         The given list of Room objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param listOfItems      The given list of Item objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param ui               The user interface for this execution method.
     */
    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws InvalidUserException {
        boolean isRecorded = housekeeperList.hasNameAdded(getHousekeeper().getName());
        if (!isRecorded) {
            housekeeperList.addHousekeeper(getHousekeeper());
            ui.printHousekeeperNoted(housekeeper);
        } else {
            throw new InvalidUserException();
        }

    }
}
