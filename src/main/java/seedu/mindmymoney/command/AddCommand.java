package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import static seedu.mindmymoney.helper.GeneralFunctions.parseInputWithCommandFlag;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_DESCRIPTION;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENDITURE;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CATEGORY;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_AMOUNT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_TIME;
import static seedu.mindmymoney.constants.Flags.FLAG_END_VALUE;
import static seedu.mindmymoney.helper.AddCommandInputTests.testAmount;
import static seedu.mindmymoney.helper.AddCommandInputTests.testExpenditure;
import static seedu.mindmymoney.helper.AddCommandInputTests.testCategory;
import static seedu.mindmymoney.helper.AddCommandInputTests.testDescription;
import static seedu.mindmymoney.helper.TimeFunctions.convertTime;

/**
 * Represents the Add command.
 */
public class AddCommand extends Command {
    private String addInput;
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;

    public AddCommand(String addInput, ExpenditureList expenditureList, CreditCardList creditCardList) {
        this.addInput = addInput;
        this.expenditureList = expenditureList;
        this.creditCardList = creditCardList;
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
     * Sets the EXPENDITURE, CATEGORY, DESCRIPTION, AMOUNT and TIME fields in the users' expenditure
     * and adds it into the list.
     *
     * @throws MindMyMoneyException when inputs are invalid or flags are missing.
     */
    public void executeCommand() throws MindMyMoneyException {
        String expenditure = parseInputWithCommandFlag(addInput, FLAG_OF_EXPENDITURE, FLAG_OF_CATEGORY);
        testExpenditure(expenditure, creditCardList);
        String category = parseInputWithCommandFlag(addInput, FLAG_OF_CATEGORY, FLAG_OF_DESCRIPTION);
        testCategory(category);
        String description = parseInputWithCommandFlag(addInput, FLAG_OF_DESCRIPTION, FLAG_OF_AMOUNT);
        testDescription(description);
        String amount = parseInputWithCommandFlag(addInput, FLAG_OF_AMOUNT, FLAG_OF_TIME);
        testAmount(amount);
        float amountInt = Float.parseFloat(amount);
        String time = parseInputWithCommandFlag(addInput, FLAG_OF_TIME, FLAG_END_VALUE);
        time = convertTime(time);

        expenditureList.add(new Expenditure(expenditure, category, description, amountInt, time));
        System.out.println("Successfully added: \n\n"
                + "Description: " + description + "\n"
                + "Amount: $" + amount + "\n"
                + "Category: " + category + "\n"
                + "Payment method: " + expenditure + "\n"
                + "Date: " + time + "\n\n"
                + "into the account");
        System.out.print(System.lineSeparator());
    }

}
