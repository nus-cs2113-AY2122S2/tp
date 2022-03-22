package seedu.duke.manager;

import seedu.duke.entities.Dish;

import java.io.IOException;
import java.util.ArrayList;

public class DishStorageManager extends Manager {
    private static final String FILE = "dish.txt";
    private final DishManager dishManager;

    public DishStorageManager(DishManager m) {
        super(FILE);
        dishManager = m;
    }


    @Override
    protected void loadData() throws Exception {
        Object obj = load();
        while (obj != null && obj instanceof Dish) {
            Dish dish = (Dish) obj;
            if (!dish.isValid()) {
                throw new IOException("Input dish invalid");
            }
            dishManager.addDish(dish);
            obj = load();
        }
    }

    @Override
    public void saveData() throws Exception {
        ArrayList<Dish> list = dishManager.getDishes();
        for (Dish dish : list) {
            save(dish);
        }
    }
}
