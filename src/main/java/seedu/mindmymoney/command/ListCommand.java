package seedu.mindmymoney.command;

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
    public void executeCommand() {
        if (Lists.expenditures.size() == 0) {
            System.out.println(PrintStrings.LINE
                    + "Your list is currently empty! Try adding expenditures first using the add command"
                    + System.lineSeparator() + PrintStrings.LINE);
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
