package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.helper.ValidatorFunctions;

import java.util.Scanner;

/**
 * Represents the user.
 */
public class User {
    private ExpenditureList expenditureListArray;
    private CreditCardList creditCardListArray;
    private IncomeList incomeListArray;

    public User() {
        setExpenditureListArray(new ExpenditureList());
        setCreditCardListArray(new CreditCardList());
        setIncomeListArray(new IncomeList());
    }

    public User(ExpenditureList expenditureListArray, CreditCardList creditCardListArray,
                IncomeList incomeListArray) {
        setExpenditureListArray(expenditureListArray);
        setCreditCardListArray(creditCardListArray);
        setIncomeListArray(incomeListArray);
    }

    public void setExpenditureListArray(ExpenditureList expenditureListArray) {
        this.expenditureListArray = expenditureListArray;
    }

    public void setCreditCardListArray(CreditCardList creditCardListArray) {
        this.creditCardListArray = creditCardListArray;
    }

    public void setIncomeListArray(IncomeList incomeListArray) {
        this.incomeListArray = incomeListArray;
    }

    public ExpenditureList getExpenditureListArray() {
        return expenditureListArray;
    }

    public CreditCardList getCreditCardListArray() {
        return creditCardListArray;
    }

    public IncomeList getIncomeListArray() {
        return incomeListArray;
    }

    /**
     * Returns a String representation of this user in a machine-readable format.
     * @return A serialized User.
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(expenditureListArray.serialize());
        sb.append(creditCardListArray.serialize());
        sb.append(incomeListArray.serialize());
        return sb.toString();
    }


    /**
     * Converts the output of User#serialized back into a User. This method reads from
     * a Scanner
     * @param scanner A Scanner from which to read a serialized User.
     * @return The User.
     * @throws MindMyMoneyException if the format is incorrect.
     */
    public static User deserializeFrom(Scanner scanner) throws MindMyMoneyException {
        User savedUser = new User();

        savedUser.setExpenditureListArray(ExpenditureList.deserializeFrom(scanner));
        savedUser.setCreditCardListArray(CreditCardList.deserializeFrom(scanner));
        savedUser.setIncomeListArray(IncomeList.deserializeFrom(scanner));

        ValidatorFunctions.validateCreditCardNames(savedUser.creditCardListArray);
        ValidatorFunctions.validatePaymentMethods(savedUser.expenditureListArray, savedUser.creditCardListArray);

        return savedUser;
    }

}
