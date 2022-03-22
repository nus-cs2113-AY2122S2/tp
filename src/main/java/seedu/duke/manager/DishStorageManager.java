package seedu.duke.manager;

import seedu.duke.entities.Dish;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DishStorageManager extends Manager {
    private static final String FILE = "dish";
    private final DishManager dishManager;

    public DishStorageManager(DishManager m) {
        super(FILE);
        dishManager = m;
    }


    @Override
    protected void loadData() throws Exception {
        Object obj = load();
        ArrayList<Object> list;
        if (!(obj instanceof ArrayList)) {
            throw new Exception("Input broken");
        }
        list = (ArrayList<Object>) obj;
        for (Object object : list) {
            if (!(object instanceof Dish)) {
                throw new Exception("Input elements broken");
            }
        }
        dishManager.setDishes((ArrayList)list);
    }

    @Override
    public void saveData() throws Exception {
        ArrayList<Dish> list = dishManager.getDishes();
        save(list);
    }
}
