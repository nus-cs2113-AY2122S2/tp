package seedu.duke.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

class OrderTest {
    @Test
    void staff_InvalidInput_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Order(Arrays.asList(new Dish(null, 10))));
        assertThrows(IllegalArgumentException.class, () -> new Order(Arrays.asList(new Dish("chicken", 10), new Dish("potato", 5))));
    }
}

