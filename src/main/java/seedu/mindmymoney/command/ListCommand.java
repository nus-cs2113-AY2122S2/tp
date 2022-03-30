package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.PrintStrings;
import seedu.mindmymoney.constants.ValidationRegexTypes;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.helper.GeneralFunctions;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.Income;
import seedu.mindmymoney.userfinancial.User;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_INCOME;

/**
 * Represents the List command.
 */
public class ListCommand extends Command {
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;
    public IncomeList incomeList;
    private String listInput;

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
     * Indicates whether the list command is to list expenditure(s) by looking for the /expenses flag.
     *
     * @return true if the /expenses flag is present, false otherwise.
     */
    private boolean hasExpensesFlag() {
        return listInput.contains(FLAG_OF_EXPENSES);
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
        int indexOfList = 1;
        String listInString = "";
        if (listInput.equals(FLAG_OF_EXPENSES)) {
            listInString = printListString(indexOfList, listInString);
        } else {
            String[] inputArray = GeneralFunctions.parseInput(listInput);
            if (!inputArray[1].equals("")) {
                if (!isValidInput(inputArray[1])) {
                    throw new MindMyMoneyException("Date has to be in \"dd/mm/yyyy\", \"mm/yyyy\" or \"yyyy\" format!");
                }
                for (Expenditure i : expenditureList.expenditureListArray) {
                    if (i.getTime().contains(inputArray[1])) {
                        listInString += indexOfList + ". $" + i.getAmount() + " was spent on " + i.getDescription()
                                + "(" + i.getCategory() + ") " + "using " + i.getExpenditure()
                                + " [" + i.getTime() + "]" + "\n";
                        indexOfList++;
                    }
                }
            } else {
                listInString = printListString(indexOfList, listInString);
            }
        }
        assert listInString.length() != 0 : "Return string should be non-empty";
        return listInString;
    }

    /**
     * Formats the output of expenses in list.
     *
     * @param index To obtain the numbering when listing the expenses.
     * @param listInString String where the content of output is appended to.
     * @return
     */
    public String printListString(int index, String listInString) {
        for (Expenditure i : expenditureList.expenditureListArray) {
            listInString += index + ". $" + i.getAmount() + " was spent on " + i.getDescription() + "("
                    + i.getCategory() + ") " + "using " + i.getExpenditure() + " [" + i.getTime() + "]" + "\n";
            index++;
        }
        return listInString;
    }

    /**
     * Checks if date input format is valid.
     *
     * @param input The string of the date input.
     * @return true if format is valid, false otherwise.
     */
    public static boolean isValidInput(String input) {
        if (input.matches(ValidationRegexTypes.VALIDATION_REGEX_D)
                || input.matches(ValidationRegexTypes.VALIDATION_REGEX_M)
                || input.matches(ValidationRegexTypes.VALIDATION_REGEX_Y)) {
            return true;
        } else {
            return false;
        }
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
            System.out.print(PrintStrings.LINE);
            System.out.print(expenditureListToString());
            System.out.println(PrintStrings.LINE);
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
            System.out.println(PrintStrings.LINE);
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
                    + "Please add some credit cards to your account first");
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
                    + "Use 'list /expenses' to view your current list of expenses\n"
                    + "Use 'list /cc' to view your current list of stored credit cards"
                    + "Use list /i to view your current list of incomes");
        }
    }
}
