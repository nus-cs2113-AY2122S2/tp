package seedu.duke;

/**
 * Extract name and age of housekeeper from user input and record it into the
 * housekeeper list.
 */
public class AddHousekeeperCommand extends Command {
    private Housekeeper housekeeper;
    private static final String AGE_INDICATE = "~";
    private Ui ui = new Ui();

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
     * @throws InvalidAgeException       Age enter is invalid/
     * @throws InvalidHousekeeperProfile Command enter regarding the housekeeper profile is wrong.
     */
    private Housekeeper extractDetails(String commandStringWithoutCommand)
            throws InvalidAgeException, InvalidHousekeeperProfile {
        int ageNumber;
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(AGE_INDICATE);
        if (isSymbolIncorrect) {
            throw new InvalidHousekeeperProfile();
        }
        String[] input = commandStringWithoutCommand.split(AGE_INDICATE);
        String name = input[0].trim();
        try {
            String age = input[1].trim();
            ageNumber = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new InvalidAgeException();
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAgeException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidAgeException();
        }
        Housekeeper housekeeper = new Housekeeper(name, ageNumber);
        return housekeeper;
    }

    public Housekeeper getHousekeeper() {
        return housekeeper;
    }

    public void setHousekeeper(Housekeeper housekeeper) {
        this.housekeeper = housekeeper;
    }

    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        boolean isRecorded = housekeeperList.hasNameAdded(getHousekeeper().getName());
        if (!isRecorded) {
            housekeeperList.addHousekeeper(getHousekeeper());
            ui.printHousekeeperNoted(housekeeper);
        } else {
            ui.printMessage("This person has already been recorded");
        }

    }
}
