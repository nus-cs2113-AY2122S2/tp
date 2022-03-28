package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;

import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENSES;

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
     * @return Indication on whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private boolean hasExpensesFlag() {
        return FLAG_OF_EXPENSES.equals(helpInput);
    }

    private boolean hasCreditCardListFlag() {
        return FLAG_OF_CREDIT_CARD.equals(helpInput);
    }

    /**
     * Prints out the help page if the user requested for it. If not, it means an invalid command was received,
     * and prints out an error message.
     */
    public void printExpenditureHelpPage() {
        if (isFromUser) {
            String helpPage = "---------------------------------------Help Page--------------------------------"
                    + "-------\n"
                    + "1. Listing all Expenditures: list /expenses\n"
                    + "2. Adding an Expenditure entry: add /e [EXPENDITURE] /c [CATEGORY] "
                    + "/d [DESCRIPTION] /a [AMOUNT] /t [TIME]\n"
                    + "3. Calculating the total expenditure in a month: calculate /epm [MONTH]\n"
                    + "4. Updating an Expenditure entry: update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]\n"
                    + "5. Updating an Expenditure entry with category: update [INDEX] [NEW_DESCRIPTION] -c "
                    + "[NEW_CATEGORY] [NEW_AMOUNT]\n"
                    + "6. Removing an Expenditure entry: delete [INDEX]\n"
                    + "7. Exiting the program: bye\n"
                    + "---------------------------------------------------------------------------------------\n";

            System.out.println(helpPage);
        } else {
            System.out.println("Invalid command! \nType \"help /expenses\" to view the list of supported expenditure"
                    + " commands\nType \"help /cc\" to view the list of supported Credit Card commands"
                    + System.lineSeparator());
        }
    }

    /**
     * Prints out the help page if the user requested for it. If not, it means an invalid command was received,
     * and prints out an error message.
     */
    //add /cc /n name /cb 1.5 /cl 500 /bal 1000
    public void printCreditCardHelpPage() {
        String helpPage = "---------------------------------------Help Page--------------------------------"
                + "-------\n"
                + "1. Listing all Credit Cards: list /cc\n"
                + "2. Adding a Credit Card: add /cc /n [CREDIT_CARD_NAME] /cb [CASHBACK] /cl [CREDIT_LIMIT] "
                + "/bal [CREDIT CARD BALANCE]\n"
                + "3. Updating an Expenditure entry: update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] "
                + "/cl [NEW_CREDIT_LIMIT] /bal [NEW_BALANCE]\n"
                + "4. Removing a credit card: delete /cc [INDEX]\n"
                + "5. Exiting the program: bye\n"
                + "---------------------------------------------------------------------------------------\n";
        System.out.println(helpPage);
    }

    public void executeCommand() throws MindMyMoneyException {
        if (hasExpensesFlag()) {
            printExpenditureHelpPage();
        } else if (hasCreditCardListFlag()) {
            printCreditCardHelpPage();
        } else {
            throw new MindMyMoneyException("Please ensure that you have entered a valid list command.\n"
                    + "Type 'list /expenses' to view your current list of expenses\n"
                    + "Type 'list /cc' to view your current list of stored credit cards");
        }
    }
}
