package manager;

import java.io.IOException;
import java.math.BigDecimal;

public class ExpenseManager {
    /** Constructs a <code>RecordManager</code> with an empty list of records. */
    public ExpenseManager() {
        try {
            loadTotalExpense();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BigDecimal totalExpense = BigDecimal.valueOf(0);
    private static BigDecimal fashionExpense = BigDecimal.valueOf(0);
    private static BigDecimal foodExpense = BigDecimal.valueOf(0);
    private static BigDecimal accessoryExpense = BigDecimal.valueOf(0);
    private static BigDecimal otherExpense = BigDecimal.valueOf(0);
    private static BigDecimal subscriptionExpense = BigDecimal.valueOf(0);

    public static void addToExpense(double value) {
        totalExpense = totalExpense.add(BigDecimal.valueOf(value));
    }
    public static void addToFashionExpense(double value) {
        fashionExpense = fashionExpense.add(BigDecimal.valueOf(value));
    }

    public static void addToFoodExpense(double value) {
        foodExpense = foodExpense.add(BigDecimal.valueOf(value));
    }

    public static void addToAccessoryExpense(double value) {
        accessoryExpense = accessoryExpense.add(BigDecimal.valueOf(value));
    }

    public static void addToOtherExpense(double value) {
        otherExpense = otherExpense.add(BigDecimal.valueOf(value));
    }

    public static void addToSubscriptionExpense(double value) {
        subscriptionExpense = subscriptionExpense.add(BigDecimal.valueOf(value));
    }

    public static void subtractFromExpense(double value) {
        totalExpense = totalExpense.subtract(BigDecimal.valueOf(value));
    }

    public static double getTotalExpense() {
        return totalExpense.doubleValue();
    }

    public static double getFashionExpense() {
        return fashionExpense.doubleValue();
    }

    public static double getFoodExpense() {
        return foodExpense.doubleValue();
    }

    public static double getAccessoryExpense() {
        return accessoryExpense.doubleValue();
    }

    public static double getOtherExpense() {
        return otherExpense.doubleValue();
    }

    public static double getSubscriptionExpense() {
        return subscriptionExpense.doubleValue();
    }

    public static double calculateTotalExpense() {
        return subscriptionExpense.
                add(otherExpense).
                add(accessoryExpense).
                add(foodExpense).
                add(fashionExpense).
                doubleValue();
    }

    public static void resetExpenses() {
        fashionExpense = BigDecimal.valueOf(0);
        foodExpense = BigDecimal.valueOf(0);
        accessoryExpense = BigDecimal.valueOf(0);
        otherExpense = BigDecimal.valueOf(0);
        subscriptionExpense = BigDecimal.valueOf(0);
    }

    /**
     * Calls <code>storage</code> method to load total expense from data file.
     *
     * @throws IOException data file cannot be read.
     */
    public void loadTotalExpense() throws IOException {
        try {
            totalExpense = BigDecimal.valueOf(Storage.loadTotalExpenseFile());
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Calls <code>storage</code> method to save total expense into data file.
     */
    public void saveTotalExpense() {
        try {
            Storage.saveTotalExpenseFile(totalExpense.doubleValue());
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }
}
