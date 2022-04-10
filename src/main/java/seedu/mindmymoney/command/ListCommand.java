package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.helper.GeneralFunctions;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.Income;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_INCOME;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;
import static seedu.mindmymoney.helper.TimeFunctions.isValidInputCalculateCommand;

/**
 * Represents the List command.
 */
public class ListCommand extends Command {
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    public IncomeList incomeList;
    private String listInput;
    private static final int COUNTVALUE = 1;

    public ListCommand(String listInput, User user) {
        this.expenditureList = user.getExpenditureListArray();
        this.creditCardList = user.getCreditCardListArray();
        this.incomeList = user.getIncomeListArray();
        this.listInput = listInput;
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return true if the program should exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Indicates whether the list command is to list expenditure(s) by looking for the /e flag.
     *
     * @return true if the /pm flag is present, false otherwise.
     */
    private boolean hasExpensesFlag() {
        return listInput.startsWith(FLAG_OF_EXPENSES);
    }

    /**
     * Indicates whether the list command is to list credit card(s) by looking for the /cc flag.
     *
     * @return true if the /cc flag is present, false otherwise.
     */
    private boolean hasCreditCardListFlag() {
        return FLAG_OF_CREDIT_CARD.equals(listInput);
    }

    /**
     * Indicates whether the list command is to list income(s) by looking for the /i flag.
     *
     * @return true if the /i flag is present, false otherwise.
     */
    private boolean hasIncomeListFlag() {
        return FLAG_OF_INCOME.equals(listInput);
    }

    /**
     * Gets all expenditures and formats them into a String to be printed.
     *
     * @return String of expenditures.
     * @throws MindMyMoneyException Throws an exception when the date is not in the correct format
     */
    public String expenditureListToString() throws MindMyMoneyException {
        int count = COUNTVALUE;
        String listInString = "";
        if (listInput.equals(FLAG_OF_EXPENSES)) {
            listInString = listString(count, listInString);
        } else {
            listInString = outputListWithDate(count,listInString);
        }
        assert listInString.length() != 0 : "Return string should be non-empty";
        return listInString;
    }

    /**
     * Outputs the list of expenses with date.
     * @param count To obtain the numbering when listing the expenses.
     * @param listInString String where the content of output is appended to.
     * @return String of expenditures.
     * @throws MindMyMoneyException Throws an exception when the date is not in the correct format.
     */
    public String outputListWithDate(int count, String listInString) throws MindMyMoneyException {
        String[] inputArray = GeneralFunctions.parseInput(listInput);
        if (!inputArray[INDEX_OF_SECOND_ITEM].equals("")) {
            if (!isValidInputCalculateCommand(inputArray[INDEX_OF_SECOND_ITEM])) {
                throw new MindMyMoneyException("Date has to be valid and"
                        + " in \"dd/mm/yyyy\", \"mm/yyyy\" or \"yyyy\" format!");
            }
            if (listStringWithDate(count, listInString, inputArray).equals("")) {
                throw new MindMyMoneyException("Date not found in the list! Do check your input");
            }
            return PrintStrings.LINE + listStringWithDate(count, listInString, inputArray) + PrintStrings.LINE;
        } else {
            return listString(count, listInString);
        }
    }

    /**
     * Formats the output of expenses in list according to date.
     *
     * @param count To obtain the numbering when listing the expenses.
     * @param listInString String where the content of output is appended to.
     * @return String of expenditures
     */
    public String listStringWithDate(int count, String listInString, String[] inputArray) {
        for (Expenditure expenditure : expenditureList.expenditureListArray) {
            if (expenditure.getTime().contains(inputArray[INDEX_OF_SECOND_ITEM])) {
                listInString += count + ". $" + String.format("%.2f", expenditure.getAmount()) + " was spent on "
                        + expenditure.getDescription() + "(" + expenditure.getCategory() + ") " + "using "
                        + expenditure.getPaymentMethod() + " [" + expenditure.getTime() + "]" + "\n";
                count++;
            }
        }
        return listInString;
    }

    /**
     * Formats the output of all expenses in list.
     *
     * @param count To obtain the numbering when listing the expenses.
     * @param listInString String where the content of output is appended to.
     * @return String of expenditures
     */
    public String listString(int count, String listInString) {
        listInString += PrintStrings.LINE;
        for (Expenditure expenditure : expenditureList.expenditureListArray) {
            listInString += count + ". $" + String.format("%.2f", expenditure.getAmount()) + " was spent on " 
                + expenditure.getDescription() + "(" + expenditure.getCategory() + ") " + "using " 
                + expenditure.getPaymentMethod() + " [" + expenditure.getTime() + "]" + "\n";
            count++;
        }
        listInString += PrintStrings.LINE;
        return listInString;
    }

    /**
     * Prints user's current list of expenditures.
     *
     * @throws MindMyMoneyException when expenditure list is empty.
     */
    public void printExpenditureList() throws MindMyMoneyException {
        if (expenditureList.isEmpty()) {
            throw new MindMyMoneyException(
                    "Your expenditure list is currently empty! Please add some expenditures to your list first");
        } else {
            System.out.print(expenditureListToString());
        }
    }

    /**
     * Gets all credit cards and formats them into a String to be printed.
     *
     * @return String of credit cards.
     */
    public String creditCardListToString() {
        int indexOfList = 1;
        String listInString = "";
        for (CreditCard creditCard : creditCardList.creditCardListArray) {
            listInString += indexOfList + ". " + creditCard.toString();
            indexOfList++;
        }

        assert listInString.length() != 0 : "Return string should be non-empty";
        return listInString;
    }

    /**
     * Prints user's current list of credit cards.
     *
     * @throws MindMyMoneyException when credit card list is empty.
     */
    public void printCreditCardList() throws MindMyMoneyException {
        if (creditCardList.isEmpty()) {
            throw new MindMyMoneyException(
                    "Your credit card list is currently empty! Please add some credit cards to your account first");
        } else {
            System.out.print(PrintStrings.LINE);
            System.out.print(creditCardListToString());
            System.out.print(PrintStrings.LINE);
        }
    }

    /**
     * Gets all incomes and formats them into a String to be printed.
     *
     * @return String of incomes.
     */
    public String incomeListToString() {
        int indexOfList = 1;
        String listInString = "";
        for (Income income : incomeList.incomeListArray) {
            listInString += indexOfList + ". " + income.toString();
            indexOfList++;
        }

        assert listInString.length() != 0 : "Return string should be non-empty";
        return listInString;
    }

    /**
     * Prints user's current list of incomes.
     *
     * @throws MindMyMoneyException when income list is empty.
     */
    public void printIncomeList() throws MindMyMoneyException {
        if (incomeList.isEmpty()) {
            throw new MindMyMoneyException("Your income list is currently empty! "
                    + "Please add some incomes to your account first");
        } else {
            System.out.print(PrintStrings.LINE);
            System.out.print(incomeListToString());
            System.out.println(PrintStrings.LINE);
        }
    }

    /**
     * Prints either a list of expenditure(s), credit card(s) or income(s) based on the user's input.
     *
     * @throws MindMyMoneyException when an invalid command is received, along with its corresponding error message.
     */
    @Override
    public void executeCommand() throws MindMyMoneyException {
        if (hasExpensesFlag()) {
            printExpenditureList();
        } else if (hasCreditCardListFlag()) {
            printCreditCardList();
        } else if (hasIncomeListFlag()) {
            printIncomeList();
        } else {
            throw new MindMyMoneyException("Please ensure that you have entered a valid list command.\n"
                    + "Use 'list /e' to view your current list of expenditure\n"
                    + "Use 'list /cc' to view your current list of stored credit cards\n"
                    + "Use list /i to view your current list of incomes");
        }
    }
}
