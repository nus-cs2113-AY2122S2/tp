package seedu.duke.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {
    @Test
    void Dish_InvalidInput_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->new Dish(null, 10));
        assertThrows(IllegalArgumentException.class, ()->new Dish("Fake dish", -1));
        assertThrows(IllegalArgumentException.class, ()->new Dish(null, -1));
    }
}