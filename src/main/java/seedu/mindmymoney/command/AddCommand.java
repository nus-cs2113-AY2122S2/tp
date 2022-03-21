package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;

import static seedu.mindmymoney.helper.GeneralFunctions.parseInputWithCommandFlag;
import static seedu.mindmymoney.constants.Flags.flagOfDescription;
import static seedu.mindmymoney.constants.Flags.flagOfExpenditure;
import static seedu.mindmymoney.constants.Flags.flagOfCategory;
import static seedu.mindmymoney.constants.Flags.flagOfAmount;
import static seedu.mindmymoney.constants.Flags.flagOfTime;
import static seedu.mindmymoney.constants.Flags.flagEndValue;
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
        String expenditure = null;
        String category = null;
        String description = null;
        String amount = null;
        String time = null;
        float amountInt = 0;

        expenditure = parseInputWithCommandFlag(addInput, flagOfExpenditure, flagOfCategory);
        testExpenditure(expenditure, creditCardList);
        category = parseInputWithCommandFlag(addInput, flagOfCategory, flagOfDescription);
        testCategory(category);
        description = parseInputWithCommandFlag(addInput, flagOfDescription, flagOfAmount);
        testDescription(description);
        amount = parseInputWithCommandFlag(addInput, flagOfAmount, flagOfTime);
        testAmount(amount);
        amountInt = Float.parseFloat(amount);
        time = parseInputWithCommandFlag(addInput, flagOfTime, flagEndValue);
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
