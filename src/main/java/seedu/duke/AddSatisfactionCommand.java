package seedu.duke;

public class AddSatisfactionCommand extends Command {
    private Satisfaction satisfaction;

    public AddSatisfactionCommand(String commandStringWithoutCommand) throws HotelLiteManagerException{
        String customerName = "";
        int satisfactionValue = 0;
        try {
            customerName = extractCustomerName(commandStringWithoutCommand);
            satisfactionValue = extractSatisfactionValue(commandStringWithoutCommand);
        } catch (EmptySatisfactionCustomerException e) {
            throw new EmptySatisfactionCustomerException();
        } catch (EmptySatisfactionValueException e) {
            throw new EmptySatisfactionValueException();
        } catch (InvalidSatisfactionValueException e) {
            throw new InvalidSatisfactionValueException();
        }
        Satisfaction satisfaction = new Satisfaction(customerName, satisfactionValue);
        setSatisfaction(satisfaction);
    }

    private String extractCustomerName(String userInput) throws HotelLiteManagerException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length == 0) {
            throw new EmptySatisfactionCustomerException();
        }
        String customerName = splitInput[0].trim();
        if (customerName.isEmpty()) {
            throw new EmptySatisfactionCustomerException();
        }
        return customerName;
    }

    private int extractSatisfactionValue(String userInput) throws HotelLiteManagerException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < 2) {
            throw new EmptySatisfactionValueException();
        }
        String satisfactionString = splitInput[1].trim();
        int satisfactionValue = Integer.parseInt(satisfactionString);
        if (satisfactionValue < 1 || satisfactionValue > 5) {
            throw new InvalidSatisfactionValueException();
        }
        return satisfactionValue;
    }

    @Override
    public void execute() {}

    public void execute(SatisfactionList satisfactionList) {
        satisfactionList.addSatisfaction(satisfaction);
    }

    public Satisfaction getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Satisfaction satisfaction) {
        this.satisfaction = satisfaction;
    }
}