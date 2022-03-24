package manager;

public class ExpenseManager {
    private static double totalExpense = 0;

    public static void addToExpense(double value) {
        totalExpense += value;
    }

    public static void subtractFromExpense(double value) {
        totalExpense -= value;
    }

    public static double getTotalExpense() {
        return totalExpense;
    }
}
