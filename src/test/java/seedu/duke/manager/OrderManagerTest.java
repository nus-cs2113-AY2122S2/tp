package seedu.duke.manager;

import org.junit.jupiter.api.Test;
import seedu.duke.entities.Dish;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderManagerTest {
    @Test
    void deleteDish_IndexNotValid_ThrowIllegalArgumentException() {
        OrderManager.resetInstance();
        OrderManager orderManager = OrderManager.getInstance();
        orderManager.addToOrder(new Dish("hamburger", 5), 0);
        orderManager.addToOrder(new Dish("beef", 10), 0);
        orderManager.addToOrder(new Dish("hamburger", 5), 1);
        orderManager.addToOrder(new Dish("hamburger", 5), 1);
        assertEquals(25, orderManager.getAllValue());
    }
}
