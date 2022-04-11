package seedu.duke.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DishTest {
    @Test
    void dish_InvalidInput_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Dish("", 10));
        assertThrows(IllegalArgumentException.class, () -> new Dish("Fake dish", -1));
        assertThrows(IllegalArgumentException.class, () -> new Dish("", -1));
    }
}