package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

public class ListCommand extends Command {
    public ExpenditureList expenditureList;

    public ListCommand(ExpenditureList expenditureList) {
        this.expenditureList = expenditureList;
    }

    public String listToString() {
        int indexOfList = 1;
        String listInString = "";
        for (Expenditure i : expenditureList.expenditureListArray) {
            if (i.getCategory() == null) {
                listInString += indexOfList + ". $" + i.getAmount() + " on " + i.getDescription() + "\n";
            } else {
                listInString += indexOfList + ". $" + i.getAmount() + " on " + i.getDescription() + " from "
                        + i.getCategory() + "\n";
            }
            indexOfList++;
        }
        return listInString;
    }

    /**
     * Prints user's current list of expenditures.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        if (expenditureList.size() == 0) {
            throw new MindMyMoneyException(
                    "Your list is currently empty! Please add some expenditures to your list first");
        } else {
            System.out.print(PrintStrings.LINE);
            System.out.println(listToString());
            System.out.print(PrintStrings.LINE);
        }
    }
}
