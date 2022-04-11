package seedu.duke.command.customercommands;

import seedu.duke.ListContainer;
import seedu.duke.satisfactionlists.SatisfactionList;
import seedu.duke.Ui;
import seedu.duke.command.Command;

/**
 * Class that implements execution behavior for user command
 * "View Satisfactions." Contains override of execution
 * method in Command class that prints out the information of the Satisfaction objects
 * in the given satisfactionList (i.e. prints out each Satisfaction object's
 * corresponding customer name and satisfaction value).
 */

public class ViewSatisfactionsCommand extends Command {

    /**
     * Override of execute command in Command class.
     * Prints out the information of the Satisfaction objects
     * in the given satisfactionList (i.e. prints out each Satisfaction object's
     * corresponding customer name and satisfaction value).
     *
     * @param ui               The user interface for this execution method.
     * @return
     */
    @Override
    public Object execute(ListContainer listContainer, Ui ui) {
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        satisfactionList.viewSatisfactions();
        return null;
    }

    
}

