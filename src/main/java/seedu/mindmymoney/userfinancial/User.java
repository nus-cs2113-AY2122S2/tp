package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;

public class User {
    private ExpenditureList expenditureListArray;
    private CreditCardList creditCardListArray;

    public User() {
    }

    public User(ExpenditureList expenditureListArray, CreditCardList creditCardListArray) {
        setExpenditureListArray(expenditureListArray);
        setCreditCardListArray(creditCardListArray);
    }

    public void setExpenditureListArray(ExpenditureList expenditureListArray) {
        this.expenditureListArray = expenditureListArray;
    }

    public void setCreditCardListArray(CreditCardList creditCardListArray) {
        this.creditCardListArray = creditCardListArray;
    }

    public ExpenditureList getExpenditureListArray() {
        return expenditureListArray;
    }

    public CreditCardList getCreditCardListArray() {
        return creditCardListArray;
    }
}
