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
     * @param housekeeperList  The list of housekeeper recorded. N/A for this class, but
     *                         must be included for the execution override.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList         The given list of Room objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param itemList         The given list of Item objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param ui               The user interface for this execution method.
     */
    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList,
                        ItemList itemList, Ui ui) {
        System.out.println("Average customer satisfaction: " + satisfactionList.calculateAverageSatisfaction());
    }

}
