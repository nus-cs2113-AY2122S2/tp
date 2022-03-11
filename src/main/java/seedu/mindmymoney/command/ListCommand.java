package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.Lists;
import seedu.mindmymoney.userfinancial.Expenditure;

public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * Prints user's current list of expenditures.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        if (Lists.expenditures.size() == 0) {
            throw new MindMyMoneyException(
                    "Your list is currently empty! Please add some expenditures to your list first");
        } else {
            System.out.println(PrintStrings.LINE);
            Integer indexOfList = 1;
            for (Expenditure i : Lists.expenditures) {
                System.out.println(indexOfList + ". $" + i.getAmount() + " on " + i.getDescription());
                indexOfList++;
            }
            System.out.println(PrintStrings.LINE);
        }
    }
}
