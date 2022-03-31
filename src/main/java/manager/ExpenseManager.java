package manager;

import java.io.IOException;

public class ExpenseManager {
    private Storage storage;
    /** Constructs a <code>RecordManager</code> with an empty list of records. */
    public ExpenseManager() {
        try {
            loadTotalExpense();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static double totalExpense = 0;
    private static double fashionExpense = 0;
    private static double foodExpense = 0;
    private static double accessoryExpense = 0;
    private static double otherExpense = 0;
    private static double subscriptionExpense = 0;

    public static void addToExpense(double value) {
        totalExpense += value;
    }
    public static void addToFashionExpense(double value) {
        fashionExpense += value;
    }

    public static void addToFoodExpense(double value) {
        foodExpense += value;
    }

    public static void addToAccessoryExpense(double value) {
        accessoryExpense += value;
    }

    public static void addToOtherExpense(double value) {
        otherExpense += value;
    }

    public static void addToSubscriptionExpense(double value) {
        subscriptionExpense += value;
    }

    public static void subtractFromExpense(double value) {
        totalExpense -= value;
    }

    public static double getTotalExpense() {
        return totalExpense;
    }

    public static double getFashionExpense() {
        return fashionExpense;
    }

    public static double getFoodExpense() {return foodExpense;}

    public static double getAccessoryExpense() {return accessoryExpense;}

    public static double getOtherExpense() {return otherExpense;}

    public static double getSubscriptionExpense() {return subscriptionExpense;}

    public static double calculateTotalExpense() {return subscriptionExpense+
            otherExpense+accessoryExpense+foodExpense+fashionExpense;}

    public static void resetExpenses() {
        fashionExpense = 0;
        foodExpense = 0;
        accessoryExpense = 0;
        otherExpense = 0;
        subscriptionExpense = 0;
    }

    /**
     * Calls <code>storage</code> method to load total expense from data file.
     *
     * @throws IOException data file cannot be read.
     */
    public void loadTotalExpense() throws IOException {
        try {
            totalExpense = storage.loadTotalExpenseFile();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Calls <code>storage</code> method to save total expense into data file.
     */
    public void saveTotalExpense() {
        try {
            storage.saveTotalExpenseFile(totalExpense);
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }
}
