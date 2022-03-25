package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

public class ListCommand extends Command {
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    private String listInput;

    public ListCommand(String listInput, ExpenditureList expenditureList, CreditCardList creditCardList) {
        this.expenditureList = expenditureList;
        this.creditCardList = creditCardList;
        this.listInput = listInput;
    }

    public String expenditureListToString() {
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
        assert listInString.length() != 0 : "Return string should be non-empty";
        return listInString;
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints user's current list of expenditures.
     */
    public void printExpenditureList() throws MindMyMoneyException {
        if (expenditureList.size() == 0) {
            throw new MindMyMoneyException(
                    "Your list is currently empty! Please add some expenditures to your list first");
        } else {
            System.out.print(PrintStrings.LINE);
            System.out.print(expenditureListToString());
            System.out.println(PrintStrings.LINE);
        }
    }

    public void executeCommand() throws MindMyMoneyException {
        if ()
    }
}
