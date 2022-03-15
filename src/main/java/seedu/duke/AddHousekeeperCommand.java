package seedu.duke;

public class AddHousekeeperCommand extends Command {
    private Housekeeper housekeeper;
    private final String AGE_INDICATE = "~";
    private Ui ui = new Ui();

    public AddHousekeeperCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            throw new InvalidHousekeeperProfile();
        }
        Housekeeper housekeeper = extractDetails(commandStringWithoutCommand);
        setHousekeeper(housekeeper);
        ui.printHousekeeperNoted(housekeeper);
    }

    private Housekeeper extractDetails(String commandStringWithoutCommand) throws InvalidAgeException, InvalidHousekeeperProfile {
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
    public void execute(Ui ui) throws WrongCommandException {


    }
}
