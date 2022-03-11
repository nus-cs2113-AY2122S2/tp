package seedu.allonus.expense;

public class Expense {

    private String date;
    private String amount;
    private String category;
    private String remark;
    private static int noOfItems = 0;

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
        return date + "|" + amount + "|" + category + "|" + remark;
    }

}
