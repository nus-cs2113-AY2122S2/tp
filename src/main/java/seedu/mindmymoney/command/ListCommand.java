package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;

public class ListCommand extends Command {
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    private String listInput;

    public ListCommand(String listInput, ExpenditureList expenditureList, CreditCardList creditCardList) {
        this.expenditureList = expenditureList;
        this.creditCardList = creditCardList;
        this.listInput = listInput;
    }

    private boolean hasExpensesFlag() {
        return FLAG_OF_EXPENSES.equals(listInput);
    }

    private boolean hasCreditCardListFlag() {
        return FLAG_OF_CREDIT_CARD.equals(listInput);
    }

    public String expenditureListToString() {
        int indexOfList = 1;
        String listInString = "";
        for (Expenditure i : expenditureList.expenditureListArray) {
            if (i.getCategory() == null) {
                listInString += indexOfList + ". $" + i.getAmount() + " on " + i.getDescription()
                        + " [" + i.getTime()+ "]" +"\n";
            } else {
                listInString += indexOfList + ". $" + i.getAmount() + " on " + i.getDescription() + " from "
                        + i.getCategory() + " [" + i.getTime()+ "]" + "\n";
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
        if (expenditureList.isEmpty()) {
            throw new MindMyMoneyException(
                    "Your expenditure list is currently empty! Please add some expenditures to your list first");
        } else {
            System.out.print(PrintStrings.LINE);
            System.out.print(expenditureListToString());
            System.out.println(PrintStrings.LINE);
        }
    }

    public String creditCardListToString() {
        int indexOfList = 1;
        String listInString = "";
        for (CreditCard i : creditCardList.creditCardListArray) {
            listInString += indexOfList + ". Name: " + i.getNameOfCard() + " Cashback: " + i.getCashback()
                    +  "% Card limit: $" + i.getMonthlyCardLimit() + " Card balance: $" + i.getBalance() + "\n";
            indexOfList++;
        }
        assert listInString.length() != 0 : "Return string should be non-empty";
        return listInString;
    }

    /**
     * Prints user's current list of credit cards.
     */
    public void printCreditCardList() throws MindMyMoneyException {
        if (creditCardList.isEmpty()) {
            throw new MindMyMoneyException(
                    "Your credit card list is currently empty! Please add some credit cards to your account first");
        } else {
            System.out.print(PrintStrings.LINE);
            System.out.print(creditCardListToString());
            System.out.println(PrintStrings.LINE);
        }
    }

    public void executeCommand() throws MindMyMoneyException {
        if (hasExpensesFlag()) {
            printExpenditureList();
        } else if (hasCreditCardListFlag()) {
            printCreditCardList();
        } else {
            throw new MindMyMoneyException("Please ensure that you have entered a valid list command.\n"
                    + "Use 'list /expenses' to view your current list of expenses\n"
                    + "Use 'list /cc' to view your current list of stored credit cards");
        }
    }
}
