package seedu.duke;

/**
 * Represents a Satisfaction object. Attributes include satisfactionValue
 * (a value from 1-5) and customerName (the name of the customer with
 * this satisfactionValue).
 */

public class Satisfaction {
    protected String customerName;
    protected int satisfactionValue;

    public Satisfaction(String customerName, int satisfactionValue) {
        this.customerName = customerName;
        this.satisfactionValue = satisfactionValue;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getSatisfactionValue() {
        return satisfactionValue;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setSatisfactionValue(int satisfactionValue) {
        this.satisfactionValue = satisfactionValue;
    }

}

