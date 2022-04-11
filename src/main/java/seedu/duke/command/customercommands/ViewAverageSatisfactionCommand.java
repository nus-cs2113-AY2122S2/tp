package seedu.duke.command.customercommands;

import seedu.duke.ListContainer;
import seedu.duke.satisfactionlists.SatisfactionList;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.exceptions.HotelLiteManagerException;

/**
 * Class that implements execution behavior for user command
 * "average satisfaction." Contains override of execution
 * method in Command class that calculations the average
 * customer satisfaction rating of the given list of Satisfaction
 * objects.
 */

public class ViewAverageSatisfactionCommand extends Command {

    /**
     * Override of execute command in Command class.
     * Calculates the average customer satisfaction rating of the
     * given satisfactionList.
     *
     * @param ui The user interface for this execution method.
     * @return
     */
    @Override
    public Object execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        System.out.println("Average customer satisfaction: " + satisfactionList.calculateAverageSatisfaction());
        return null;
    }

}
