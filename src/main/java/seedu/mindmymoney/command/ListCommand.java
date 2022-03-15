package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.Lists;
import seedu.mindmymoney.userfinancial.Expenditure;

public class ListCommand extends Command {
    public ListCommand() {
    }

    public String listToString() {
        int indexOfList = 1;
        String listInString = "";
        for (Expenditure i : Lists.expenditures) {
            listInString += indexOfList + ". $" + i.getAmount() + " on " + i.getDescription() + "\n";
            indexOfList++;
        }
        return listInString;
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
            System.out.print(PrintStrings.LINE);
            System.out.println(listToString());
            System.out.print(PrintStrings.LINE);
        }
    }
}
