package seedu.duke;

public class AddSatisfactionCommand extends Command {
    private Satisfaction satisfaction;

    // Must add Exceptions to ensure that satisfactionValues are between 1 and 5
    // Also add JUNIT tests (and docstring?)
    // Ask Yangzhi if he pushed his Exceptions code
    public AddSatisfactionCommand(String commandStringWithoutCommand) {
        String[] input = commandStringWithoutCommand.split(" ");
        String customerName = input[0].trim();
        String satisfactionString = input[1].trim();
        int satisfactionValue = Integer.parseInt(satisfactionString);
        Satisfaction satisfaction = new Satisfaction(customerName, satisfactionValue);
        setSatisfaction(satisfaction);
    }

    @Override
    public void execute() {

    }

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