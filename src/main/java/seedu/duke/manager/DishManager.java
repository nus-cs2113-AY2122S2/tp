package seedu.duke.manager;

import seedu.duke.entities.Dish;
import seedu.duke.loggers.MainLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * DishManager.
 */
public class DishManager extends Manager {
    private static DishManager singleton = null;
    private List<Dish> dishes;
    private static final String STORAGE_FILE = "dish.dat";

    private DishManager() {
        super(STORAGE_FILE);
        try {
            this.loadData();
        } catch (Exception e) {
            MainLogger.logWarning(this, e.toString());
            MainLogger.logWarning(this, "Start with an empty menu");
            dishes.clear();
        }
    }

    public static DishManager getInstance() {
        if (singleton != null) {
            return singleton;
        }
        singleton = new DishManager();
        return singleton;
    }

    public static void resetInstance() {
        singleton = null;
    }

    @Override
    protected void loadData() throws Exception {
        this.dishes = new ArrayList<>();
        ArrayList<?> list = (ArrayList<?>) this.load();
        for (Object object : list) {
            this.addDish((Dish) object);
        }
    }

    @Override
    public void saveData() throws Exception {
        this.save(this.dishes);
    }

    /**
     * Print all the dishes/menu.
     */
    public String printDishes() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dishes.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, dishes.get(i)));
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
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
}
