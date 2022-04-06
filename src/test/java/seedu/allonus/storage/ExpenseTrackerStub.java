package seedu.allonus.storage;

import seedu.allonus.expense.Expense;
import seedu.allonus.expense.ExpenseTracker;

import java.util.ArrayList;

/**
 * Stub class for ExpenseTracker for unit testing.
 */
public class ExpenseTrackerStub extends ExpenseTracker {
    private ArrayList<Expense> list = new ArrayList<Expense>();
    private static int number;

    public ExpenseTrackerStub() {
        this.number = 0;
    }

    @Override
    public int getExpenseCount() {
        return number;
    }

    @Override
    public ArrayList<Expense> getExpenseList() {
        return list;
    }

    @Override
    public void loadAdd(String savedExpense) {
        String[] components = savedExpense.split(" ",5);
        Expense expense = new Expense(components[1].substring(2),components[2].substring(2),
                components[3].substring(2),components[4].substring(2));
        list.add(expense);
        number += 1;
    }
}
