package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_INCOME;
import static seedu.mindmymoney.constants.Flags.EMPTY_PARAMETER;

/**
 * Represents the Help command. This class also serves as a dummy class to return when an invalid command is
 * received.
 */
public class HelpCommand extends Command {
    protected boolean isFromUser;
    public String helpInput;

    public HelpCommand(boolean isFromUser, String helpInput) {
        this.isFromUser = isFromUser;
        this.helpInput = helpInput;
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
     * Indicates whether the help command is for expenses by looking for the /e flag.
     *
     * @return true if the /e flag is present, false otherwise.
     */
    private boolean hasExpensesFlag() {
        return helpInput.equals(FLAG_OF_EXPENSES);
    }

    /**
     * Indicates whether the help command is for credit card by looking for the /cc flag.
     *
     * @return true if the /cc flag is present, false otherwise.
     */
    private boolean hasCreditCardListFlag() {
        return FLAG_OF_CREDIT_CARD.equals(helpInput);
    }

    /**
     * Indicates whether the help command is for income by looking for the /i flag.
     *
     * @return true if the /i flag is present, false otherwise.
     */
    private boolean hasIncomeFlag() {
        return FLAG_OF_INCOME.equals(helpInput);
    }

    /**
     * Prints out the help page if the user requested for it. If not, it means an invalid command was received,
     * and prints out an error message.
     */
    public void printExpenditureHelpPage() {
        if (isFromUser) {
            String helpPage = "---------------------------------------Expenditure Help Page------------------------"
                    + "---------------\n"
                    + "1. Listing all Expenditures: list /e [DATE]\n"
                    + "2. Adding an Expenditure entry: add /e /pm [PAYMENT_METHOD] /c [CATEGORY] "
                    + "/d [DESCRIPTION] /a [AMOUNT] /t [DATE]\n"
                    + "3. Calculating the total expenditure in a month: calculate /epm [DATE]\n"
                    + "4. Updating an Expenditure: update /e [NEW_INDEX] /pm [NEW_PAYMENT_METHOD] /c [NEW_CATEGORY] "
                    + "/d [NEW_DESCRIPTION] /a [NEW_AMOUNT] /t [NEW_DATE]\n"
                    + "5. Removing an Expenditure entry: delete /e [INDEX]\n"
                    + "6. Exiting the program: bye\n"
                    + "----------------------------------------------------------------------------------------------"
                    + "-----\n";

            System.out.println(helpPage);
        } else {
            System.out.println("Invalid command! \n"
                    + "Type \"help /e\" to view the list of supported expenditure commands\n"
                    + "Type \"help /cc\" to view the list of supported Credit Card commands\n"
                    + "Type \"help /i\" to view the list of supported income commands\n");
        }
    }

    /**
     * Prints out the help page if the user requested for it. If not, it means an invalid command was received,
     * and prints out an error message.
     */
    public void printCreditCardHelpPage() {
        String helpPage = "---------------------------------------Credit Card Help Page--------------------------"
                + "-------------\n"
                + "1. Listing all Credit Cards: list /cc\n"
                + "2. Adding a Credit Card: add /cc /n [CREDIT_CARD_NAME] /cb [CASHBACK] /cl [CREDIT_LIMIT]\n"
                + "3. Updating a Credit Card: update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] "
                + "/cl [NEW_CREDIT_LIMIT]\n"
                + "4. Removing a credit card: delete /cc [INDEX]\n"
                + "5. Exiting the program: bye\n"
                + "-----------------------------------------------------------------------------------------------"
                + "----\n";

        System.out.println(helpPage);
    }

    /**
     * Prints out the Income help page.
     */
    public void printIncomeHelpPage() {
        String incomeHelpPage = "--------------------------------Income Help Page------------------------------"
                + "---------\n"
                + "1. Listing all Incomes: list /i\n"
                + "2. Adding an Income entry: add /i /a [AMOUNT] /c [CATEGORY]\n"
                + "3. Updating an Income entry: update /i [INDEX] /a [NEW_AMOUNT] /c [NEW_CATEGORY]\n"
                + "4. Removing an Income entry: delete /i [INDEX]\n"
                + "---------------------------------------------------------------------------------------\n";

        System.out.println(incomeHelpPage);
    }

    /**
     * Prints either the Expenditure, Credit Card or Income help page based on the user's input.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    public void executeCommand() throws MindMyMoneyException {
        if (helpInput.equals(EMPTY_PARAMETER)) {
            printExpenditureHelpPage();
            printCreditCardHelpPage();
            printIncomeHelpPage();
        } else if (hasExpensesFlag()) {
            printExpenditureHelpPage();
        } else if (hasCreditCardListFlag()) {
            printCreditCardHelpPage();
        } else if (hasIncomeFlag()) {
            printIncomeHelpPage();
        } else {
            throw new MindMyMoneyException("Please ensure that you have entered a valid help command.\n"
                    + "Type \"help /e\" to view the list of supported expenditure commands\n"
                    + "Type \"help /cc\" to view the list of supported Credit Card commands\n"
                    + "Type \"help /i\" to view the list of supported income commands\n");
        }
    }
}
