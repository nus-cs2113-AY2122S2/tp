package seedu.duke.manager;

import org.junit.jupiter.api.Test;
import seedu.duke.entities.Dish;

import static org.junit.jupiter.api.Assertions.*;

class DishManagerTest {
    @Test
    void deleteDish_IndexNotValid_ThrowIllegalArgumentException() {
        DishManager dishManager = new DishManager();
        dishManager.addDish(new Dish("A", 1));
        dishManager.addDish(new Dish("B", 0));
        dishManager.addDish(new Dish("C", 30));
        dishManager.addDish(new Dish("D", 2));
        dishManager.addDish(new Dish("E", 7));
        assertDoesNotThrow(() -> dishManager.deleteDish(1));
        assertDoesNotThrow(() -> dishManager.deleteDish(2));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.deleteDish(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.deleteDish(0));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.deleteDish(4));
        assertDoesNotThrow(() -> dishManager.deleteDish(3));
        assertDoesNotThrow(() -> dishManager.deleteDish(1));
        assertDoesNotThrow(() -> dishManager.deleteDish(1));
        assertEquals(0, dishManager.getNumOfDishes());
    }

    @Test
    void setPrice_IndexNotValidOrPriceNotValid_Throw() {
        DishManager dishManager = new DishManager();
        dishManager.addDish(new Dish("A", 1));
        dishManager.addDish(new Dish("B", 0));
        dishManager.addDish(new Dish("C", 30));
        dishManager.addDish(new Dish("D", 2));
        dishManager.addDish(new Dish("E", 7));
        assertDoesNotThrow(() -> dishManager.setPrice(1, 10));
        assertDoesNotThrow(() -> dishManager.setPrice(2, 20));
        assertThrows(IllegalArgumentException.class, () -> dishManager.setPrice(1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.setPrice(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.setPrice(-1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.setPrice(6, 100));
    }

    @Test
    void setName_IndexNotValidOrNameNotValid_Throw() {
        DishManager dishManager = new DishManager();
        dishManager.addDish(new Dish("A", 1));
        dishManager.addDish(new Dish("B", 0));
        dishManager.addDish(new Dish("C", 30));
        dishManager.addDish(new Dish("D", 2));
        dishManager.addDish(new Dish("E", 7));
        assertDoesNotThrow(() -> dishManager.setName(1, "AAAAA"));
        assertDoesNotThrow(() -> dishManager.setName(2, "BBBBB"));
        assertThrows(IllegalArgumentException.class, () -> dishManager.setName(1, ""));
        assertThrows(IllegalArgumentException.class, () -> dishManager.setName(1, null));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.setName(0, "CCCCC"));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.setName(-1, "DDDDD"));
        assertThrows(IndexOutOfBoundsException.class, () -> dishManager.setName(6, "EEEEE"));
    }
}