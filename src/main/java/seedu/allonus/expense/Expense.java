package seedu.allonus.expense;

import java.util.logging.Logger;

public class Expense {

    private String date;
    private String amount;
    private String category;
    private String remark;
    static  Logger logger;
    private static int noOfItems = 0;

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getRemark() {
        return remark;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static void setNoOfItems(int noOfItems) {
        Expense.noOfItems = noOfItems;
    }

    public static int getNoOfItems() {
        return noOfItems;
    }

    public Expense(String date, String amount, String category, String remark) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return date + " | " + amount + " | " + category + " | " + remark;
    }

}
