package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;

/**
 * Represents the user.
 */
public class User {
    private ExpenditureList expenditureListArray;
    private CreditCardList creditCardListArray;
    private IncomeList incomeListArray;

    public User() {
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
}
