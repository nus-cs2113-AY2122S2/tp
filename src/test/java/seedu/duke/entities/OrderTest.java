package seedu.duke.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    @Test
    void order_InvalidInput_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Order());
    }
}