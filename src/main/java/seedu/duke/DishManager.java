package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class DishManager {
    private List<Dish> dishes;

    public DishManager() {
        dishes = new ArrayList<>();
    }

    public void printDishes() {
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println((i+1) + ". " + dishes.get(i));
        }
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public boolean tryDeleteDish(int menuIndex) {
        int listIndex = menuIndex - 1;
        if (!isListIndexValid(listIndex)) {
            return false;
        }
        dishes.remove(listIndex);
        return true;
    }

    public boolean trySetPrice(int menuIndex, double newPrice) {
        int listIndex = menuIndex - 1;
        if(!isListIndexValid(listIndex)) {
            return false;
        }
        dishes.get(listIndex).setPrice(newPrice);
        return true;
    }

    private boolean isListIndexValid(int listIndex) {
        if(listIndex < 0 || listIndex >= dishes.size()) {
            return false;
        }
        return true;
    }
}
