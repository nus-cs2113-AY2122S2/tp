package seedu.duke.manager;

import com.sun.tools.javac.Main;
import seedu.duke.entities.Dish;
import seedu.duke.loggers.MainLogger;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * DishManager.
 */
public class DishManager {
    private List<Dish> dishes;
    private final DishStorageManager dishStorageManager;

    public DishManager() {
        dishes = new ArrayList<>();
        dishStorageManager = new DishStorageManager(this);
        try {
            dishStorageManager.loadData();
        } catch (Exception e) {
            MainLogger.logWarning(this, e.toString());
            MainLogger.logWarning(this, "Start with an empty menu");
            dishes.clear();
        }
    }

    /**
     * Print all the dishes/menu.
     */
    public void printDishes() {
        if (dishes.size() == 0) {
            System.out.println("There is no dish in the list");
            return;
        }
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println((i + 1) + ". " + dishes.get(i));
        }
    }

    /**
     * Return the number of dishes.
     *
     * @return the number of current dishes
     */
    public int getNumOfDishes() {
        return dishes.size();
    }

    /**
     * Add one dish to menu.
     *
     * @param dish the dish to add
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    /**
     * Try to delete dish in menu.
     *
     * @param menuIndex the index of dish to delete in menu
     * @throws IndexOutOfBoundsException if menuIndex is not valid
     */
    public void deleteDish(int menuIndex) throws IndexOutOfBoundsException {
        int listIndex = menuIndex - 1;
        throwIfIndexNotValid(listIndex);
        dishes.remove(listIndex);
    }

    /**
     * Try to set the price of one dish.
     *
     * @param menuIndex the index of dish to set price in menu
     * @param newPrice  the new price of the dish
     * @throws IndexOutOfBoundsException if menuIndex is not valid
     * @throws IllegalArgumentException  if new price is negative
     */
    public void setPrice(int menuIndex, double newPrice) throws IndexOutOfBoundsException, IllegalArgumentException {
        int listIndex = menuIndex - 1;
        throwIfIndexNotValid(listIndex);
        dishes.get(listIndex).setPrice(newPrice);
    }

    /**
     * Try to set the name of one dish.
     *
     * @param menuIndex the index of dish to set price in menu
     * @param newName   the new name of the dish
     * @throws IndexOutOfBoundsException if menuIndex is not valid
     * @throws IllegalArgumentException  if newName is null
     */
    public void setName(int menuIndex, String newName) throws IndexOutOfBoundsException, IllegalArgumentException {
        int listIndex = menuIndex - 1;
        throwIfIndexNotValid(listIndex);
        dishes.get(listIndex).setName(newName);
    }

    private void throwIfIndexNotValid(int listIndex) throws IndexOutOfBoundsException {
        if (listIndex < 0 || listIndex >= dishes.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get a copy of dishes.
     *
     * @return a copy of dishes
     */
    public ArrayList<Dish> getDishes() {
        return new ArrayList<>(dishes);
    }

    public void store() {
        try {
            dishStorageManager.saveData();
        } catch (Exception e) {
            MainLogger.logWarning(this, e.toString());
            MainLogger.logWarning(this, "Store an empty menu");
            // maybe a method to empty the file
            dishes.clear();
            try {
                dishStorageManager.saveData();
            } catch (Exception ee) {
                assert false;
            }
        }
    }

    public void setDishes(ArrayList<Dish> list) {
        dishes = list;
    }
}
