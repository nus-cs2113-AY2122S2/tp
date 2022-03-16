package seedu.duke;

import java.util.ArrayList;

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
     * @param housekeeperList The list of housekeeper recorded.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList The given list of Room objects. N/A for this class, but
 *                 must be included for the execution override.
     * @param itemList The given list of Item objects. N/A for this class, but
*                 must be included for the execution override.
     * @param ui The user interface for this execution method.
     */
    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList,
                        ItemList itemList, Ui ui) {
        int number = 1;
        ArrayList<Satisfaction> satisfactionArrayList = satisfactionList.getSatisfactionList();
        for (Satisfaction satisfaction : satisfactionArrayList) {
            System.out.println(Integer.toString(number) + ". " + satisfaction.getCustomerName() + " - "
                    + satisfaction.getSatisfactionValue());
            number += 1;
        }

    }
}

