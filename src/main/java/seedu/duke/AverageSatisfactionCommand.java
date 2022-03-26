package seedu.duke;

/**
 * Class that implements execution behavior for user command
 * "average satisfaction." Contains override of execution
 * method in Command class that calculations the average
 * customer satisfaction rating of the given list of Satisfaction
 * objects.
 */

public class AverageSatisfactionCommand extends Command {

    /**
     * Override of execute command in Command class.
     * Calculates the average customer satisfaction rating of the
     * given satisfactionList.
     *
     * @param ui               The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        System.out.println("Average customer satisfaction: " + satisfactionList.calculateAverageSatisfaction());
    }

}
